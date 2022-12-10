package mpp.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import mpp.business.controller.SystemController;
import mpp.business.model.Address;
import mpp.business.model.LibraryMember;
import mpp.business.util.CommonUtil;
import mpp.business.util.Constant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static mpp.business.util.Constant.SUCCESS_UPDATE_MEMBER;
import static mpp.view.LibAppWindow.statusBar;

public class AddLibraryMemberWindow extends JFrame implements MessageableWindow {
    private static final long serialVersionUID = 1L;

    private JPanel AddLibraryMemberWindow;
    private JButton addMemberButton;
    private JButton deleteMemberButton;
    private JTextField street;
    private JTextField telephoneNumber;
    private JTextField zip;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField city;
    private JTextField state;
    private JTextField memberId;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JTable memberTable;
    private JScrollPane memberScroll;
    private CustomTableModel model;

    //table data and config
    private final String[] DEFAULT_COLUMN_HEADERS
            = {"Member Id", "First Name", "Last Name", "Street", "City", "State", "Zip", "Telephone number"};
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 480;
    private static final int TABLE_WIDTH = (int) (0.75 * SCREEN_WIDTH);
    private static final int DEFAULT_TABLE_HEIGHT = (int) (0.75 * SCREEN_HEIGHT);
    private final float COL_WIDTH_PROPORTIONS = 0.35f;
    private final Color errorColor = CommonUtil.ERROR_MESSAGE_COLOR;
    private final Color infoColor = CommonUtil.INFO_MESSAGE_COLOR;
    private SystemController systemController;

    public JPanel getMainPanel() {
        return AddLibraryMemberWindow;
    }

    public AddLibraryMemberWindow() {

        AddLibraryMemberWindow = new JPanel();
        AddLibraryMemberWindow.setLayout(new BorderLayout());
        systemController = new SystemController();
        memberScroll = new JScrollPane();

        updateTable();
        initPanel();

        //add or update new meber
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibraryMember libraryMember = new LibraryMember(memberId.getText().strip(), firstName.getText().strip()
                        , lastName.getText().strip(), telephoneNumber.getText().strip()
                        , new Address(street.getText().strip(), city.getText().strip(), state.getText().strip(), zip.getText().strip()));
                Boolean isValidate = validateMember(libraryMember);
                if (isValidate) {
                    String newMemberId = libraryMember.getMemberId();
                    String oldMemberId = getLibraryMemberId(newMemberId);
                    if (newMemberId.equals(oldMemberId)) {
                        int opt = JOptionPane.showConfirmDialog(AddLibraryMemberWindow, String.format(Constant.EXISTING_MEMBER, newMemberId), Constant.ADD_UPDATE_LIBRARY_MEMBER_TITLE, JOptionPane.YES_NO_OPTION);
                        if (opt == 0) {
                            updateMember(model, libraryMember, false);
                            memberTable.updateUI();
                            clear();
                        }
                    } else {
                        updateMember(model, libraryMember, true);
                        memberTable.updateUI();
                        clear();
                    }
                }
            }
        });

        //delete member
        deleteMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel tblModel = memberTable.getModel();
                if (memberTable.getSelectedRow() < 0) {
                    displayError("There is no members selected to delete!");
                    return;
                }

                String getMemberId = tblModel.getValueAt(memberTable.getSelectedRow(), 0).toString().strip();
                int opt = JOptionPane.showConfirmDialog(AddLibraryMemberWindow, String.format(Constant.DELETE_MESS, getMemberId), Constant.DELETE_TITLE, JOptionPane.YES_NO_OPTION);
                if (opt == 0) {
                    if (validateMemberId(getMemberId)) {
                        systemController.deleteLibraryMember(getMemberId);
                        clear();
                        model.setTableValues(parseMemberToArray());
                        memberTable.updateUI();
                        printNotify(Constant.SUCCESS_DELETE_MEMBER, getMemberId, infoColor);
                    }
                }
            }
        });

        //click row in table and fill box
        memberTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                getRowData();
            }
        });

        getMainPanel().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                setWelcomeUser();
            }
        });
    }

    private void getRowData() {
        TableModel tblModel = memberTable.getModel();
        String getMemberId = tblModel.getValueAt(memberTable.getSelectedRow(), 0).toString();
        String getFirstName = tblModel.getValueAt(memberTable.getSelectedRow(), 1).toString();
        String getLastName = tblModel.getValueAt(memberTable.getSelectedRow(), 2).toString();
        String getStreet = tblModel.getValueAt(memberTable.getSelectedRow(), 3).toString();
        String getCity = tblModel.getValueAt(memberTable.getSelectedRow(), 4).toString();
        String getState = tblModel.getValueAt(memberTable.getSelectedRow(), 5).toString();
        String getZip = tblModel.getValueAt(memberTable.getSelectedRow(), 6).toString();
        String getTelephoneNumber = tblModel.getValueAt(memberTable.getSelectedRow(), 7).toString();
        memberId.setText(getMemberId);
        street.setText(getStreet);
        telephoneNumber.setText(getTelephoneNumber);
        zip.setText(getZip);
        lastName.setText(getLastName);
        firstName.setText(getFirstName);
        city.setText(getCity);
        state.setText(getState);
    }

    private Boolean validateMember(LibraryMember libraryMember) {
        if (!validateMemberId(libraryMember.getMemberId())) {
            return false;
        } else if (!validateFirstName(libraryMember.getFirstName())) {
            return false;
        } else if (!validateLastName(libraryMember.getLastName())) {
            return false;
        } else if (!validateStreet(libraryMember.getAddress().getStreet())) {
            return false;
        } else if (!validateState(libraryMember.getAddress().getState())) {
            return false;
        } else if (!validateCity(libraryMember.getAddress().getCity())) {
            return false;
        } else if (!validateZip(libraryMember.getAddress().getZip())) {
            return false;
        } else if (!validatePhoneNumber(libraryMember.getTelephone())) {
            return false;
        }
        return true;
    }

    private boolean validateMemberId(String memberId) {
        if (!blankOrEmpty(memberId, Constant.MEMBER_ID) && isNumber(memberId, Constant.MEMBER_ID)) {
            return true;
        }
        printNotify("Member ID must be integer", memberId, errorColor);
        return false;
    }

    private boolean validateFirstName(String firstName) {
        if (!blankOrEmpty(firstName, Constant.FIRST_NAME) && isWord(firstName, Constant.FIRST_NAME)) {
            return true;
        }
        printNotify("First name must not be empty", firstName, errorColor);
        return false;
    }

    private boolean validateLastName(String lastName) {
        if (!blankOrEmpty(lastName, Constant.LASTNAME) && isWord(lastName, Constant.LASTNAME)) {
            return true;
        }
        printNotify("Lats name must not be empty", lastName, errorColor);
        return false;
    }

    private boolean validateStreet(String street) {
        if (!blankOrEmpty(street, Constant.STREET)) {
            return true;
        }
        printNotify("Street must not be empty", street, errorColor);
        return false;
    }

    private boolean validateState(String state) {
        if (!blankOrEmpty(state, Constant.STATE) && isWord(state, Constant.STATE)) {
            return true;
        }
        printNotify("State must not be empty", state, errorColor);
        return false;
    }

    private boolean validateCity(String city) {
        if (!blankOrEmpty(city, Constant.CITY) && isWord(city, Constant.CITY)) {
            return true;
        }
        printNotify("City must not be empty", city, errorColor);
        return false;
    }

    private boolean validateZip(String zip) {
        if (!blankOrEmpty(zip, Constant.ZIP) && CommonUtil.isValidZip(zip)) {
            return true;
        }
        printNotify("Zip must not be empty and a number with 5 digits", zip, errorColor);
        return false;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (!blankOrEmpty(phoneNumber, Constant.PHONE_NUMBER) && isNumber(phoneNumber, Constant.PHONE_NUMBER)) {
            return true;
        }
        printNotify("Phone number must not be empty and a number", phoneNumber, errorColor);
        return false;
    }

    private boolean blankOrEmpty(String check, String print) {
        if (check == null || check.length() == 0) {
            printNotify(Constant.FAIL_ADD_MEMBER_BLANK, print, errorColor);
            return true;
        }
        return false;
    }

    private boolean isNumber(String check, String print) {
        for (int i = 0; i < check.length(); ++i) {
            if (!Character.isDigit(check.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isWord(String check, String print) {
        for (int i = 0; i < check.length(); ++i) {
            if (!Character.isAlphabetic(check.charAt(i)) && check.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        street.setText("");
        telephoneNumber.setText("");
        zip.setText("");
        lastName.setText("");
        firstName.setText("");
        city.setText("");
        state.setText("");
        memberId.setText("");
    }

    private void createCustomColumns(JTable table, int width, float proportions,
                                     String[] headers) {
        table.setAutoCreateColumnsFromModel(false);
        int num = headers.length;
        for (int i = 0; i < num; ++i) {
            TableColumn column = new TableColumn(i);
            column.setHeaderValue(headers[i]);
            column.setMinWidth(Math.round(proportions * width));
            table.addColumn(column);
        }
    }

    private List<String[]> parseMemberToArray() {
        Map<String, LibraryMember> data = systemController.getAllLibraryMembers();
        List<String[]> listResouse = new ArrayList<>();
        if (data != null) {
            for (String key : data.keySet()) {
                LibraryMember keyData = data.get(key);
                String[] input = {keyData.getMemberId(), keyData.getFirstName(), keyData.getLastName(),
                        keyData.getAddress().getStreet(), keyData.getAddress().getCity(), keyData.getAddress().getState(),
                        keyData.getAddress().getZip()
                        , keyData.getTelephone()};
                listResouse.add(input);
            }
        }
        return listResouse;
    }

    private String getLibraryMemberId(String libraryMember) {
        Map<String, LibraryMember> data = systemController.getAllLibraryMembers();
        return data.get(libraryMember) != null ? data.get(libraryMember).getMemberId() : "";
    }

    private void updateTable() {
        model = new CustomTableModel();
        model.setTableValues(parseMemberToArray());
        memberTable = new JTable(model);
        memberTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        createCustomColumns(memberTable, TABLE_WIDTH,
                COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
        memberScroll.setPreferredSize(
                new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));

        memberTable.setShowGrid(true);
        memberTable.setGridColor(Color.GRAY);
        memberTable.setShowGrid(true);
        memberTable.setGridColor(Color.GRAY);

        memberScroll.getViewport().add(memberTable);
    }

    private void initPanel() {
        //set panel
        AddLibraryMemberWindow.add(middlePanel, BorderLayout.NORTH);
        AddLibraryMemberWindow.add(memberScroll, BorderLayout.CENTER);
        AddLibraryMemberWindow.add(lowerPanel, BorderLayout.SOUTH);
        getContentPane().add(AddLibraryMemberWindow);
        AddLibraryMemberWindow.setVisible(true);
        pack();
    }

    private void updateMember(CustomTableModel model, LibraryMember libraryMember, boolean isNew) {
        systemController.addUpdateLibraryMember(libraryMember);
        model.setTableValues(parseMemberToArray());
        String successAddUpdateMember = isNew ? Constant.SUCCESS_ADD_MEMBER : SUCCESS_UPDATE_MEMBER;
        printNotify(successAddUpdateMember, libraryMember.getMemberId(), infoColor);
    }

    private void printNotify(String title, String memberId, Color color) {
        statusBar.setText(String.format(title, memberId));
        statusBar.setForeground(color);
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
        AddLibraryMemberWindow = new JPanel();
        AddLibraryMemberWindow.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        AddLibraryMemberWindow.setName("Add New Member");
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        AddLibraryMemberWindow.add(middlePanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        telephoneNumber = new JTextField();
        middlePanel.add(telephoneNumber, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        zip = new JTextField();
        middlePanel.add(zip, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        street = new JTextField();
        middlePanel.add(street, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lastName = new JTextField();
        middlePanel.add(lastName, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        state = new JTextField();
        middlePanel.add(state, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        firstName = new JTextField();
        middlePanel.add(firstName, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        city = new JTextField();
        middlePanel.add(city, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        memberId = new JTextField();
        middlePanel.add(memberId, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("City");
        middlePanel.add(label1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("State");
        middlePanel.add(label2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Zip");
        middlePanel.add(label3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Telephone number");
        middlePanel.add(label4, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Member Id");
        middlePanel.add(label5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("First name");
        middlePanel.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Last name");
        middlePanel.add(label7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Street");
        middlePanel.add(label8, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Add library member");
        middlePanel.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        AddLibraryMemberWindow.add(lowerPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addMemberButton = new JButton();
        addMemberButton.setText("Add/Update member");
        lowerPanel.add(addMemberButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteMemberButton = new JButton();
        deleteMemberButton.setText("Delete member");
        lowerPanel.add(deleteMemberButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        memberScroll = new JScrollPane();
        AddLibraryMemberWindow.add(memberScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        memberScroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        memberTable = new JTable();
        memberScroll.setViewportView(memberTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return AddLibraryMemberWindow;
    }
}
