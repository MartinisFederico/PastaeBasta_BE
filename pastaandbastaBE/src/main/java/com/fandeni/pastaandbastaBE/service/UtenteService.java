package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.conf.PasswordUtils;
import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.model.Utente;
import com.fandeni.pastaandbastaBE.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    //PasswordUtils.encrypt generates a different hash based on the time,
    // but like this I cannot compare the two hash and see if password is correct, so I use BCryptPasswordEncoder
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    @Autowired
    private UtenteCrudRepository utenteCrudRepository;

    public boolean checkLoginUsername(String username, String password) throws NoUserFoundExceptioin {
        Optional<Utente> optionalUtente = utenteCrudRepository.findByUsername(username);
        if(optionalUtente.isPresent()){
            Utente u = optionalUtente.get();
            return passwordEncoder.encode(password).equals(u.getPassword());
        }else{
            throw new NoUserFoundExceptioin("Wrong username!");
        }
    }
}
