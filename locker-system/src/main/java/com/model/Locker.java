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
@Table(name = "lockers")
@PrimaryKeyJoinColumn(name = "lockerId", referencedColumnName = "lockerId")
public class Locker extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger lockerId;
    private Boolean isAvailable;
    private Boolean isLock;
    private BigInteger userId;

    //getter setter
    public BigInteger getLockerId() {
        return lockerId;
    }
    public void setLockerId(BigInteger lockerId) {
        this.lockerId = lockerId;
    }
    public Boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public Boolean isLock() {
        return isLock;
    }
    public void setLocked(Boolean isLock) {
        this.isLock = isLock;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    //method
    @Override
    public String toString() {
        return "Locker [lockerId=" + lockerId + ", isAvailable=" + isAvailable + ", isLock=" + isLock + ", userId="
                + userId + "]";
    } 
}
