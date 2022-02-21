package com.fandeni.pastaandbastaBE.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String nome; 
	private String cognome; 
	private String username; 
	private String email; 
	private LocalDate dob; 
	private String password; 
	private String biografia; 
	@OneToMany(mappedBy = "author")
	private List<Commento> commenti; 
	@ManyToMany
	@JoinTable(name = "like_post", joinColumns = @JoinColumn(name = "id_utente"), inverseJoinColumns = @JoinColumn(name = "id_post"))
	private List<Post> like; 
	@ManyToMany
	@JoinTable(name = "unlike_post", joinColumns = @JoinColumn(name = "id_utente"), inverseJoinColumns = @JoinColumn(name = "id_post"))
	private List<Post> unlike; 
	@ManyToOne
	@JoinColumn(name = "id_ruolo")
	private Ruolo role;

	public Utente(Integer id, String nome, String cognome, String username, String email, LocalDate dob,
			String password, String biografia, Ruolo role) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.dob = dob;
		this.password = password;
		this.biografia = biografia;
		this.role = role;
	} 
	
	public Utente() {}
	
	public Utente(String nome, String cognome, String username, String email, LocalDate dob,
			String password, String biografia, Ruolo role) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.dob = dob;
		this.password = password;
		this.biografia = biografia;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Ruolo getRole() {
		return role;
	}

	public void setAdmin(Ruolo role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Utente utente = (Utente) o;
		return id.equals(utente.id) && nome.equals(utente.nome) && cognome.equals(utente.cognome) && username.equals(utente.username) && email.equals(utente.email) && dob.equals(utente.dob) && password.equals(utente.password) && biografia.equals(utente.biografia) && commenti.equals(utente.commenti) && Objects.equals(like, utente.like) && Objects.equals(unlike, utente.unlike) && role.equals(utente.role);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, cognome, username, email, dob, password, biografia, commenti, like, unlike, role);
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", email="
				+ email + ", dob=" + dob + ", password=" + password + ", biografia=" + biografia + ", ruolo=" + role
				+ "]";
	}

	
}


















