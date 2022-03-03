package com.fandeni.pastaandbastaBE.DTO;

public class PostUpdateDTO {
    private AuthDTO user;
    private PostVisualizationDTO post;

    public PostUpdateDTO(AuthDTO user, PostVisualizationDTO post) {
        this.user = user;
        this.post = post;
    }

    public AuthDTO getUser() {
        return user;
    }

    public void setUser(AuthDTO user) {
        this.user = user;
    }

    public PostVisualizationDTO getPost() {
        return post;
    }

    public void setPost(PostVisualizationDTO post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "PostUpdateDTO{" +
                "user=" + user +
                ", post=" + post +
                '}';
    }
}
