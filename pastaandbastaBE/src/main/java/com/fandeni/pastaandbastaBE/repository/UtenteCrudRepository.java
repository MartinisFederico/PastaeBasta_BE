package com.fandeni.pastaandbastaBE.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fandeni.pastaandbastaBE.model.Utente;

@Repository
public interface UtenteCrudRepository extends CrudRepository<Utente, Integer>{
	Optional<Utente> findByUsername(String username);
	Optional<Utente> findByEmail(String email);
}
