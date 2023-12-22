package com.model;

import java.math.BigInteger;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "mst_user")
@PrimaryKeyJoinColumn(name = "userId",referencedColumnName = "userId")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger userId;
    private String ktpNumber;
    private String phoneNumber;
    private String email;
    
    //getter setter
    public BigInteger getUserid() {
        return userId;
    }
    public void setUserid(BigInteger userid) {
        this.userId = userid;
    }
    public String getKtpnumber() {
        return ktpNumber;
    }
    public void setKtpnumber(String ktpnumber) {
        this.ktpNumber = ktpnumber;
    }
    public String getPhonenumber() {
        return phoneNumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
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
        return "User [userid=" + userId + ", ktpnumber=" + ktpNumber + ", phonenumber=" + phoneNumber + ", email="
                + email + "]";
    }
}
