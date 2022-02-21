package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fandeni.pastaandbastaBE.repository.PostCrudRepository;

import java.util.List;

@Service("post_service")
@Transactional
public class PostService {

	@Autowired
	private PostCrudRepository postCrudRepository;

	public List<Post> getDashboard(){
		return postCrudRepository.getDashboardOrderByDataLastUpdate();
	}
	//ricerca ordinata per data
	//ricerca ordinata per numero di like  /  dislike
	//creazione hashtag a partire dalla didascalia
	//create post: "inserimento categorie e didascalia"
	//delete & update post 
	//controlli di auth
	

}
