package com.fandeni.pastaandbastaBE.DTO;

import java.util.List;

public class PostDTO {
    private AuthDTO user;
    private String title;
    private String descrizione;
    private String didascalia;
    private List<String> categorie;
    private String img;

    public PostDTO(AuthDTO user, String title, String descrizione, String didascalia, List<String> categorie, String img) {
        this.user = user;
        this.title = title;
        this.descrizione = descrizione;
        this.didascalia = didascalia;
        this.categorie = categorie;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public AuthDTO getUser() {
        return user;
    }

    public void setUser(AuthDTO user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<String> categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "user=" + user +
                ", title='" + title + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", didascalia='" + didascalia + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
