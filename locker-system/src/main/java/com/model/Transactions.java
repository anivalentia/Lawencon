package com.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transactions extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger bookingId;
    private BigInteger userId;
    private BigInteger lockerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;
    private BigInteger deposit;
    private String status;
    private Integer qtyAccess;
    private String token;
    private Boolean isLocked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lockerId", referencedColumnName = "lockerId",insertable = false , updatable = false)
    private Locker locker;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "userId",insertable = false , updatable = false)
    private User user;

    //getter setter
    public BigInteger getBookingId() {
        return bookingId;
    }
    public void setBookingId(BigInteger bookingId) {
        this.bookingId = bookingId;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public BigInteger getLockerId() {
        return lockerId;
    }
    public void setLockerId(BigInteger lockerId) {
        this.lockerId = lockerId;
    }
    public Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    public BigInteger getDeposit() {
        return deposit;
    }
    public void setDeposit(BigInteger deposit) {
        this.deposit = deposit;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getQtyAccess() {
        return qtyAccess;
    }
    public void setQtyAccess(Integer qtyAccess) {
        this.qtyAccess = qtyAccess;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Boolean getIsLocked() {
        return isLocked;
    }
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    public Locker getLocker() {
        return locker;
    }
    public void setLocker(Locker locker) {
        this.locker = locker;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    //method
    @Override
    public String toString() {
        return "Transactions [bookingId=" + bookingId + ", userId=" + userId + ", lockerId=" + lockerId
                + ", bookingDate=" + bookingDate + ", deposit=" + deposit + ", status=" + status + ", qtyAccess="
                + qtyAccess + ", token=" + token + "]";
    }    
}
