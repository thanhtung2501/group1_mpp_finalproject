package mpp.business.util;

import mpp.business.model.CheckoutRecordEntry;
import mpp.business.model.LibraryMember;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtil {
    private CommonUtil(){}

    public static final String[] DEFAULT_COLUMN_HEADERS
            = {"Member ID", "ISBN", "Book Copy ID", "Checkout Date", "Due Date", "Fine", "Fine Date Paid"};
    public static final Color DARK_BLUE = Color.BLUE.darker();
    public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
    public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19);

    public static boolean isValidIsbn(String isbn) {
        String regex = "^[0-9]{2}+[- ][- 0-9X]{5}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(isbn).matches();
    }

    public static boolean isValidZip(String zip) {
        String regex = "^[0-9]{5}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(zip).matches();
    }

    public static java.util.List<String[]> parseCheckoutRecordEntryRows(LibraryMember libraryMember) {
        java.util.List<CheckoutRecordEntry> checkoutRecordEntries = libraryMember.getCheckoutRecord().getCheckoutRecordEntries();
        List<String[]> rows = new ArrayList<>();
        checkoutRecordEntries.forEach(checkoutRecordEntry -> {
            String[] row = new String[DEFAULT_COLUMN_HEADERS.length];
            row[0] = libraryMember.getMemberId();
            row[1] = checkoutRecordEntry.getIsbn();
            row[2] = checkoutRecordEntry.getBookCopyId();
            row[3] = checkoutRecordEntry.getCheckoutDate().toString();
            row[4] = checkoutRecordEntry.getDueDate().toString();
            row[5] = String.valueOf(checkoutRecordEntry.getFines());
            row[6] = checkoutRecordEntry.getFinesDatePaid() != null ? checkoutRecordEntry.getFinesDatePaid().toString(): "";
            rows.add(row);
        });

        return rows;
    }

    public static void adjustLabelFont(JLabel label, Color color, boolean bigger) {
        if (bigger) {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() + 2));
            label.setFont(f);
        } else {
            Font f = new Font(label.getFont().getName(),
                    label.getFont().getStyle(), (label.getFont().getSize() - 2));
            label.setFont(f);
        }
        label.setForeground(color);
    }


}
