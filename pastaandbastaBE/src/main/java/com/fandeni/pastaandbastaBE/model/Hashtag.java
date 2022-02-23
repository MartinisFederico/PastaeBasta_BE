package com.fandeni.pastaandbastaBE.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Hashtag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String descrizione; 
	@ManyToMany
	@JoinTable(name = "hashtag_post", joinColumns = @JoinColumn(name = "id_hashtag"), inverseJoinColumns = @JoinColumn(name = "id_post"))
	private List<Post> post;
	
	public Hashtag(Integer id, String descrizione, List<Post> post) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.post = new ArrayList<>();
	}
	
	public Hashtag(String s) {}
	
	public Hashtag(String descrizione, List<Post> post) {
		
		this.descrizione = descrizione;
		this.post = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hashtag other = (Hashtag) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hashtag [id=" + id + ", descrizione=" + descrizione + ", post=" + post + "]";
	}


}
