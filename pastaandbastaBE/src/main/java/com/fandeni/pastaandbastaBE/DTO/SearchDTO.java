package com.fandeni.pastaandbastaBE.DTO;


import java.time.LocalDateTime;
import java.util.List;

public class SearchDTO {
    private AuthDTO user;
    private String usernameToSearch;
    private List<String> categorie;
    private LocalDateTime start, end;

    public SearchDTO(AuthDTO user, String usernameToSearch, List<String> categorie, LocalDateTime start, LocalDateTime end) {
        this.user = user;
        this.usernameToSearch = usernameToSearch;
        this.categorie = categorie;
        this.start = start;
        this.end = end;
    }

    public AuthDTO getUser() {
        return user;
    }

    public void setUser(AuthDTO user) {
        this.user = user;
    }

    public String getUsernameToSearch() {
        return usernameToSearch;
    }

    public void setUsernameToSearch(String usernameToSearch) {
        this.usernameToSearch = usernameToSearch;
    }

    public List<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<String> categorie) {
        this.categorie = categorie;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "user=" + user +
                ", usernameToSearch='" + usernameToSearch + '\'' +
                ", categorie=" + categorie +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
