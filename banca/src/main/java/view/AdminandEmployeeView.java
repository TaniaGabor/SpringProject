package view;

import model.Right;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import static database.Constants.Rights.*;


public class AdminandEmployeeView extends JFrame {

    private JButton viewUser;
    private JButton createUser;
    private JButton updateUser;
    private JButton deleteUser;
    private JButton activityUser;

    private JButton createAccount;
    private JButton updateAccount;
    private JButton viewAccount;
    private JButton deleteAccount;
    private JButton transferAmountMoney;
    private JButton createClient;
    private JButton viewClient;
    private JButton updateClient;
    private JButton processBills;
    private JButton logout;

    private JTextField usernameTextField;
    private JPasswordField passwordTextField;


    private Component[] oldComponents;


    public AdminandEmployeeView(Map<Right, Boolean> map) {
        setSize(300, 300);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        initializeFields();
        for (Right right : map.keySet()) {
            if (right.getRight().equals(VIEW_USER)) {
                cs.gridx = 0;
                cs.gridy = 0;
                cs.gridwidth = 1;
                panel.add(viewUser, cs);
            }
            if (right.getRight().equals(DELETE_USER)) {
                JPanel bp = new JPanel();
                bp.add(activityUser);
                getContentPane().add(bp, BorderLayout.PAGE_END);
                cs.gridx = 1;
                cs.gridy = 1;
                cs.gridwidth = 2;
                setVisible(false);
                panel.add(deleteUser, cs);

            }
            if (right.getRight().equals(CREATE_USER)) {
                setVisible(false);
                cs.gridx = 1;
                cs.gridy = 0;
                cs.gridwidth = 2;
                panel.add(createUser, cs);

            }
            if (right.getRight().equals(UPDATE_USER)) {
                setVisible(false);
                cs.gridx = 0;
                cs.gridy = 1;
                cs.gridwidth = 1;
                panel.add(updateUser, cs);

            }
            if (right.getRight().equals(CREATE_ACCOUNT)) {
                cs.gridx = 0;
                cs.gridy = 0;
                cs.gridwidth = 1;
                panel.add(createAccount, cs);
            }
            if (right.getRight().equals(DELETE_ACCOUNT)) {
                cs.gridx = 0;
                cs.gridy = 1;
                cs.gridwidth = 2;
                panel.add(deleteAccount, cs);
            }
            if (right.getRight().equals(UPDATE_ACCOUNT)) {
                cs.gridx = 1;
                cs.gridy = 0;
                cs.gridwidth = 2;
                panel.add(updateAccount, cs);
            }
            if (right.getRight().equals(RETURN_ACCOUNT)) {
                cs.gridx = 1;
                cs.gridy = 1;
                cs.gridwidth = 2;
                panel.add(viewAccount, cs);
            }

            if (right.getRight().equals(CREATE_CLIENT)) {
                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(createClient, cs);
            }
            if (right.getRight().equals(UPDATE_CLIENT)) {
                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(updateClient, cs);
            }

            if (right.getRight().equals(VIEW_CLIENT)) {
                cs.gridx = 0;
                cs.gridy = 3;
                cs.gridwidth = 3;
                panel.add(viewClient, cs);
            }
            if (right.getRight().equals(TRANSFER_MONEYAMOUNT)) {
                cs.gridx = 0;
                cs.gridy = 4;
                cs.gridwidth = 4;
                panel.add(transferAmountMoney, cs);

            }
            if (right.getRight().equals(GENERATE_BILL)) {
                cs.gridx = 0;
                cs.gridy = 5;
                cs.gridwidth =5;
                panel.add(processBills, cs);

            }
            cs.gridx = 0;
            cs.gridy = 6;
            cs.gridwidth =6;
            panel.add(logout, cs);

        }
        panel.setBorder(new LineBorder(Color.GRAY));
        getContentPane().add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }


    private void initializeFields() {
        viewUser = new JButton("View Employee");
        createUser = new JButton("Create Employee");
        updateUser = new JButton("Update Employee");
        deleteUser = new JButton("Delete Employee");
        activityUser = new JButton("Generate Activity Employee");
        createAccount = new JButton("Create Account");
        updateAccount = new JButton("Update Account");
        viewAccount = new JButton("View Account");
        deleteAccount = new JButton("Delete Account");
        transferAmountMoney = new JButton("Transfer Money");
        createClient = new JButton("Create Client");
        viewClient = new JButton("View Client");
        updateClient = new JButton("Update Client");
        processBills=new JButton("Process Bills");
        logout=new JButton("Log out");

    }


    public void setLogOutButtonListener(ActionListener logoutButtonListener) {
        logout.addActionListener(logoutButtonListener);
    }
    public void setViewButtonListener(ActionListener viewButtonListener) {
        viewUser.addActionListener(viewButtonListener);
    }

    public void setCreateButtonListener(ActionListener createButtonListener) {

        createUser.addActionListener(createButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener) {
        updateUser.addActionListener(updateButtonListener);
    }

    public void setDeleteButtonListener(ActionListener deleteButtonListener) {
        deleteUser.addActionListener(deleteButtonListener);
    }

    public void setTransferMoneyAmountButtonListener(ActionListener transferMoneyAmountButtonListener) {
        transferAmountMoney.addActionListener(transferMoneyAmountButtonListener);
    }

    public void setCreateAccountButtonListener(ActionListener createAccountButtonListener) {
        createAccount.addActionListener(createAccountButtonListener);
    }

    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener) {
        updateAccount.addActionListener(updateAccountButtonListener);
    }

    public void setViewAccountButtonListener(ActionListener viewAccountButtonListener) {
        viewAccount.addActionListener(viewAccountButtonListener);
    }



    public void setCreateClientButtonListener(ActionListener createClientButtonListener) {
        createClient.addActionListener(createClientButtonListener);
    }

    public void setViewClientButtonListener(ActionListener viewClientButtonListener) {
       viewClient.addActionListener(viewClientButtonListener);
    }

    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
        updateClient.addActionListener(updateClientButtonListener);
    }

    public void setActivityButtonListener(ActionListener activityButtonListener) {
        activityUser.addActionListener(activityButtonListener);
    }
    public void  setDeleteAccountButtonListener(ActionListener deleteAccountButtonListener) {
       deleteAccount.addActionListener(deleteAccountButtonListener);
    }
    public void  setProcessBillsButtonListener(ActionListener processBillsButtonListener) {
        processBills.addActionListener(processBillsButtonListener);
    }

}
