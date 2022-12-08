import ui.MainForm;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setSize(400, 200);
            mainForm.setTitle("MPP Library Application");
            mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(mainForm);
            mainForm.setVisible(true);
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