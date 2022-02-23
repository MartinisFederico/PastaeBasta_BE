package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.model.Ruolo;
import com.fandeni.pastaandbastaBE.repository.RuoloCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitializationService {
    @Autowired
    RuoloCrudRepository ruoloCrudRepository;

    public void insertRole(List<Ruolo> roles){
        try{
            if(((List<Ruolo>)ruoloCrudRepository.findAll()).isEmpty())
                ruoloCrudRepository.saveAll(roles);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
