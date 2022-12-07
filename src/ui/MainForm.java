package ui;

import business.controller.ControllerInterface;
import business.controller.SystemController;
import business.exception.LoginException;
import business.model.Role;

import javax.swing.*;
import java.awt.*;

import static business.util.Constant.WELCOME;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private Role role;
    JPanel cards;
    public static JTextArea statusBar = new JTextArea(WELCOME);
    public static String currentUser = "";
    private final ControllerInterface systemController = new SystemController();

    public MainForm() {
        cards = new JPanel();
        cards.setLayout(new BorderLayout());
        LoginForm loginForm = new LoginForm(this);
        cards.add(loginForm.getMainPanel(), BorderLayout.CENTER);
        this.add(cards, BorderLayout.CENTER);
    }

    public void validateUser(String username, String password) {
        if (username.length() > 0 && password.length() > 0) {
            try {
                this.role = systemController.login(username, password);
                currentUser = username;
                statusBar.setText(String.format(WELCOME, currentUser));
                removeComponents();
                addComponents();
            } catch (LoginException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void addComponents() {

    }

    private void removeComponents() {
        cards.removeAll();
        cards.invalidate();
        cards.repaint();
    }
}
