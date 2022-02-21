package com.fandeni.pastaandbastaBE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fandeni.pastaandbastaBE.model.Post;

@Repository
public interface PostCrudRepository extends CrudRepository<Post, Integer> {
	
	Optional<Post> findByDescrizione(String descrizione); 
	Optional<Post> findById(Integer id); 
	@Query("SELECT p FROM Post p JOIN p.categorie c WHERE c.descrizione = ?1") 
	List<Post> findByCategoriaDesc(String catDesc); 
	@Query("SELECT p FROM Post p JOIN p.categorie c WHERE c.id = ?1") 
	List<Post> findByCategoriaId(Integer catId); 
	@Query("SELECT p FROM Post p JOIN p.hashtags h WHERE h.descrizione = ?1")
	List<Post> findByHashDesc(String hashDesc); 
	@Query("SELECT p FROM Post p JOIN p.hashtags h WHERE h.id = ?1")
	List<Post> findByHashId(Integer hashId);
	//commen
}
