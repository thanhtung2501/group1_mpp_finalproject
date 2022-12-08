package ui;

import javax.swing.*;

public class AddBook {
    private JTextField txtISBN;
    private JTextField txtTitle;
    private JComboBox cmbAuthors;
    private JComboBox cmbMaxCheckoutLength;
    private JComboBox cmbNumberOfCopies;
    private JButton addButton;
    private JPanel mainPanel;

    public JTextField getTxtISBN() {
        return txtISBN;
    }

    public JTextField getTxtTitle() {
        return txtTitle;
    }

    public JComboBox getCmbAuthors() {
        return cmbAuthors;
    }

    public JComboBox getCmbMaxCheckoutLength() {
        return cmbMaxCheckoutLength;
    }

    public JComboBox getCmbNumberOfCopies() {
        return cmbNumberOfCopies;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
