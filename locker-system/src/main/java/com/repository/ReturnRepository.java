package com.repository;

import java.math.BigInteger;

import com.model.Returns;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReturnRepository implements PanacheRepository<Returns>{
    public Returns getByBookingId(BigInteger bookingId){
        return find("bookingId", bookingId).firstResultOptional().orElse(null);
    }
}
