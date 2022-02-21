package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.conf.PasswordUtils;
import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.model.Utente;
import com.fandeni.pastaandbastaBE.repository.UtenteCrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    private UtenteCrudRepository utenteCrudRepository;
    public boolean checkLoginUsername(String username, String password) throws NoUserFoundExceptioin {
        Optional<Utente> optionalUtente = utenteCrudRepository.findByUsername(username);
        if(optionalUtente.isPresent()){
            Utente u = optionalUtente.get();
            return PasswordUtils.encrypt(password).equals(u.getPassword());
        }else{
            throw new NoUserFoundExceptioin("Wrong username!");
        }
    }
}
