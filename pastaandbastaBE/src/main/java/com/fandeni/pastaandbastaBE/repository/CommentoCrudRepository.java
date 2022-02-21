package com.fandeni.pastaandbastaBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.fandeni.pastaandbastaBE.model.Commento;

@Repository
public interface CommentoCrudRepository extends CrudRepository<Commento, Integer> {
	
	Optional<Commento> findByText(String text); 
	Optional<Commento> findById(Integer id); 
	@Query("SELECT c FROM Commento c JOIN Post p WHERE p.id = ?1")
	List<Commento> findByPost(Integer id); 

}
