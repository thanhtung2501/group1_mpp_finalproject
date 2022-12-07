package business.util;

import java.util.regex.Pattern;

public class CommonUtil {
    private CommonUtil(){}

    public static boolean isValidIsbn(String isbn) {
        String regex = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(isbn).matches();
    }
}
