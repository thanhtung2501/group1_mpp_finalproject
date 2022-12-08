package mpp.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAuthor {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtZip;
    private JTextField txtPhone;
    private JCheckBox chbCredentials;
    private JTextArea txaBio;
    private JButton btnAdd;
    private JPanel mainPanel;

    public AddAuthor() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthor();
            }
        });
    }

    public void addAuthor(){
        if(txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty())
            JOptionPane.showMessageDialog(mainPanel, "Please enter both first name and last name");
        else {
            JOptionPane.showMessageDialog(mainPanel, txtFirstName.getText() + " " + txtLastName.getText()
                    + " has been added to the author list!");
        }
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public JTextField getTextField1() {
        return txtLastName;
    }

    public JTextField getTxtStreet() {
        return txtStreet;
    }

    public JTextField getTxtCity() {
        return txtCity;
    }

    public JTextField getTxtState() {
        return txtState;
    }

    public JTextField getTxtZip() {
        return txtZip;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JCheckBox getChbCredentials() {
        return chbCredentials;
    }

    public JTextArea getTxaBio() {
        return txaBio;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
