package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.customException.NoUserFoundExceptioin;
import com.fandeni.pastaandbastaBE.customException.UserAlreadyExistsException;
import com.fandeni.pastaandbastaBE.model.Ruolo;
import com.fandeni.pastaandbastaBE.model.Utente;
import com.fandeni.pastaandbastaBE.repository.RuoloCrudRepository;
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
    @Autowired
    private RuoloCrudRepository ruoloCrudRepository;

    public boolean checkLoginUsername(String username, String password) throws NoUserFoundExceptioin {
        Optional<Utente> optionalUtente = utenteCrudRepository.findByUsername(username);
        if(optionalUtente.isPresent()){
            Utente u = optionalUtente.get();
            return passwordEncoder.matches(password, u.getPassword());
        }else{
            throw new NoUserFoundExceptioin("Wrong username!");
        }
    }

    public boolean checkLoginEmail(String email, String password) throws NoUserFoundExceptioin {
        Optional<Utente> optionalUtente = utenteCrudRepository.findByEmail(email);
        if(optionalUtente.isPresent()){
            Utente u = optionalUtente.get();
            return passwordEncoder.matches(password, u.getPassword());
        }else{
            throw new NoUserFoundExceptioin("Wrong email!");
        }
    }

    public boolean checkRegistration(Utente utente) throws UserAlreadyExistsException {
        if(utenteCrudRepository.findByUsername(utente.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("Username is already taken!");
        }else if(utenteCrudRepository.findByEmail(utente.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("Email is already associated to another account!");
        }
        try{
            utente.setPassword(passwordEncoder.encode(utente.getPassword()));
            utente.setRole(ruoloCrudRepository.findAllByDescrizione("USER").get());
            utenteCrudRepository.save(utente);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
