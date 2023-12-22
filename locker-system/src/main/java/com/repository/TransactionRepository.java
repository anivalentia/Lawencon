package com.repository;

import java.math.BigInteger;
import java.util.List;

import com.model.Transactions;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<Transactions>{
    public Transactions getByBookingId(BigInteger bookingId){
        return find("bookingId", bookingId).firstResultOptional().orElse(null);
    }

    public List<Transactions> getByUserId(BigInteger userId){
        return find("userId = ?1 and status = ?2", userId,"BOOKING").list();
    }

    public List<Transactions> getByBookingId(List<BigInteger> bookingIds){
        return find("bookingId IN ?1", bookingIds).list();
    }
}
