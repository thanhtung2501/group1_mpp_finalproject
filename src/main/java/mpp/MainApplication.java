package mpp;

import mpp.dataaccess.DataAccess;
import mpp.dataaccess.DataAccessFacade;
import mpp.dataaccess.TestData;
import mpp.view.LibAppWindow;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
       // SpringApplication.run(MainApplication.class, args);
        SwingUtilities.invokeLater(() -> {
            LibAppWindow libAppWindow = new LibAppWindow();
            libAppWindow.setSize(400, 200);
            libAppWindow.setTitle("MPP Library Application");
            libAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(libAppWindow);
            libAppWindow.setVisible(true);
        });
        setTestData();

    }

    public static void centerFrameOnDesktop(Component frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = frame.getSize().height;
        int frameWidth = frame.getSize().width;
        frame.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 2);
    }

    public static void setTestData(){
        TestData td = new TestData();
        td.bookData();
        td.libraryMemberData();
        td.userData();
        td.authorData();

        DataAccess da = new DataAccessFacade();
        System.out.println(da.readBooksMap());
        System.out.println(da.readUserMap());
    }

}
