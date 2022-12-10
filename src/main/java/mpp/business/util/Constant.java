package mpp.business.util;

public class Constant {
    private Constant() {}

    public static final String CHECK_OUT = "Checkout";
    public static final String ADD_MEMBER = "Add member";
    public static final String ADD_AUTHOR = "Add author";
    public static final String ADD_BOOK_COPY = "Add book copy";
    public static final String ADD_BOOK = "Add book";
    public static final String EXPORT_CHECKOUT_RECORD = "Export Checkout Record";
    public static final String LOG_OUT = "Log out";
    public static final String LOG_OUT_MESS = "Do you want to log out?";
    public static final String LOG_OUT_TITLE = "LOG OUT";
    public static final String ERROR_PASSWORD_WRONG = "Password incorrect!";
    public static final String ERROR_USERNAME_WRONG = "Username %s not exist!";
    public static final String CHECK_DUE_DATE = "Check due date";
    //add library member
    public static final String SUCCESS_ADD_MEMBER = "Member %s is added success!";
    public static final String SUCCESS_UPDATE_MEMBER = "Member %s is updated success!";
    public static final String SUCCESS_DELETE_MEMBER = "Member %s is deleted success!";
    public static final String FAIL_ADD_MEMBER_BLANK = "%s field must be non empty!";
    public static final String WELCOME = "Welcome %s to the MPP Library!";

    public static final String MEMBER_ID = "Member Id";
    public static final String FIRST_NAME = "First name";
    public static final String LASTNAME = "Last name";
    public static final String STREET = "Street";
    public static final String STATE = "State";
    public static final String CITY = "City";
    public static final String ZIP = "Zip";
    public static final String PHONE_NUMBER = "Telephone number";

    public static final String EXISTING_MEMBER = "MemberId %s already exist. Do you want to replace?";
    public static final String DELETE_MESS = "Do you want to delete memberId %s?";
    public static final String DELETE_TITLE = "DELETE LIBRARY MEMBER";
    public static final String ADD_UPDATE_LIBRARY_MEMBER_TITLE = "ADD/UPDATE LIBRARY MEMBER";


    //Duedate book
    public static final String CANNOT_FIND_MEMBER_ID = "Cannot find member %s";

    //fine
    public static final Double FINE_20 = 20.0;
    public static final Integer FINE_PAID_INCREMENTAL = 7;

}
