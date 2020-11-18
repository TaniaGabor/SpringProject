package service.user;

import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;

public interface AuthenticationService {

    Notification<Boolean> register(String username, String password,String role);


    //Authentificare(login = username passwrod) !=  Autorizare(privilegiile = rolulul userului)

    Notification<User> login(String username, String password) throws AuthenticationException;

    boolean logout(User user);

}