package mpp.business.util;

import java.awt.*;
import java.util.regex.Pattern;

public class CommonUtil {
    private CommonUtil(){}

    public static final String[] DEFAULT_COLUMN_HEADERS
            = {"Member ID", "ISBN", "Book Copy ID", "Checkout Date", "Due Date"};
    public static final Color DARK_BLUE = Color.BLUE.darker();
    public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
    public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19);

    public static boolean isValidIsbn(String isbn) {
        String regex = "^[0-9]{2}+[- ][- 0-9X]{5}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(isbn).matches();
    }
}
