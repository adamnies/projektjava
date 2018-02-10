package main.com.controllers;

import bmroz.calc.dao.PostDAO;
import bmroz.calc.entities.PostEntity;
import bmroz.calc.entities.UserEntity;
import main.com.services.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;


@ManagedBean
@ViewScoped
public class ProfilePostsBB {
    public String newPostImage;
    public String newPostText;
    public List<PostEntity> postsList;

    private PostEntity post = new PostEntity();

    @EJB
    PostDAO postDAO;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;


    public ProfilePostsBB() {
    }

    @PostConstruct
    public void postConstruct() {
        FacesContext context = FacesContext.getCurrentInstance();
        textAccountErrManual = ResourceBundle.getBundle("textAccountErr",
                context.getViewRoot().getLocale());

        this.newPostImage = this.sessionService.currentUser.getProfileImage();
        this.newPostText = "";
        this.postsList = this.postDAO.getPostsWhereOwnerId(this.sessionService.currentUser.getIdUser());
    }

    @ManagedProperty("#{textAccountErr}")
    private ResourceBundle textAccountErr;
    private ResourceBundle textAccountErrManual;

    public ResourceBundle getTextAccountErr() {
        return textAccountErr;
    }
    public void setTextAccountErr(ResourceBundle textAccountErr) {
        this.textAccountErr = textAccountErr;
    }

    public ResourceBundle getTextAccountErrManual() {
        return textAccountErrManual;
    }
    public void setTextAccountErrManual(ResourceBundle textAccountErrManual) {
        this.textAccountErrManual = textAccountErrManual;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public String getNewPostImage() {
        return newPostImage;
    }

    public void setNewPostImage(String newPostImage) {
        this.newPostImage = newPostImage;
    }

    public String getNewPostText() {
        return newPostText;
    }

    public void setNewPostText(String newPostText) {
        this.newPostText = newPostText;
    }

    public List<PostEntity> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<PostEntity> postsList) {
        this.postsList = postsList;
    }
}


