package main.com.controllers;


import bmroz.calc.dao.UserDAO;
import bmroz.calc.entities.UserEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
public class RegisterBB {
    private UserEntity user = new UserEntity();

    @EJB
    private UserDAO userDAO;

    @ManagedProperty("#{textAccountErr}")
    private ResourceBundle textAccountErr;
    private ResourceBundle textAccountErrManual;

    @PostConstruct
    public void postConstruct() {
        FacesContext context = FacesContext.getCurrentInstance();
        textAccountErrManual = ResourceBundle.getBundle("textAccountErr",
                context.getViewRoot().getLocale());
    }

    public void setTextAccountErr(ResourceBundle textAccountErr) {
        this.textAccountErr = textAccountErr;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String signUp() {
        this.user.setProfileImage("http://s3.amazonaws.com/37assets/svn/765-default-avatar.png");
        this.user.setDescription("Opis");
        this.user.setCity("Miasto");

        FacesContext ctx = FacesContext.getCurrentInstance();
        List<UserEntity> lista = this.userDAO.getUsersListWhereEmail(user.getEmail());
        if (lista.size() > 0) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    textAccountErr.getString("accountExist"), null));
            return null;
        } else {
            this.userDAO.create(user);
            System.out.println(user.toString());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    textAccountErrManual.getString("accountCreated"), null));
            return "index";
        }
    }
}
