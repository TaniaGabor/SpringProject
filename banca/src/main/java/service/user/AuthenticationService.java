package service.user;

import model.User;
import model.validation.Notification;
import repository.security.RightsRolesRepository;
import repository.user.AuthenticationException;
import repository.user.UserRepository;

public interface AuthenticationService {

    Notification<Boolean> register(String username, String password,String role);


    //Authentificare(login = username passwrod) !=  Autorizare(privilegiile = rolulul userului)
    boolean delete(User user);
    Notification<User> login(String username, String password) throws AuthenticationException;
    UserRepository getUserRepository();
    RightsRolesRepository getRightsRolesRepository();
    boolean logout(User user);

}