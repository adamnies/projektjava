package main.com.controllers;

import bmroz.calc.dao.UserDAO;
import bmroz.calc.entities.UserEntity;
import main.com.services.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
public class LoginBB {
    private UserEntity user = new UserEntity();

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

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void setTextAccountErr(ResourceBundle textAccountErr) {
        this.textAccountErr = textAccountErr;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String signIn() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        List<UserEntity> lista = this.userDAO.getUsersListWhereEmailAndPassword(user.getEmail(), user.getPassword());
        if (lista.size() == 1) {
//            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    textAccountErr.getString("loginSuccess"), null));

            System.out.println("Zalogowano");
            this.sessionService.currentUser = lista.get(0);
            this.sessionService.currentProfile = lista.get(0);
            System.out.println(this.sessionService.getCurrentUser().getEmail());
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", lista.get(0));
            return "goprofile";
        } else {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    textAccountErrManual.getString("emailOrPasswordIncorrect"), null));
            return null;
        }
    }

    public String logout() {
        this.sessionService.currentUser = null;
        System.out.println("logout");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                textAccountErrManual.getString("password"), null));
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("user");
        return "index";
    }

}
