package com.fandeni.pastaandbastaBE.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String descrizione; 
	private String didascalia; 
	private LocalDateTime dataPublish;
	private LocalDateTime dataLastUpdate;
	@ManyToMany(mappedBy = "like")
	private List<Utente> likers; 
	@ManyToMany(mappedBy = "unlike")
	private List<Utente> unlikers; 
	private Integer numLike; 
	private Integer numUnlike; 
	@OneToMany(mappedBy = "post")
	private List<Commento> comments; 
	@ManyToOne
	@JoinColumn(name="id_utente")
	private Utente creator;
	@ManyToMany(mappedBy = "post")
	private List<Categoria> categorie; 
	@ManyToMany(mappedBy = "post")
	private List<Hashtag> hashtags; 
	
	
	public Post(Integer id, String descrizione, String didascalia, LocalDateTime dataPublish,
				LocalDateTime dataLastUpdate, Utente creator) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.didascalia = didascalia; 
		this.dataPublish = dataPublish;
		this.likers = new ArrayList<>();
		this.unlikers = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.creator = creator;
		this.dataLastUpdate = dataLastUpdate;
	} 
	
	public Post() {}
	
	public Post(String descrizione, String didascalia, LocalDateTime dataPublish,
				LocalDateTime dataLastUpdate, Utente creator) {
		this.descrizione = descrizione;
		this.didascalia = didascalia; 
		this.dataPublish = dataPublish;
		this.likers = new ArrayList<>();
		this.unlikers = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.creator = creator;
		this.dataLastUpdate = dataLastUpdate;
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
	
	public String getDidascalia() {
		return didascalia;
	}

	public void setDidascalia(String didascalia) {
		this.didascalia = didascalia;
	}

	public LocalDateTime getDataPublish() {
		return dataPublish;
	}

	public void setDataPublish(LocalDateTime dataPublish) {
		this.dataPublish = dataPublish;
	}

	public List<Utente> getLikers() {
		return likers;
	}

	public void setLikers(List<Utente> likers) {
		this.likers = likers;
	}

	public List<Utente> getUnlikers() {
		return unlikers;
	}

	public void setUnlikers(List<Utente> unlikers) {
		this.unlikers = unlikers;
	}

	public Integer getNumLike() {
		return numLike;
	}

	public void setNumLike(Integer numLike) {
		this.numLike = numLike;
	}

	public Integer getNumUnlike() {
		return numUnlike;
	}

	public void setNumUnlike(Integer numUnlike) {
		this.numUnlike = numUnlike;
	}

	public List<Commento> getComments() {
		return comments;
	}

	public void setComments(List<Commento> comments) {
		this.comments = comments;
	}

	public Utente getCreator() {
		return creator;
	}

	public void setCreator(Utente creator) {
		this.creator = creator;
	}

	public LocalDateTime getDataLastUpdate() {
		return dataLastUpdate;
	}

	public void setDataLastUpdate(LocalDateTime dataLastUpdate) {
		this.dataLastUpdate = dataLastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((dataPublish == null) ? 0 : dataPublish.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((likers == null) ? 0 : likers.hashCode());
		result = prime * result + ((numLike == null) ? 0 : numLike.hashCode());
		result = prime * result + ((numUnlike == null) ? 0 : numUnlike.hashCode());
		result = prime * result + ((unlikers == null) ? 0 : unlikers.hashCode());
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
		Post other = (Post) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (dataPublish == null) {
			if (other.dataPublish != null)
				return false;
		} else if (!dataPublish.equals(other.dataPublish))
			return false;
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
		if (likers == null) {
			if (other.likers != null)
				return false;
		} else if (!likers.equals(other.likers))
			return false;
		if (numLike == null) {
			if (other.numLike != null)
				return false;
		} else if (!numLike.equals(other.numLike))
			return false;
		if (numUnlike == null) {
			if (other.numUnlike != null)
				return false;
		} else if (!numUnlike.equals(other.numUnlike))
			return false;
		if (unlikers == null) {
			if (other.unlikers != null)
				return false;
		} else if (!unlikers.equals(other.unlikers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", descrizione=" + descrizione + ", dataPublish=" + dataPublish + ", likers=" + likers
				+ ", unlikers=" + unlikers + ", numLike=" + numLike + ", numUnlike=" + numUnlike + ", comments="
				+ comments + ", creator=" + creator + "]";
	} 


}
	
	
	


























