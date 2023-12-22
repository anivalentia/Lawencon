package com.repository;

import java.util.List;

import com.model.Locker;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LockerRepository implements PanacheRepository<Locker>{
    public List<Locker> getAvailableLockers(){
        return find("isAvailable", true).list();
    }

    public Locker getLockerById(String lockerId){
        return find("lockerId", lockerId).firstResultOptional().orElse(null);
    }
    
}
