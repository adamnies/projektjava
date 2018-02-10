package main.com.controllers;


import bmroz.calc.dao.PostDAO;
import bmroz.calc.dao.UserDAO;
import bmroz.calc.entities.UserEntity;
import main.com.services.SessionService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;

@ManagedBean
@RequestScoped
public class ProfilesBB {
    public String search;
    public String searchParam;

    @EJB
    PostDAO postDAO;

    @EJB
    UserDAO userDAO;
    @ManagedProperty(value = "#{sessionService}")
    private SessionService sessionService;
    @ManagedProperty("#{textAccountErr}")
    private ResourceBundle textAccountErr;
    private ResourceBundle textAccountErrManual;

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }

    public SessionService getSessionService() {
        return sessionService;
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


    @PostConstruct
    public void dupa() {
        System.out.println("eleoeleoeleoeleoeleoeleo");
        FacesContext context = FacesContext.getCurrentInstance();
        textAccountErrManual = ResourceBundle.getBundle("textAccountErr", context.getViewRoot().getLocale());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        String id = request.getParameter("search");
        System.out.println(this.search);
        System.out.println(id);
        System.out.println(this.searchParam);

        if (id != null) {
            System.out.println("id" + id);
            this.search = id;
            this.searchUser();
        }
    }

    public String getSearch() {
        return search;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String searchUser() {
        System.out.println("szukam: " + search);
        System.out.println("szukam2: " + searchParam);
        if (this.searchParam != null) {
            this.search = this.searchParam;
        }

        System.out.println("szukam: " + search);
        System.out.println("szukam2: " + searchParam);

        List<UserEntity> list = this.userDAO.getUsersListWhereUsername(this.search);
        if (list.size() == 0) {
            list = this.userDAO.getUsersListWhereEmail(this.search);
        }
        if (list.size() == 0) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, textAccountErr.getString("notfoundProfile"), null));
            return "indexowe";
        }

        System.out.println(list.size());
        this.sessionService.currentProfile = list.get(0);
        this.search = "";
        this.searchParam = null;
        return "profile.xhtml?faces-redirect=true";
    }

    public String showCurrentUserProfile() {
        this.sessionService.currentProfile = this.sessionService.currentUser;
        return "goprofile";
    }
}
