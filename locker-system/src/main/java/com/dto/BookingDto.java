package com.dto;

import java.math.BigInteger;

public class BookingDto {
    private BigInteger userId; 
    private int qtyLocker;
    
    //getter setter
    public BigInteger getUserId() {
        return userId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public int getQtyLocker() {
        return qtyLocker;
    }
    public void setQtyLocker(int qtyLocker) {
        this.qtyLocker = qtyLocker;
    }

    //method
    @Override
    public String toString() {
        return "BookingDto [userId=" + userId + ", qtyLocker=" + qtyLocker + "]";
    }    
}
