package com.fandeni.pastaandbastaBE.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fandeni.pastaandbastaBE.model.Hashtag;

@Repository
public interface HashtagCrudRepository extends CrudRepository<Hashtag, Integer>{
	
	Optional<Hashtag> findByDescrizione(String Descrizione); 
	Optional<Hashtag> findById(Integer id); 

}
