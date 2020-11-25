package controller;

import model.*;
import model.validation.Notification;
import repository.client.ClientException;
import repository.user.AuthenticationException;
import service.account.AccountService;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.AdminandEmployeeView;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static database.Constants.Roles.EMPLOYEE;

public class EmployeeController {

    private final AdminandEmployeeView adminView;
    private final User user;
    private final  AuthenticationService autenticationService;


    public EmployeeController(Map<Right, Boolean> map, ClientService clientService, AccountService accountService,User user,AuthenticationService autenticationService) {
        this.adminView = new AdminandEmployeeView(map);
        this.user=user;
        this.autenticationService=autenticationService;
        adminView.setCreateAccountButtonListener(new CreateAccountButtonListener(accountService,user));
        adminView.setViewAccountButtonListener(new ViewAccountButtonListener(accountService));
        adminView.setUpdateAccountButtonListener(new UpdateAccountButtonListener(accountService));
        adminView.setDeleteAccountButtonListener(new DeleteAccountButtonListener(accountService));
        adminView.setViewClientButtonListener(new ViewClientButtonListener(clientService));
        adminView.setUpdateClientButtonListener(new UpdateClientButtonListener(clientService));
        adminView.setCreateClientButtonListener(new CreateClientButtonListener(clientService));
        adminView.setTransferMoneyAmountButtonListener(new TransferMoneyAmount(accountService));
        adminView.setProcessBillsButtonListener(new ProcessBills(accountService));
        adminView.setLogOutButtonListener(new LogoutButtonListener(user,autenticationService));
    }

    private class LogoutButtonListener implements ActionListener {
        private final AuthenticationService autenticationService;
        private final User user;
        public LogoutButtonListener(User user,AuthenticationService authenticationService) {
            this.autenticationService=authenticationService;
            this.user=user;
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                autenticationService.getUserRepository().setDateofLogOut(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.exit(0);

        }
    }
    private class CreateAccountButtonListener implements ActionListener {
        private final AccountService accountService;
        User user;


        public CreateAccountButtonListener(AccountService accountService,User user) {
            this.accountService = accountService;
            this.user=user;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            String type;
            String cnp;
            Double money;
            String idenNumber;
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("New Identity Card Number");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Identity Card Number", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                idenNumber = txt.getText();
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
                int SelectedOption1 = JOptionPane.showOptionDialog(null, panel, "CNP", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button1, button1[0]);
                if (SelectedOption1 == 0) {
                    cnp = txt1.getText();
                    String[] button2 = {"OK"};
                    JPanel panel1 = new JPanel();
                    JLabel label1 = new JLabel("Amount of Money:");
                    JTextField txt2 = new JTextField(20);
                    panel1.add(label1);
                    panel1.add(txt2);
                    int SelectedOption2 = JOptionPane.showOptionDialog(null, panel1, "Money Amount", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button2, button2[0]);
                    if (SelectedOption2 == 0) {
                        try {
                            money = Double.valueOf(txt2.getText());
                            System.out.println(money);
                            Notification<Boolean> clientNotification = accountService.save(type, cnp, money, idenNumber);
                            if (clientNotification.hasErrors()) {
                                JOptionPane.showMessageDialog(adminView.getContentPane(), clientNotification.getFormattedErrors());
                            } else {
                                if (!clientNotification.getResult()) {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration Account not successful, please try again later.");
                                } else {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration Account successful!");
                                  //  autenticationService.getUserRepository().insertActivity(this.user,"createAccount");

                                }
                            }
                            if (!clientNotification.getResult()) {
                                JOptionPane.showMessageDialog(adminView.getContentPane(), "This personal numerical code doesn't exist");
                            }
                        } catch (Exception p) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Money Amount not Valid!");

                        }


                    }
                }

            }
        }
    }

    private class ViewAccountButtonListener implements ActionListener {
        private final AccountService accountService;

        private ViewAccountButtonListener(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Account> findClient = null;
            String input = JOptionPane.showInputDialog("Identity Card Number:", JOptionPane.OK_CANCEL_OPTION);
            try {
                findClient = accountService.find(input);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (findClient != null) {
                if (findClient.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Account found!");
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getResult().toString());
                }

            } else {
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");

            }


        }
    }

    private class UpdateAccountButtonListener implements ActionListener {
        AccountService accountService;

        public UpdateAccountButtonListener(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("Identity Card Number :");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "ICN", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                String identityCardNumber = txt.getText();
                JPanel panel = new JPanel();
                JLabel label = new JLabel("New Identity Card Number:");
                JTextField txt1 = new JTextField(20);
                panel.add(label);
                panel.add(txt1);
                String[] options = new String[]{"Ok", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, " ICN", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    String newIdentityCardNumber = txt1.getText();
                    Notification<Boolean> modifyNotification = null;
                    modifyNotification = accountService.modifyICN(identityCardNumber, newIdentityCardNumber);
                    if (modifyNotification.hasErrors()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), modifyNotification.getFormattedErrors());
                    } else {
                        if (!modifyNotification.getResult()) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Account update unsuccesful.Please try again later.");
                        } else {

                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Update Account Successful!");


                        }
                    }

                }
            }

        }


    }


    private class DeleteAccountButtonListener implements ActionListener {
        private final AccountService accountService;

        public DeleteAccountButtonListener(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Boolean> findClient = null;
            String input = JOptionPane.showInputDialog("Identity Card Number:", JOptionPane.OK_CANCEL_OPTION);
            try {
                findClient = accountService.delete(input);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (findClient != null) {
                if (findClient.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Account deleted!");
                }

            } else {
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");
            }
        }
    }

    private class ViewClientButtonListener implements ActionListener {
        private final ClientService clientService;

        public ViewClientButtonListener(ClientService clientService) {
            this.clientService = clientService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Client> findClient = null;
            String input = JOptionPane.showInputDialog("Personal Numerical Code:", JOptionPane.OK_CANCEL_OPTION);
            try {
                findClient = clientService.getClientRepository().findbyCNP(input);


            } catch (ClientException authenticationException) {
                authenticationException.printStackTrace();
            }
            if (findClient != null) {
                if (findClient.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Client found!");
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getResult().toString());
                }


            }


        }
    }

    private class UpdateClientButtonListener implements ActionListener {
        private final ClientService clientService;

        public UpdateClientButtonListener(ClientService clientService) {

            this.clientService = clientService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] button = {"OK"};
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("Client Personal Numerical Code :");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "Client CNP", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                String cnp = txt.getText();
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter New Adress:");
                JTextField txt1 = new JTextField(20);
                panel.add(label);
                panel.add(txt1);
                String[] options = new String[]{"Ok", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, " New Adress", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
                if (option == 0) {
                    String adress = txt1.getText();
                    JPanel panel1 = new JPanel();
                    JLabel lbl1 = new JLabel("Enter New Name :");
                    JTextField txt2 = new JTextField(20);
                    panel1.add(lbl1);
                    panel1.add(txt2);
                    String[] options1 = new String[]{"Ok", "Cancel"};
                    int SelectedOption1 = JOptionPane.showOptionDialog(null, panel1, "Client  New Name", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[1]);
                    if (SelectedOption1 == 0) {
                        String newName = txt2.getText();
                        Notification<Boolean> modifyNotification = null;
                        modifyNotification = clientService.modifyNameAdress(cnp, newName, adress);
                        if (modifyNotification.hasErrors()) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), modifyNotification.getFormattedErrors());
                        } else {
                            if (!modifyNotification.getResult()) {
                                JOptionPane.showMessageDialog(adminView.getContentPane(), "Client update unsuccesful.");
                            } else {

                                JOptionPane.showMessageDialog(adminView.getContentPane(), "Update successful!");


                            }
                        }

                    }
                }

            }


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
            String adress;
            ;
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
        private final AccountService accountService;

        public TransferMoneyAmount(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Notification<Account> firstAccount=null;
            Notification<Account> secondAccount=null;
            Notification<Boolean> doneTransfer=null;
            String[] button = {"OK"};
            String idenNumber1;
            String idenNumber2;
            Double amountToSend;
            JPanel firstPanel = new JPanel();
            JLabel lbl = new JLabel("First Card Identity Number:");
            JTextField txt = new JTextField(20);
            firstPanel.add(lbl);
            firstPanel.add(txt);
            int SelectedOption = JOptionPane.showOptionDialog(null, firstPanel, "ICN", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button, button[0]);
            if (SelectedOption == 0) {
                idenNumber1 = txt.getText();
                String[] button1 = {"OK"};
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Second Card Identity Number:");
                JTextField txt1 = new JTextField(20);
                panel.add(label);
                panel.add(txt1);
                int SelectedOption1 = JOptionPane.showOptionDialog(null, panel, "ICN", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button1, button1[0]);
                if (SelectedOption1 == 0) {
                    idenNumber2 = txt1.getText();
                    String[] button2 = {"OK"};
                    JPanel panel1 = new JPanel();
                    JLabel label1 = new JLabel("Transfer Funds:");
                    JTextField txt2 = new JTextField(20);
                    panel1.add(label1);
                    panel1.add(txt2);
                    int SelectedOption2 = JOptionPane.showOptionDialog(null, panel1, "Funds", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, button2, button2[0]);
                    if (SelectedOption2 == 0) {
                        amountToSend = Double.valueOf(txt2.getText());
                        if (amountToSend < 0) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Invalid Money Amount to Send!");
                        } else {
                            try {
                                firstAccount = accountService.find(idenNumber1);
                                if (firstAccount.getResult() == null) {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");
                                } else {
                                    try {
                                        secondAccount = accountService.find(idenNumber2);
                                        if (secondAccount.getResult() == null) {
                                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");
                                        } else {
                                            doneTransfer = accountService.makeTransfer(firstAccount.getResult(), secondAccount.getResult(), amountToSend);
                                            if(doneTransfer.getResult())
                                            {
                                                JOptionPane.showMessageDialog(adminView.getContentPane(), "Process done!New Balance:");
                                                JOptionPane.showMessageDialog(adminView.getContentPane(), firstAccount.getResult().getAmountofMoney());
                                                JOptionPane.showMessageDialog(adminView.getContentPane(), secondAccount.getResult().getAmountofMoney());
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(adminView.getContentPane(), "Sold Insuficient!");
                                            }

                                        }


                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }

                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            try {
                                secondAccount = accountService.find(idenNumber2);
                                if (secondAccount.getResult() == null) {
                                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");
                                }


                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                        }
                    }
                    }


                        }

                    }
                }





    private class ProcessBills implements ActionListener {
        private final AccountService accountService;

        public ProcessBills(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FileWriter writer = null;
            Notification<Account> findClient = null;
            Random rand = new Random();
            String input = JOptionPane.showInputDialog("Identity Card Number:", JOptionPane.OK_CANCEL_OPTION);
            try {
                findClient = accountService.find(input);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (findClient != null) {
                if (findClient.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), findClient.getFormattedErrors());
                } else {
                    try {

                        writer = new FileWriter("bill" +  rand.nextInt()+ ".txt");
                        if(findClient.getResult()!=null) {
                            JOptionPane.showMessageDialog(adminView.getContentPane(), "Account found.Bill generate!");
                            writer.write(findClient.getResult().toString());
                            writer.close();
                        }
                        else {JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");}
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            } else {

                JOptionPane.showMessageDialog(adminView.getContentPane(), "Account not found!");
                try {
                    writer.write("Insufficient funds!");
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            }

        }

    }
}


