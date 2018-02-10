package main.com.controllers;

import bmroz.calc.dao.PostDAO;
import bmroz.calc.dao.UserDAO;
import bmroz.calc.entities.PostEntity;
import bmroz.calc.entities.UserEntity;
import main.com.services.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
@ViewScoped
public class ProfileAboutBB {

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;
    public String newPostImage;
    public String newPostText;
    public Collection<PostEntity> postsList;
    private PostEntity post = new PostEntity();
    private UserEntity user = new UserEntity();
    @ManagedProperty("#{textAccountErr}")
    private ResourceBundle textAccountErr;
    private ResourceBundle textAccountErrManual;
    @EJB
    PostDAO postDAO;

    @EJB
    UserDAO userDAO;

    public Boolean renderTextarea;
    public Boolean renderInput;
    public String userPhoto;
    public String author;
    public String email;
    public Integer postsCount;
    public Integer subscribersCount;
    public String description;
    public String city;
    public Boolean showChangePhotoInput = false;

    public ProfileAboutBB() {
    }


    public Boolean getShowChangePhotoInput() {
        return showChangePhotoInput;
    }

    public void setShowChangePhotoInput(Boolean showChangePhotoInput) {
        this.showChangePhotoInput = showChangePhotoInput;
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

    public Collection<PostEntity> getPostsList() {
        return postsList;
    }

    public void setPostsList(Collection<PostEntity> postsList) {
        this.postsList = postsList;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

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

    public PostDAO getPostDAO() {
        return postDAO;
    }

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public Boolean getRenderTextarea() {
        return renderTextarea;
    }

    public void setRenderTextarea(Boolean renderTextarea) {
        this.renderTextarea = renderTextarea;
    }

    public Boolean getRenderInput() {
        return renderInput;
    }

    public void setRenderInput(Boolean renderInput) {
        this.renderInput = renderInput;
    }

    public Integer getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    public Integer getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(Integer subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostConstruct
    public void assignData() {
        System.out.println("nowedobreasdupesadjasdifakjdhsflkjsadhflkjhsdlfkjhe");
        FacesContext context = FacesContext.getCurrentInstance();
        textAccountErrManual = ResourceBundle.getBundle("textAccountErr", context.getViewRoot().getLocale());

        this.user = this.sessionService.currentProfile;
        this.user = this.userDAO.find(this.sessionService.currentProfile.getIdUser());
        System.out.println(this.user.getIdUser() + "dupa");

        this.newPostImage = this.user.getProfileImage();
        this.newPostText = "";
        this.postsList = this.user.getPostsWhereUserIsOwner();
        this.renderTextarea = false;
        this.renderInput = false;
        this.postsCount = this.user.getPostsWhereUserIsOwner().size();
        this.subscribersCount = 11;
        this.author = this.user.getUsername();
        this.description = this.user.getDescription();
        this.email = this.user.getEmail();
        this.city = this.user.getCity();
        this.userPhoto = this.user.getProfileImage();
    }

    public void editDescription() {
        this.renderTextarea = !this.renderTextarea;
        System.out.println(this.renderTextarea);
    }

    public void editCity() {
        this.renderInput = !this.renderInput;
        System.out.println(this.renderInput);
    }

    public String saveDescription() {
        UserEntity userUpdate = this.sessionService.getCurrentUser();
        userUpdate.setDescription(this.description);
        this.userDAO.merge(userUpdate);
        this.renderTextarea = !this.renderTextarea;
        this.assignData();
        return null;
    }

    public String saveCity() {
        UserEntity userUpdate = this.sessionService.getCurrentUser();
        userUpdate.setCity(this.city);
        this.userDAO.merge(userUpdate);
        System.out.println(this.city);
        this.renderInput = !this.renderInput;
        this.assignData();
        return null;
    }

    public String addPost() {
        System.out.println(this.sessionService.currentUser.getIdUser());
        System.out.println(this.sessionService.currentProfile.getIdUser());
        System.out.println("szzz");
        this.post.setUserByOwnerId(this.sessionService.currentProfile);
        this.post.setUserByAuthorId(this.sessionService.currentUser);
        this.post.setData(new Timestamp(System.currentTimeMillis()));
        if (this.post.getDescription().equals("")) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, textAccountErr.getString("emptyPost"), null));
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, textAccountErr.getString("addedPost"), null));
            this.postDAO.create(this.post);

        }
        this.post.setDescription("");
        this.postsList = this.postDAO.getPostsWhereOwnerId(this.sessionService.currentProfile.getIdUser());
        this.postsCount = this.postsList.size();
        return null;
    }

    public String displayChangePhotoInput() {
        System.out.println(this.showChangePhotoInput);
        this.showChangePhotoInput = !this.showChangePhotoInput;
        System.out.println(this.showChangePhotoInput);
        System.out.println("elo");
        return null;
    }

    public String changePhotoUrl() {
        UserEntity userUpdate = this.sessionService.currentProfile;
        userUpdate.setProfileImage(this.userPhoto);
        this.userDAO.merge(userUpdate);
        this.showChangePhotoInput = false;
        this.user = this.userDAO.find(this.sessionService.currentProfile.getIdUser());
        this.sessionService.currentProfile = this.userDAO.find(this.sessionService.currentProfile.getIdUser());
        this.postsList = this.postDAO.getPostsWhereOwnerId(this.sessionService.currentProfile.getIdUser());
        return "goprofile";
    }
}


