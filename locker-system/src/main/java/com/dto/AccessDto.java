package com.dto;

import java.math.BigInteger;

public class AccessDto {
    private BigInteger bookingId;
    private String password;

    //getter setter
    public BigInteger getBookingId() {
        return bookingId;
    }
    public void setBookingId(BigInteger bookingId) {
        this.bookingId = bookingId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    //method
    @Override
    public String toString() {
        return "AccessDto [bookingId=" + bookingId + ", password=" + password + "]";
    }
     
}
