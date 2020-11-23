package model;

import database.Constants;

import java.util.ArrayList;
import java.util.List;

public class Role {
    @Override
    public String toString() {
        return
                " Role= " + role+"   Rights=  "+rights ;
    }

    private Long id;
    private String role;
    private List<Right> rights;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
}