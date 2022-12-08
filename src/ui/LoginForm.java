package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginForm implements ActionListener, KeyListener, DocumentListener {
    private JPanel mainPanel;
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    private MainForm mainForm;

    public LoginForm(MainForm mainForm) {
        tfUsername.getDocument().addDocumentListener(this);
        pfPassword.getDocument().addDocumentListener(this);
        btnLogin.setEnabled(false);
        this.mainForm = mainForm;
        // add the listener to the textField
        btnLogin.addKeyListener(this);
        pfPassword.addKeyListener(this);
        tfUsername.addKeyListener(this);
        btnLogin.addActionListener(e ->
                mainForm.validateUser(tfUsername.getText().trim(), pfPassword.getPassword() != null ? String.valueOf(pfPassword.getPassword()) : "")
        );
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTextField getTfUsername() {
        return tfUsername;
    }

    public void setTfUsername(JTextField tfUsername) {
        this.tfUsername = tfUsername;
    }

    public JPasswordField getPfPassword() {
        return pfPassword;
    }

    public void setPfPassword(JPasswordField pfPassword) {
        this.pfPassword = pfPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(JButton btnLogin) {
        this.btnLogin = btnLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            mainForm.validateUser(tfUsername.getText().trim(), pfPassword.getPassword() != null ? String.valueOf(pfPassword.getPassword()) : "");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateLoginButton();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateLoginButton();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateLoginButton();
    }

    private void updateLoginButton() {
        btnLogin.setEnabled(false);
        if (pfPassword.getPassword().length > 0 && !tfUsername.getText().trim().isEmpty()) {
            btnLogin.setEnabled(true);
        }
    }
}
