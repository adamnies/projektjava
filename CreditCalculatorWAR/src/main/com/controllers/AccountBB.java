package main.com.controllers;

import bmroz.calc.dao.UserDAO;
import bmroz.calc.entities.UserEntity;
import main.com.services.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@ManagedBean
@ViewScoped
public class AccountBB {
    UserEntity userEntity = new UserEntity();
    String passwordOld = "";
    String passwordNew = "";
    String passwordConfirm = "";

    @EJB
    UserDAO userDAO;
    @ManagedProperty("#{textAccountErr}")
    private ResourceBundle textAccountErr;
    private ResourceBundle textAccountErrManual;

    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;

    @PostConstruct
    public void postConstruct() {
        FacesContext context = FacesContext.getCurrentInstance();
        textAccountErrManual = ResourceBundle.getBundle("textAccountErr",
                context.getViewRoot().getLocale());
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String changePassword() {
        System.out.println(this.passwordOld);
        System.out.println(this.passwordNew);
        System.out.println(this.passwordConfirm);

        this.userEntity = this.sessionService.currentUser;
        if (this.passwordOld.equals(this.sessionService.currentUser.getPassword())) {
            System.out.println("epass");
            if (this.passwordNew.equals(this.passwordConfirm)) {
                this.userEntity.setPassword(this.passwordNew);
                this.userDAO.merge(userEntity);
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        textAccountErrManual.getString("password"), null));

            }
            return "stronaglowna";
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    textAccountErrManual.getString("passwordChanged"), null));
            return null;
        }
    }
}
