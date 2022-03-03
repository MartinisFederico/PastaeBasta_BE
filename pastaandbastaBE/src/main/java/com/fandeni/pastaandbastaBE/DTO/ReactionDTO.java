package com.fandeni.pastaandbastaBE.DTO;

public class ReactionDTO {
    private AuthDTO user;
    private String type;
    private Integer idPost;

    public ReactionDTO(AuthDTO user, String type, Integer idPost) {
        this.user = user;
        this.type = type;
        this.idPost = idPost;
    }

    public AuthDTO getUser() {
        return user;
    }

    public void setUser(AuthDTO user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    @Override
    public String toString() {
        return "ReactionDTO{" +
                "user=" + user +
                ", type='" + type + '\'' +
                "idPost=" + idPost + '}';
    }
}
