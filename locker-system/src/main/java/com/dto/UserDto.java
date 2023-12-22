package com.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    @NotEmpty (message = "ktpNumber is empty")
    private String ktpNumber;
    @NotEmpty (message = "phoneNumber is empty")
    private String phoneNumber;
    @NotEmpty (message = "email is empty")
    private String email;
    //getter setter
    public String getKtpNumber() {
        return ktpNumber;
    }
    public void setKtpNumber(String ktpNumber) {
        this.ktpNumber = ktpNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //method
    @Override
    public String toString() {
        return "UserDto [ktpNumber=" + ktpNumber + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
    }    
}
