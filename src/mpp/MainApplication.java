package mpp;

import mpp.view.LibAppWindow;

import javax.swing.*;
import java.awt.*;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibAppWindow libAppWindow = new LibAppWindow();
            libAppWindow.setSize(400, 200);
            libAppWindow.setTitle("MPP Library Application");
            libAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(libAppWindow);
            libAppWindow.setVisible(true);
        });
    }
    public static void centerFrameOnDesktop(Component frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = frame.getSize().height;
        int frameWidth = frame.getSize().width;
        frame.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 2);
    }
}
