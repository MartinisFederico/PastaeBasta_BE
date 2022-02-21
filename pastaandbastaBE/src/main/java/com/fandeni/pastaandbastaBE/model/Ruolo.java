package com.fandeni.pastaandbastaBE.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descrizione;
    @OneToMany(mappedBy = "role")
    private List<Utente> users;

    public Ruolo(Integer id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }

    public Ruolo(){}

    public Ruolo(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Utente> getUsers() {
        return users;
    }

    public void setUsers(List<Utente> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruolo ruolo = (Ruolo) o;
        return Objects.equals(id, ruolo.id) && Objects.equals(descrizione, ruolo.descrizione) && Objects.equals(users, ruolo.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descrizione, users);
    }

}
