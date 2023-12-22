package com.repository;

import com.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
    public User getExistUser(String ktpNumber){
        return find("ktpNumber", ktpNumber).firstResultOptional().orElse(null);
    }
    
}
