package com.fandeni.pastaandbastaBE.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fandeni.pastaandbastaBE.model.Categoria;

@Repository
public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer>{
	
	Optional<Categoria> findByDescrizione(String descrizione); 
	Optional<Categoria> findById(Integer id);
	

}
