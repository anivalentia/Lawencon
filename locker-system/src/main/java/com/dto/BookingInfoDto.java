package com.dto;

import java.math.BigInteger;
import java.util.List;

public class BookingInfoDto {
    private List<BigInteger> bookingIds;
    
    //getter setter
    public List<BigInteger> getBookingIds() {
        return bookingIds;
    }
    public void setBookingIds(List<BigInteger> bookingIds) {
        this.bookingIds = bookingIds;
    }

    //method
    @Override
    public String toString() {
        return "BookingInfoDto [bookingIds=" + bookingIds + "]";
    }        
}
