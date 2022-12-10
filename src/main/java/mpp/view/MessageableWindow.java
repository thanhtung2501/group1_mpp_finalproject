package mpp.view;


import mpp.business.util.CommonUtil;
import mpp.business.util.Constant;

import static mpp.view.LibAppWindow.currentUser;
import static mpp.view.LibAppWindow.statusBar;
public interface MessageableWindow {

    public default void displayError(String msg) {
        statusBar.setForeground(CommonUtil.ERROR_MESSAGE_COLOR);
        statusBar.setText(msg);
    }

    public default void displayInfo(String msg) {
        statusBar.setForeground(CommonUtil.INFO_MESSAGE_COLOR);
        statusBar.setText(msg);
    }

    public default void setWelcomeUser() {
        statusBar.setText(String.format(Constant.WELCOME, currentUser));
        statusBar.setForeground(CommonUtil.DARK_BLUE);
    }
}
