package model.builder;

import database.Constants;
import model.Right;
import model.Role;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setDateofLogOut(Date date) {
        user.setDateLogOut(date);
        return this;
    }
    public UserBuilder setDateofAcces(Date date ) {
        user.setDateAccess(date);
        return this;
    }
    public UserBuilder setRoles(List<Role> roles) {
        user.setRoles(roles);
        return this;
    }

    public User build() {
        return user;
    }

}