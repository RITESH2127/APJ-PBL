package gui;

import service.AuthService;
import model.User;
import util.DataStore;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private AuthService authService;
    private DataStore dataStore;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(DataStore dataStore) {
        this.dataStore = dataStore;
        this.authService = new AuthService(dataStore);

        setTitle("Hospital Management System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        panel.add(new JLabel("Username (admin):"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password (admin123):"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> attemptLogin());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        add(new JLabel("Welcome to Hospital System", SwingConstants.CENTER), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = authService.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful! Welcome, " + user.getUsername());
            dispose(); // Close login window
            // Launch main dashboard
            new DashboardFrame(dataStore).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
