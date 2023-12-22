package com.model;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "returns")
public class Returns extends PanacheEntityBase{
    @Id
    private BigInteger bookingId;
    private BigInteger userId;
    private BigInteger lockerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date bookingDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private BigInteger holdDeposit;
    private BigInteger penaltyAmount;
    private boolean isLocked;
    private boolean isLate;
    private BigInteger paidPenalty;

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
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public BigInteger getHoldDeposit() {
        return holdDeposit;
    }
    public void setHoldDeposit(BigInteger holdDeposit) {
        this.holdDeposit = holdDeposit;
    }
    public BigInteger getPenaltyAmount() {
        return penaltyAmount;
    }
    public void setPenaltyAmount(BigInteger penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void setLock(boolean isLocked) {
        this.isLocked = isLocked;
    }
    public boolean isLate() {
        return isLate;
    }
    public void setLate(boolean isLate) {
        this.isLate = isLate;
    }
    public BigInteger getPaidPenalty() {
        return paidPenalty;
    }
    public void setPaidPenalty(BigInteger paidPenalty) {
        this.paidPenalty = paidPenalty;
    }

    //method
    @Override
    public String toString() {
        return "Return [bookingId=" + bookingId + ", userId=" + userId + ", lockerId=" + lockerId + ", bookingDate="
                + bookingDate + ", returnDate=" + returnDate + ", holdDeposit=" + holdDeposit + ", penaltyAmount="
                + penaltyAmount + ", isLocked=" + isLocked + ", isLate=" + isLate + ", paidPenalty=" + paidPenalty + "]";
    }
}
