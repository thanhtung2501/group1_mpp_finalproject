package mpp.view;



import mpp.business.controller.ControllerInterface;
import mpp.business.controller.SystemController;
import mpp.business.exception.LoginException;
import mpp.business.model.ListItem;
import mpp.business.model.Role;
import mpp.business.util.Constant;

import javax.swing.*;
import java.awt.*;

import static mpp.business.util.CommonUtil.DARK_BLUE;
import static mpp.business.util.CommonUtil.adjustLabelFont;

public class LibAppWindow extends JFrame {

    private JPanel mainPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getCards() {
        return cards;
    }

    private Role role;

    JPanel cards;
    JList<ListItem> linkList;

    ListItem checkoutItem = new ListItem(Constant.CHECK_OUT, true);
    ListItem addMemberItem = new ListItem(Constant.ADD_MEMBER, true);
    ListItem addBookCopyItem = new ListItem(Constant.ADD_BOOK_COPY, true);
    ListItem checkDueDate = new ListItem(Constant.CHECK_DUE_DATE, true);
    ListItem addBookItem = new ListItem(Constant.ADD_BOOK, true);

    ListItem exportCheckoutRecordItem = new ListItem(Constant.EXPORT_CHECKOUT_RECORD, false);
    ListItem logoutItem = new ListItem(Constant.LOG_OUT, true);

    ListItem[] sellerItems = {checkoutItem, addMemberItem};
    ListItem[] memberItems = {checkoutItem, addBookCopyItem, addBookItem};

    public ListItem[] getSellerItems() {
        return sellerItems;
    }

    public ListItem[] getMemberItems() {
        return memberItems;
    }

    public JList<ListItem> getLinkList() {
        return linkList;
    }

    public void setLinkList(JList<ListItem> linkList) {
        this.linkList = linkList;
    }

    public static JLabel statusBar = new JLabel(Constant.WELCOME);
    public static String currentUser = "";
    private final ControllerInterface systemController = new SystemController();

    private JSplitPane innerPane;
    private JSplitPane outerPane;

    public LibAppWindow() {
        cards = new JPanel();
        cards.setLayout(new BorderLayout());
        LoginWindow loginWindow = new LoginWindow(this);
        cards.add(loginWindow.getMainPane(), BorderLayout.CENTER);
        this.add(cards, BorderLayout.CENTER);
    }

    public void createLinkLabels() {
        DefaultListModel<ListItem> model = new DefaultListModel<>();
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
                if (value instanceof ListItem) {
                    ListItem nextItem = (ListItem) value;
                    setText(nextItem.getItemName());
                    if (isSelected) {
                        setForeground(Color.WHITE);
                        setBackground(DARK_BLUE);
                    }
                }
                return c;
            }
        });
    }

    private void createMainPanels() {

        // Checkout Book Panel
        CheckoutWindow checkoutWindow = new CheckoutWindow();
        JPanel checkoutPanel = checkoutWindow.getMainPanel();

        // Add Library Member Window
        AddLibraryMemberWindow addLibraryMemberWindow = new AddLibraryMemberWindow();
        JPanel addLibraryMemberPanel = addLibraryMemberWindow.getMainPanel();

        // Add Book Copy Window
        AddBookCopyWindow addBookCopyWindow = new AddBookCopyWindow();
        JPanel addBookCopyPanel = addBookCopyWindow.getMainPanel();

        // Add Book Window
        AddBookWindow addBookWindow = new AddBookWindow();
        JPanel addBookPanel = addBookWindow.getMainPanel();

        // Add Book Copy Window
        DueDateBookCopyWindow dueDateBookCopyWindow = new DueDateBookCopyWindow();
        JPanel dueDateBookCopyPanel = dueDateBookCopyWindow.getMainPanel();

        // Export Checkout Record Entry
        ExportCheckoutRecordWindow exportCheckoutRecordWindow = new ExportCheckoutRecordWindow();
        JPanel exportCheckoutRecordPanel = exportCheckoutRecordWindow.getMainPanel();

        cards.setLayout(new CardLayout());
        cards.add(checkoutPanel, checkoutItem.getItemName());
        cards.add(addBookCopyPanel, addBookCopyItem.getItemName());
        cards.add(addLibraryMemberPanel, addMemberItem.getItemName());
        cards.add(addBookPanel, addBookItem.getItemName());
        cards.add(dueDateBookCopyPanel, checkDueDate.getItemName());
        cards.add(exportCheckoutRecordPanel, exportCheckoutRecordItem.getItemName());
    }

    private void addComponents() {
        adjustLabelFont(statusBar, DARK_BLUE, true);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        createLinkLabels();
        createMainPanels();
        CardLayout cl = (CardLayout) (cards.getLayout());
        linkList.addListSelectionListener(event -> {
            String value = linkList.getSelectedValue().getItemName();
            if (Constant.LOG_OUT.equals(value)) {
                handleLogout();
            } else {
                cl.show(cards, value);
            }
        });

        if (innerPane == null && outerPane == null) {
            innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
            innerPane.setDividerLocation(180);
            outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, statusBar);
            outerPane.setDividerLocation(500);
            this.add(outerPane, BorderLayout.CENTER);
        } else {
            innerPane.setLeftComponent(linkList);
            innerPane.setDividerLocation(180);
            outerPane.setRightComponent(statusBar);
            outerPane.setDividerLocation(500);
        }
    }

    private void handleLogout() {
        int opt = JOptionPane.showConfirmDialog(mainPanel, Constant.LOG_OUT_MESS, Constant.LOG_OUT_TITLE, JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            removeComponents();
            innerPane.setLeftComponent(null);
            outerPane.setRightComponent(null);
            setSize(400, 200);
            setLocationRelativeTo(null);
            setVisible(true);
            LoginWindow loginWindow = new LoginWindow(this);
            cards.add(loginWindow.getMainPane(), BorderLayout.CENTER);
        }
    }

    public void removeComponents() {
        cards.removeAll();
        cards.invalidate();
        cards.repaint();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void validateUser(String username, String password) {
        if (username.length() > 0 && password.length() > 0) {
            try {
                this.role = systemController.login(username, password);
                currentUser = username;
                statusBar.setText(String.format(Constant.WELCOME, currentUser));
                removeComponents();
                addComponents();
            } catch (LoginException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
