package com.fandeni.pastaandbastaBE.service;

import com.fandeni.pastaandbastaBE.DTO.*;
import com.fandeni.pastaandbastaBE.customException.CategoriaNotFoundException;
import com.fandeni.pastaandbastaBE.customException.PostNotFoundException;
import com.fandeni.pastaandbastaBE.customException.ReactionDoesntExistsException;
import com.fandeni.pastaandbastaBE.model.Categoria;
import com.fandeni.pastaandbastaBE.model.Hashtag;
import com.fandeni.pastaandbastaBE.model.Post;
import com.fandeni.pastaandbastaBE.repository.CategoriaCrudRepository;
import com.fandeni.pastaandbastaBE.repository.HashtagCrudRepository;
import com.fandeni.pastaandbastaBE.repository.UtenteCrudRepository;
import com.fandeni.pastaandbastaBE.utlis.MyUtils;
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
							:utenteCrudRepository.findByEmail(post.getUser().getUsername()).get(),
					post.getImg()
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

	public List<PostVisualizationDTO> searchProfile(SearchDTO searchParameter) throws Exception{
		try {
			String usernameToSearch = (searchParameter.getUsernameToSearch().isEmpty())
					? searchParameter.getUser().getUsername()
					: searchParameter.getUsernameToSearch();
			List<Post> posts;
			posts = (usernameToSearch.matches("^(.+)@(.+)$"))
					? postCrudRepository.getProfileDashboardEmail(usernameToSearch)
					: postCrudRepository.getProfileDashboard(usernameToSearch);
			return MyUtils.preparePostVisualization(posts);
		}catch (Exception e){
			throw e;
		}
	}

	public List<PostVisualizationDTO> searchByCategoria(List<String> categorie) throws CategoriaNotFoundException {
		try{
			List<Post> dashboard = new ArrayList<>();
			List<Post> tempResult;
			Optional<Categoria> temp;
			for(String c : categorie){
				temp = categoriaCrudRepository.findByDescrizione(c);
				if(temp.isPresent()){
					tempResult = postCrudRepository.getPostByCategorie(temp.get());
					for(Post p : tempResult){
						if(!dashboard.contains(p))
							dashboard.add(p);
					}
				}else {
					throw new CategoriaNotFoundException("La categoria non esiste!");
				}
			}
			return MyUtils.preparePostVisualization(dashboard);
		}catch(Exception e){
			throw e;
		}
	}

	public List<PostVisualizationDTO> searchByInterval(LocalDateTime start, LocalDateTime end) {
		try{
			return MyUtils.preparePostVisualization(postCrudRepository.getPostByInterval(start, end));
		}catch (Exception e){
			throw e;
		}
	}

	public List<PostVisualizationDTO> searchByKeyword(List<String> categorie) {
		try{
			List<Post> temp;
			List<Post> dashboard = new ArrayList<>();
			for(String s: categorie){
				temp = postCrudRepository.getPostByKeyword(s);
				for(Post p : temp){
					if(!dashboard.contains(p))
						dashboard.add(p);
				}
			}
			return MyUtils.preparePostVisualization(dashboard);
		}catch (Exception e){
			throw e;
		}
	}

	public void react(ReactionDTO reaction) throws ReactionDoesntExistsException {
		Optional<Post> p = postCrudRepository.findById(reaction.getIdPost());
		if(p.isPresent()){
			Post temp = p.get();
			switch (reaction.getType()){
				case "like":
					temp.getLikers().add((reaction.getUser().getUsername().matches("^(.+)@(.+)$"))
							? utenteCrudRepository.findByEmail(reaction.getUser().getUsername()).get()
							: utenteCrudRepository.findByUsername(reaction.getUser().getUsername()).get());
					temp.setNumLike(temp.getNumLike()+1);
					break;
				case "unlike":
					temp.getUnlikers().add((reaction.getUser().getUsername().matches("^(.+)@(.+)$"))
							? utenteCrudRepository.findByEmail(reaction.getUser().getUsername()).get()
							: utenteCrudRepository.findByUsername(reaction.getUser().getUsername()).get());
					temp.setNumUnlike(temp.getNumUnlike()+1);
					break;
				default:
					throw new ReactionDoesntExistsException("Non esiste la reazione selezionata!");
			}
			postCrudRepository.save(temp);
		}
	}

	public PostVisualizationDTO editPost(PostVisualizationDTO post) throws PostNotFoundException {
		try{
			if(post == null)
				return null;
			Optional<Post> dbPostOpt = postCrudRepository.findById(post.getIdPost());
			if(dbPostOpt.isPresent()){
				Post dbPost = dbPostOpt.get();
				if(!post.getDescrizione().equals(dbPost.getDescrizione()))
					dbPost.setDescrizione(post.getDescrizione());
				if(!post.getDidascalia().equals(dbPost.getDidascalia()))
					dbPost.setDidascalia(post.getDidascalia());
				if(!post.getImgPost().equals(dbPost.getImg()))
					dbPost.setImg(post.getImgPost());
				postCrudRepository.save(dbPost);
				return MyUtils.preparePostVisualization(dbPost);
			}else{
				throw new PostNotFoundException("Il post non esiste!");
			}
		}catch(Exception e){
			throw e;
		}
	}

	public void removePost(PostVisualizationDTO post) throws PostNotFoundException {
		try{
			if(post == null)
				throw new NullPointerException();
			Optional<Post> dbPostOpt = postCrudRepository.findById(post.getIdPost());
			if(dbPostOpt.isPresent()){
				Post dbPost = dbPostOpt.get();
				dbPost.setVisible(false);
				postCrudRepository.save(dbPost);
			}else{
				throw new PostNotFoundException("Il post non esiste!");
			}
		}catch(Exception e){
			throw e;
		}
	}
}
