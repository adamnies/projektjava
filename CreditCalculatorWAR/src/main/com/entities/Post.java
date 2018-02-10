package main.com.entities;

import main.com.classes.Date;

public class Post {
    public Integer id;
    public Integer authorId;
    public Integer profileUserId;
    public String text;
    public String image;
    public Date data;

    public Post() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getProfileUserId() {
        return profileUserId;
    }
    public void setProfileUserId(Integer profileUserId) {
        this.profileUserId = profileUserId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
