package com.fandeni.pastaandbastaBE.DTO;

import com.fandeni.pastaandbastaBE.model.Categoria;
import com.fandeni.pastaandbastaBE.model.Commento;
import com.fandeni.pastaandbastaBE.model.Hashtag;
import com.fandeni.pastaandbastaBE.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostVisualizationDTO {
    private Integer idPost;
    private String usernameCreator;
    private String profileImg;
    private String imgPost;
    private String descrizione;
    private String didascalia;
    private LocalDateTime dataPublish;
    private LocalDateTime dataLastUpdate;
    private Integer numLike;
    private Integer numUnlike;
    private List<CommentDTO> comments;
    private List<String> categories;
    private List<String> hashtags;

    public PostVisualizationDTO(Post post){
        idPost = post.getId();
        usernameCreator = post.getCreator().getUsername();
        profileImg = post.getCreator().getDidascalia();
        imgPost = post.getDidascalia();
        descrizione = post.getDescrizione();
        didascalia = post.getDidascalia();
        dataPublish = post.getDataPublish();
        dataLastUpdate = post.getDataLastUpdate();
        numLike = post.getNumLike();
        numUnlike = post.getNumUnlike();
        categories = new ArrayList<>();
        hashtags = new ArrayList<>();
        comments = new ArrayList<>();
        for(Categoria c: post.getCategorie()){
            categories.add(c.getDescrizione());
        }
        for(Hashtag h: post.getHashtags()){
            hashtags.add(h.getDescrizione());
        }
        for (Commento c: post.getComments()) {
            comments.add(new CommentDTO(c.getText(), c.getAuthor().getUsername(), c.getAuthor().getDidascalia(), c.getData()));
        }
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getUsernameCreator() {
        return usernameCreator;
    }

    public void setUsernameCreator(String usernameCreator) {
        this.usernameCreator = usernameCreator;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getImgPost() {
        return imgPost;
    }

    public void setImgPost(String imgPost) {
        this.imgPost = imgPost;
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

    public LocalDateTime getDataLastUpdate() {
        return dataLastUpdate;
    }

    public void setDataLastUpdate(LocalDateTime dataLastUpdate) {
        this.dataLastUpdate = dataLastUpdate;
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<Categoria> categories) {
        for(Categoria c: categories){
            this.categories.add(c.getDescrizione());
        }
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        for(Hashtag h: hashtags){
            this.hashtags.add(h.getDescrizione());
        }
    }

    @Override
    public String toString() {
        return "PostVisualizationDTO{" +
                "idPost" + idPost +
                "usernameCreator='" + usernameCreator + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", imgPost='" + imgPost + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", didascalia='" + didascalia + '\'' +
                ", dataPublish=" + dataPublish +
                ", dataLastUpdate=" + dataLastUpdate +
                ", numLike=" + numLike +
                ", numUnlike=" + numUnlike +
                ", comments=" + comments +
                ", categories=" + categories +
                ", hashtags=" + hashtags +
                '}';
    }
}
