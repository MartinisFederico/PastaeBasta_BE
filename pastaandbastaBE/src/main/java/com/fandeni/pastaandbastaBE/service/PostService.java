package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.DTO.PostDTO;
import com.fandeni.pastaandbastaBE.model.Categoria;
import com.fandeni.pastaandbastaBE.model.Hashtag;
import com.fandeni.pastaandbastaBE.model.Post;
import com.fandeni.pastaandbastaBE.repository.CategoriaCrudRepository;
import com.fandeni.pastaandbastaBE.repository.HashtagCrudRepository;
import com.fandeni.pastaandbastaBE.repository.UtenteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fandeni.pastaandbastaBE.repository.PostCrudRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("post_service")
@Transactional
public class PostService {

	@Autowired
	private PostCrudRepository postCrudRepository;

	@Autowired
	private UtenteCrudRepository utenteCrudRepository;

	@Autowired
	private CategoriaCrudRepository categoriaCrudRepository;

	@Autowired
	private HashtagCrudRepository hashtagCrudRepository;

	public List<Post> getDashboard(String user){
		return (user.matches("^(.+)@(.+)$"))
				? postCrudRepository.getDashboardOrderByDataLastUpdateEmail(user)
				: postCrudRepository.getDashboardOrderByDataLastUpdateUsername(user);
	}

	public void addPost(PostDTO post) throws Exception{
		try{
			Post p = new Post(
					post.getDescrizione(),
					post.getDidascalia(),
					LocalDateTime.now(),
					LocalDateTime.now(),
					(post.getUser().getUsername().matches("^(.+)@(.+)$"))
							?utenteCrudRepository.findByUsername(post.getUser().getUsername()).get()
							:utenteCrudRepository.findByEmail(post.getUser().getUsername()).get()
			);
			List<Categoria> categorie = new ArrayList<>();
			Categoria newCategory;
			for (String s : post.getCategorie()){
				Optional<Categoria> c = categoriaCrudRepository.findByDescrizione(s);
//			if(c.isEmpty())
//				newCategory = categoriaCrudRepository.save(new Categoria(s));
//			else
//				newCategory = c.get();
//			categorie.add(newCategory);
				categorie.add(c.get());
			}
			p.setCategorie(categorie);

			String[] hash = post.getDescrizione().split(" ");
			List<Hashtag> hashtags = new ArrayList<>();
			Hashtag newHashtag;
			for(String s: hash){
				if(s.charAt(0)=='#'){
					Optional<Hashtag> h = hashtagCrudRepository.findByDescrizione(s.substring(1));
					if(h.isEmpty())
						newHashtag = hashtagCrudRepository.save(new Hashtag(s.substring(1)));
					else
						newHashtag = h.get();
					hashtags.add(newHashtag);
				}
			}
			p.setHashtags(hashtags);

			postCrudRepository.save(p);
		}catch(Exception e){
			throw e;
		}
	}
	//ricerca ordinata per data
	//ricerca ordinata per numero di like  /  dislike
	//creazione hashtag a partire dalla didascalia
	//create post: "inserimento categorie e didascalia"
	//delete & update post 
	//controlli di auth
	

}
