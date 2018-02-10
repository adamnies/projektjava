package main.com.controllers;


import javax.faces.bean.ManagedBean;

@ManagedBean
public class ProfilesBB {
    public String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String searchUser() {
        System.out.println("szukam" + search);
        return "index";
    }
}
