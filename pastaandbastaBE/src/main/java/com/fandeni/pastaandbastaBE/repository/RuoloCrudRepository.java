package com.fandeni.pastaandbastaBE.repository;

import com.fandeni.pastaandbastaBE.model.Ruolo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RuoloCrudRepository extends CrudRepository<Ruolo, Integer> {
    Optional<Ruolo> findAllByDescrizione(String desc);
}
