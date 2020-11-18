package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel username;
    private JLabel password;
    private JPanel panel;
    private JPanel bp;

    public LoginView() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        initializeFields();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(username, cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(usernameTextField, cs);

        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(password, cs);

        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(passwordTextField,cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        JPanel bp = new JPanel();
        bp.add(loginButton);
        bp.add(registerButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        username=new JLabel("Username: ");
        password=new JLabel ("Password: ");
        usernameTextField = new JTextField(20);
        passwordTextField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        String pass= new String(passwordTextField.getPassword());
        System.out.println(pass);
        return pass;
    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        loginButton.addActionListener(loginButtonListener);
    }

    public void setRegisterButtonListener(ActionListener registerButtonListener) {
        registerButton.addActionListener(registerButtonListener);
    }
    public void getRegister(){

    }
}
