package controller;

import model.Right;
import model.Role;
import model.validation.Notification;
import repository.user.AuthenticationException;
import service.account.AccountService;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.AdminandEmployeeView;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import static database.Constants.Roles.EMPLOYEE;

public class EmployeeController {

    private final AdminandEmployeeView adminView;


    public EmployeeController(Map<Right, Boolean> map, ClientService clientService, AccountService accountService) {
        this.adminView = new AdminandEmployeeView(map);
        adminView.setCreateAccountButtonListener(new CreateAccountButtonListener());
       // adminView.setViewAccountButtonListener(new ViewAccountButtonListener(accountService));
        adminView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
        adminView.setDeleteAccountButtonListener(new DeleteAccountButtonListener());
        adminView.setViewClientButtonListener(new ViewClientButtonListener());
        adminView.setUpdateClientButtonListener(new UpdateClientButtonListener());
        adminView.setCreateClientButtonListener(new CreateClientButtonListener(clientService));
        adminView.setTransferMoneyAmountButtonListener(new TransferMoneyAmount());
        adminView.setProcessBillsButtonListener(new ProcessBills());
    }
    private class CreateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            String type;
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("New Identity Card Number");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Username", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                String[] options = new String[]{"Debit", "Credit"};
                int response = JOptionPane.showOptionDialog(null, "Type of Card ", "Title",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 0) {
                    type = "Debit";
                } else {
                    type = "Credit";
                }
                String[] button1 = {"OK"};
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Personal Numerical Code:");
                JTextField txt1 = new JTextField(20);
                panel.add(label);
                panel.add(txt1);



            }
        }
    }
 /*   private class ViewAccountButtonListener implements ActionListener {
        private final AccountService accountService;
        private ViewAccountButtonListener(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           Notification<Long> findIdentificationNumber = null;
            String input = JOptionPane.showInputDialog("Identification Number", JOptionPane.OK_CANCEL_OPTION);

            try {
                findIdentificationNumber = accountService.getAccountRepository().findByIdentificationNumber(input);
                roles = accountService.getRightsRolesRepository().findRolesForUser(findUsername.getResult());

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
    }*/
    private class UpdateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    private class DeleteAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    private class ViewClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    private class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }
    }
    private class CreateClientButtonListener implements ActionListener {
        private final ClientService clientService;

        public CreateClientButtonListener(ClientService clientService) {
            this.clientService = clientService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            String name;
            String cnp;
            String idenNumber;
            String adress;;
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("New Client Name:");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Client Name", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                name = txt.getText();
                String[] button1 = {"OK"};
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Personal Numerical Code:");
                JTextField txt1 = new JTextField(20);
                panel.add(label);
                panel.add(txt1);
                int SelectedOption1 = JOptionPane.showOptionDialog(null, panel, "CNP", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button1, button1[0]);
                if (SelectedOption1 == 0) {
                    cnp = txt1.getText();
                    String[] button2 = {"OK"};
                    JPanel panel1 = new JPanel();
                    JLabel label1 = new JLabel("Enter Adress:");
                    JTextField txt2 = new JTextField(20);
                    panel1.add(label1);
                    panel1.add(txt2);
                    int SelectedOption2 = JOptionPane.showOptionDialog(null, panel1, "Adress", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button2, button2[0]);
                    if (SelectedOption2 == 0) {
                        adress = txt2.getText();
                        String[] button3 = {"OK"};
                        JPanel panel2 = new JPanel();
                        JLabel label2 = new JLabel("Enter Identification Number:");
                        JTextField txt3 = new JTextField(20);
                        panel2.add(label2);
                        panel2.add(txt3);
                        int SelectedOption3 = JOptionPane.showOptionDialog(null, panel2, "INumber", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button3, button3[0]);
                        if (SelectedOption3 == 0) {
                            idenNumber = txt3.getText();
                            Notification<Boolean> clientNotification = clientService.save(name, cnp, idenNumber, adress);
                            if (clientNotification.hasErrors()) {
                                JOptionPane.showMessageDialog(adminView.getContentPane(), clientNotification.getFormattedErrors());
                            } else {
                                if (!clientNotification.getResult()) {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration Client not successful, please try again later.");
                                } else {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration Client successful!");

                                }
                            }


                        }

                    }
                }


            }
        }
    }

        private class TransferMoneyAmount implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {


            }
        }

        private class ProcessBills implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {


            }
        }
    }


