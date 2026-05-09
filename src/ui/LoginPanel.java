package ui;

import javax.swing.*;
import java.awt.*;
import app.MainApp;
import dao.UserDAO;
import model.User;

public class LoginPanel extends JPanel {

    private MainApp mainApp;
    private UserDAO userDAO;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainApp mainApp) {

        this.mainApp = mainApp;
        this.userDAO = new UserDAO();

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel title = new JLabel("User Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        centerPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        centerPanel.add(usernameField);

        centerPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        centerPanel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        loginBtn.addActionListener(e -> handleLogin());
        registerBtn.addActionListener(e -> handleRegister());

        centerPanel.add(loginBtn);
        centerPanel.add(registerBtn);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void handleLogin() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        User user = userDAO.loginUser(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "Welcome " + user.getUsername());

            mainApp.showPanel("dashboard");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username and password cannot be empty.");
            return;
        }

        User user = new User(username, password);

        boolean success = userDAO.registerUser(user);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Registration Successful!");
        } else {
            JOptionPane.showMessageDialog(this,
                    " Username already exists. Please choose another.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
