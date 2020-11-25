package controller;

import database.Constants;
import model.Right;
import model.Role;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.account.AccountService;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static database.Constants.Rights.VIEW_USER;
import static database.Constants.Roles.*;

public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final ClientService clientService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService,AccountService accountService, ClientService clientService) {
        this.loginView = loginView;
        this.accountService=accountService;
        this.clientService=clientService;
        this.authenticationService = authenticationService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Map<Right, Boolean> map = new LinkedHashMap<Right, Boolean>();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");

                for (Role role : loginNotification.getResult().getRoles()) {
                    for (Right right : role.getRights()) {
                        map.put(right, true);
                    }

                    if (role.getRole().equals(ADMINISTRATOR)) {
                        AdministratorController adminController = new AdministratorController(map, authenticationService);

                    } else if (role.getRole().equals(EMPLOYEE)) {
                        EmployeeController employeeController = new EmployeeController(map,clientService,accountService, loginNotification.getResult(),authenticationService);
                        try {
                            authenticationService.getUserRepository().setDateofAcces(loginNotification.getResult());
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password, EMPLOYEE);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {

                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");


                }
            }
        }
    }
}


