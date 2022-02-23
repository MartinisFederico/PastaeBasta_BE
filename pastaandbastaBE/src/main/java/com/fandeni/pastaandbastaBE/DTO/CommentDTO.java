package com.fandeni.pastaandbastaBE.DTO;

import com.fandeni.pastaandbastaBE.model.Commento;

import java.time.LocalDateTime;

public class CommentDTO {
    private String text;
    private String usernameAuthor;
    private String urlProfileImgAuthor;
    private LocalDateTime data;

    public CommentDTO(String text, String usernameAuthor, String urlProfileImgAuthor, LocalDateTime data) {
        this.text = text;
        this.usernameAuthor = usernameAuthor;
        this.urlProfileImgAuthor = urlProfileImgAuthor;
        this.data = data;
    }

    public CommentDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsernameAuthor() {
        return usernameAuthor;
    }

    public void setUsernameAuthor(String usernameAuthor) {
        this.usernameAuthor = usernameAuthor;
    }

    public String getUrlProfileImgAuthor() {
        return urlProfileImgAuthor;
    }

    public void setUrlProfileImgAuthor(String urlProfileImgAuthor) {
        this.urlProfileImgAuthor = urlProfileImgAuthor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "text='" + text + '\'' +
                ", usernameAuthor='" + usernameAuthor + '\'' +
                ", urlProfileImgAuthor='" + urlProfileImgAuthor + '\'' +
                ", data=" + data +
                '}';
    }
}
