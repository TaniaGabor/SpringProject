package controller;

import model.Right;
import model.Role;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.user.AuthenticationService;
import view.AdminandEmployeeView;
import view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static database.Constants.Roles.EMPLOYEE;


public class AdministratorController {
    private final AdminandEmployeeView adminView;



    public AdministratorController(Map<Right, Boolean> map, AuthenticationService authenticationService) {

        this.adminView = new AdminandEmployeeView(map);
        adminView.setViewButtonListener(new ViewButtonListener(authenticationService));
        adminView.setCreateButtonListener(new CreateButtonListener(authenticationService));
        adminView.setDeleteButtonListener(new DeleteButtonListener(authenticationService));
        adminView.setActivityButtonListener(new ActivityButtonListener(authenticationService));
        adminView.setUpdateButtonListener(new UpdateButtonListener(authenticationService));
        adminView.setLogOutButtonListener(new LogoutButtonListener());
    }

    private class LogoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }
    }
    private class ViewButtonListener implements ActionListener {
        private final AuthenticationService authenticationService;

        private ViewButtonListener(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Notification<Long> findUsername = null;
            List<Role> roles = null;
            String input = JOptionPane.showInputDialog("Username Employee:", JOptionPane.OK_CANCEL_OPTION);

            try {
                findUsername = authenticationService.getUserRepository().findByUsername(input);
                roles = authenticationService.getRightsRolesRepository().findRolesForUser(findUsername.getResult());

            } catch (AuthenticationException authenticationException) {
                authenticationException.printStackTrace();
            }
            if (findUsername != null) {
                if (findUsername.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findUsername.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User found!");
                    JOptionPane.showMessageDialog(adminView.getContentPane(), roles.toString());
                }


            }
        }
    }

    private class CreateButtonListener implements ActionListener {
        AuthenticationService authenticationService;

        public CreateButtonListener(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("Username New Employee");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Username", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                String username = txt.getText();
                JPanel panel = new JPanel();
                String passwok = null;
                JLabel label = new JLabel("Enter password:");
                JPasswordField pass = new JPasswordField(20);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"Ok", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "The title", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    char[] passw = pass.getPassword();
                    passwok = new String(passw);
                    Notification<Boolean> registerNotification = authenticationService.register(username, passwok, EMPLOYEE);
                    if (registerNotification.hasErrors()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), registerNotification.getFormattedErrors());
                    } else {
                        if (!registerNotification.getResult()) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration not successful, please try again later.");
                        } else {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration successful!");

                        }
                    }
                }
            }
        }
    }


    private class UpdateButtonListener implements ActionListener {
        AuthenticationService authenticationService;

        public UpdateButtonListener(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("Username Employee");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Username", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                String username = txt.getText();
                JPanel panel = new JPanel();
                String passwok = null;
                JLabel label = new JLabel("Enter password:");
                JPasswordField pass = new JPasswordField(20);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"Ok", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "The title", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    char[] passw = pass.getPassword();
                    passwok = new String(passw);
                    Notification<Boolean> modifyNotification = authenticationService.getUserRepository().modifyPassword(username, passwok);
                    if (modifyNotification.hasErrors()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), modifyNotification.getFormattedErrors());
                    } else {
                        if (!modifyNotification.getResult()) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Set password not successful, please try again later.");
                        } else {

                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Update successful!");


                        }
                    }

                }

            }
        }
    }

        private class DeleteButtonListener implements ActionListener {
            AuthenticationService authenticationService;

            public DeleteButtonListener(AuthenticationService authenticationService) {
                this.authenticationService = authenticationService;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                Notification<Long> findUsername = null;
                String input = JOptionPane.showInputDialog("Username Employee:", JOptionPane.OK_CANCEL_OPTION);

                try {
                    findUsername = authenticationService.getUserRepository().deleteByUsername(input);

                } catch (AuthenticationException authenticationException) {
                    authenticationException.printStackTrace();
                }
                if (findUsername != null) {
                    if (findUsername.hasErrors()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), findUsername.getFormattedErrors());
                    } else {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Deleted User!");
                    }


                }
            }


        }


        private class ActivityButtonListener implements ActionListener {
        private final AuthenticationService authenticationService;


            public ActivityButtonListener(AuthenticationService authenticationService) {
                this.authenticationService = authenticationService;

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] button = {"OK"};
                String data;
                Random rand=new Random();
                JPanel firstPanel = new JPanel();
                JLabel lbl = new JLabel("Insert Username");
                JTextField txt = new JTextField(20);
                firstPanel.add(lbl);
                firstPanel.add(txt);
                int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Username", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
                 if (SelectedOption==0)
                 {
                     String text = txt.getText();
                     try {
                         Notification<String>dates= authenticationService.getUserRepository().getwellDate(text);


                         if(dates.getResult()==null)
                         {
                             JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid user!");
                         }
                         else
                         {
                             FileWriter writer= new FileWriter("activity"+rand.nextInt()+".txt");
                             writer.write(text+dates.getResult());
                             writer.close();
                             JOptionPane.showMessageDialog(adminView.getContentPane(),dates.getResult() );
                         }
                     } catch (SQLException | IOException throwables) {
                         throwables.printStackTrace();
                     }


                 }
            }
        }

    }
