package main.com.services;


import bmroz.calc.entities.UserEntity;
import main.com.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class SessionService implements Serializable {

    public UserEntity currentUser;
    public String userName;
    public String userPhoto;
    public String userProfileId;
    public UserEntity currentProfile;

    public UserEntity getCurrentProfile() {
        return currentProfile;
    }
    public void setCurrentProfile(UserEntity currentProfile) {
        this.currentProfile = currentProfile;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserProfileId() {
        return userProfileId;
    }
    public void setUserProfileId(String userProfileId) {
        this.userProfileId = userProfileId;
    }

    public SessionService() {
    }

}
