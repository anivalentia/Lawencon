package com.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.dto.AccessDto;
import com.dto.BookingDto;
import com.dto.BookingInfoDto;
import com.dto.UserDto;
import com.model.Locker;
import com.model.Returns;
import com.model.Transactions;
import com.model.User;
import com.repository.LockerRepository;
import com.repository.TransactionRepository;
import com.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class LockerService {
    @Inject
    LockerRepository lockerRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    TransactionRepository transactionRepository;

    private static BigInteger DEPOSIT = BigInteger.valueOf(10000L);
    private static String BOOKING_STATUS = "BOOKING";
    private static String RETURN_STATUS = "RETURN";
    private static BigInteger LATE_PENALTY = BigInteger.valueOf(5000);
    private static BigInteger LOCKED_PENALTY = BigInteger.valueOf(25000);
    
    @Transactional
    public Map<String,Object> registerUser(UserDto dto ){
        String exist = "exist";

        User user = userRepository.getExistUser(dto.getKtpNumber());
        if(Objects.isNull(user)){
            user = new User();
            user.setKtpnumber(dto.getKtpNumber());
            exist = "new";
        }        
        user.setEmail(dto.getEmail());
        user.setPhonenumber(dto.getPhoneNumber());
        user.persist();

        Map<String,Object> res = new HashMap<>();
        res.put("userId", user.getUserid());
        res.put("exist", exist);
        return res;
    }

    @Transactional
    public Map<String,Object> booking(BookingDto dto){
        if(dto.getQtyLocker() > 3){
            throw new WebApplicationException("max booking is 3");
        }

        List<Transactions> userBooking = transactionRepository.getByUserId(dto.getUserId());
        if(userBooking.size() + dto.getQtyLocker() > 3){
            int availableBook = 3-userBooking.size();
            throw new WebApplicationException("remaining booking available :"+availableBook);
        }

        List<Locker> lockers = lockerRepository.getAvailableLockers();
        List<Transactions> bookingList = new ArrayList<>();

        if(lockers.isEmpty() || dto.getQtyLocker() > lockers.size()){
            throw new WebApplicationException("remaining lokers : "+lockers.size());
        }else{
            List<BigInteger> bookingIds = new ArrayList<>();
            while (bookingList.size() < dto.getQtyLocker()) {
                Locker locker = lockers.get(bookingList.size());
                locker.setAvailable(false);
                locker.setUserId(dto.getUserId());
                locker.persist();

                Transactions trans = new Transactions();
                trans.setBookingDate(new Date());
                trans.setDeposit(DEPOSIT);
                trans.setIsLocked(false);
                trans.setLockerId(locker.getLockerId());
                trans.setQtyAccess(0);
                trans.setStatus(BOOKING_STATUS);
                trans.setUserId(dto.getUserId());

                trans.persist();
                
                bookingList.add(trans);
                bookingIds.add(trans.getBookingId());
            }         
        }
    
        Map<String,Object> res = new HashMap<>();
        res.put("lockers", bookingList);
        return res;        
    }

    @Transactional
    public String generatePassword(BookingInfoDto dto){
        String ok = "done";
        List<Transactions> bookindList = transactionRepository.getByBookingId(dto.getBookingIds());
        for (Transactions booking : bookindList) {
            booking.setToken(RandomStringUtils.randomAlphanumeric(12));
            booking.persist();

            Locker locker = booking.getLocker();
            locker.setLocked(false);
            locker.persist();
        }
        this.sendToEmail(bookindList);
        return ok;
    }

    @Transactional
    public Map<String,Object> returnLokers(BookingInfoDto dto){
        List<Transactions> bookindList = transactionRepository.getByBookingId(dto.getBookingIds());
        List<Returns> returns = new ArrayList<>();

        for (Transactions booking : bookindList) {
            booking.setStatus(RETURN_STATUS);
            booking.persist();

            Locker locker = booking.getLocker();
            locker.setLocked(false);
            locker.setAvailable(true);
            locker.setUserId(null);
            locker.persist();

            Returns rtn = new Returns();
            rtn.setBookingDate(booking.getBookingDate());
            rtn.setBookingId(booking.getBookingId());
            rtn.setLock(booking.getIsLocked());
            rtn.setLockerId(booking.getLockerId());
            rtn.setReturnDate(new Date());
            rtn.setUserId(booking.getUserId());   
            rtn.setLate(false);

            this.countPenalty(rtn,booking);

            rtn.persist();     
            returns.add(rtn);
        }
        Map<String,Object> res = new HashMap<>();
        res.put("returnInfo", returns);
        return res;
    }

    private Returns countPenalty(Returns rtn,Transactions trns){
        Long returnDays = ChronoUnit.DAYS.between(LocalDate.ofInstant(rtn.getBookingDate().toInstant(), ZoneId.systemDefault()),LocalDate.ofInstant(rtn.getReturnDate().toInstant(), ZoneId.systemDefault()));
        BigInteger latePenalty = BigInteger.ZERO; 

        if(returnDays > 1){
            rtn.setLate(true);
            latePenalty = LATE_PENALTY.multiply(BigInteger.valueOf(returnDays-1));
        }

        BigInteger lockedPenalty = BigInteger.ZERO;
        if(Boolean.TRUE.equals(trns.getIsLocked())){
            lockedPenalty = LOCKED_PENALTY;
        }
        
        BigInteger penaltyAmount = latePenalty.add(lockedPenalty);        
  
        rtn.setHoldDeposit(penaltyAmount.compareTo(trns.getDeposit()) > 1? trns.getDeposit().subtract(penaltyAmount):trns.getDeposit());
        rtn.setPenaltyAmount(penaltyAmount);                        
        rtn.setPaidPenalty(penaltyAmount.subtract(Boolean.TRUE.equals(trns.getIsLocked()) ? BigInteger.ZERO: rtn.getHoldDeposit()));

        return rtn;
    }

    @Transactional
    public String accessLocker(AccessDto dto){
        Transactions trns = transactionRepository.getByBookingId(dto.getBookingId());
        if(Objects.isNull(trns)){
            throw new WebApplicationException("booking id not found");
        }
        if(Boolean.TRUE.equals(trns.getLocker().isLock())){
                throw new WebApplicationException("cannot access, you got blocked");
        }
        if(dto.getPassword().equals(trns.getToken()) && trns.getQtyAccess() < 3){
            trns.setQtyAccess(trns.getQtyAccess()+1);    
            if(trns.getQtyAccess()>2 && !Boolean.TRUE.equals(trns.getIsLocked())){
                trns.setIsLocked(true);                        

                Locker locker = trns.getLocker();
                locker.setLocked(true);
                locker.persist();
            }
            trns.persist();
        }else{
            trns.setQtyAccess(trns.getQtyAccess()+1);
    
            if(trns.getQtyAccess()>2 && !Boolean.TRUE.equals(trns.getIsLocked())){
                trns.setIsLocked(true);                        

                Locker locker = trns.getLocker();
                locker.setLocked(true);
                locker.persist();
            }
            trns.persist();
            return "Incorrect password or has reached access limit";
        }
        return "OK";
    } 
    
    public Transactions updateLoginInfo(Transactions trans){
        trans.setQtyAccess(trans.getQtyAccess()+1);
        
        if(trans.getQtyAccess()>2 && !Boolean.TRUE.equals(trans.getIsLocked())){
            trans.setIsLocked(true);                        

            Locker locker = trans.getLocker();
            locker.setLocked(true);
            locker.persist();
        }
        trans.persist();
        return trans;
    }

    public void sendToEmail(List<Transactions> listBooking){
        //todo:send locker id and password via email
    }

}
