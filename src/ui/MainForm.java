package ui;

import business.controller.ControllerInterface;
import business.controller.SystemController;
import business.exception.LoginException;
import business.model.ItemDetails;
import business.model.Role;
import business.util.CommonUtil;

import javax.swing.*;
import java.awt.*;

import static business.util.Constant.*;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private Role role;
    private JPanel cards;
    private JList<ItemDetails> linkList;
    private JSplitPane innerPanel;
    private JSplitPane outerPanel;
    ItemDetails checkoutItem = new ItemDetails(CHECK_OUT, true);
    ItemDetails addMemberItem = new ItemDetails(ADD_MEMBER, false);
    ItemDetails addBookCopyItem = new ItemDetails(ADD_BOOK_COPY, false);
    ItemDetails checkDueDate = new ItemDetails(CHECK_DUE_DATE, true);
    ItemDetails addBookItem = new ItemDetails(ADD_BOOK, false);
    ItemDetails addAuthorItem = new ItemDetails(ADD_AUTHOR, false);

    ItemDetails exportCheckoutRecordItem = new ItemDetails(EXPORT_CHECKOUT_RECORD, false);
    ItemDetails logoutItem = new ItemDetails(LOG_OUT, true);

    ItemDetails[] sellerItems = {checkoutItem, addMemberItem};
    ItemDetails[] memberItems = {checkoutItem, addBookCopyItem, addBookItem};
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
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        createLinkLabels();
        createMainPanels();
        CardLayout cl = (CardLayout) (cards.getLayout());
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue().getItemName();
            if (LOG_OUT.equals(value)) {
                handleLogout();
            } else {
                cl.show(cards, value);
            }
        });

        if (innerPanel == null && outerPanel == null) {
            innerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
            innerPanel.setDividerLocation(180);
            outerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPanel, statusBar);
            outerPanel.setDividerLocation(500);
            this.add(outerPanel, BorderLayout.CENTER);
        } else {
            innerPanel.setLeftComponent(linkList);
            innerPanel.setDividerLocation(180);
            outerPanel.setRightComponent(statusBar);
            outerPanel.setDividerLocation(500);
        }
    }

    private void handleLogout() {
        int opt = JOptionPane.showConfirmDialog(mainPanel, LOG_OUT_MESS, LOG_OUT_TITLE, JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            removeComponents();
            innerPanel.setLeftComponent(null);
            outerPanel.setRightComponent(null);
            setSize(400, 200);
            setLocationRelativeTo(null);
            setVisible(true);
            LoginForm loginForm = new LoginForm(this);
            cards.add(loginForm.getMainPanel(), BorderLayout.CENTER);
        }
    }

    private void createMainPanels() {
        // Add Book Window
        AddBook addBookWindow = new AddBook();
        JPanel addBookPanel = addBookWindow.getMainPanel();

        // Add Author Window
        AddAuthor addAuthorWindow = new AddAuthor();
        JPanel addAuthorPanel = addAuthorWindow.getMainPanel();

        cards.setLayout(new CardLayout());
        cards.add(addBookPanel, addBookItem.getItemName());
        cards.add(addAuthorPanel, addAuthorItem.getItemName());
    }

    private void createLinkLabels() {
        DefaultListModel<ItemDetails> model = new DefaultListModel<>();
        if (role == Role.LIBRARIAN) {
            model.addElement(checkoutItem);
            model.addElement(checkDueDate);
            model.addElement(exportCheckoutRecordItem);
        } else if (role == Role.ADMIN) {
            model.addElement(addMemberItem);
            model.addElement(addBookItem);
            model.addElement(addBookCopyItem);
        } else {
            model.addElement(checkoutItem);
            model.addElement(checkDueDate);
            model.addElement(addMemberItem);
            model.addElement(addBookItem);
            model.addElement(addBookCopyItem);
            model.addElement(exportCheckoutRecordItem);
        }
        model.addElement(logoutItem);
        linkList = new JList<>(model);
        // selected first item in list
        int begin = 0;
        int end = 0;
        linkList.setSelectionInterval(begin, end);
        linkList.setCellRenderer(new DefaultListCellRenderer() {
            @SuppressWarnings("rawtypes")
            @Override
            public Component getListCellRendererComponent(JList list,
                                                          Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
                if (value instanceof ItemDetails) {
                    ItemDetails nextItem = (ItemDetails) value;
                    setText(nextItem.getItemName());
                    if (isSelected) {
                        setForeground(Color.WHITE);
                        setBackground(CommonUtil.DARK_BLUE);
                    }
                }
                return c;
            }
        });
    }

    private void removeComponents() {
        cards.removeAll();
        cards.invalidate();
        cards.repaint();
    }
}
