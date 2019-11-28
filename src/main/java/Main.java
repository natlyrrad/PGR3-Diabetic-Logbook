

import drawingUI.details_page.DetailsUIController;
import drawingUI.email_page.EmailUIController;

import javax.swing.*;
import java.awt.*;

public class Main {

    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) {
        JFrame details_frame= new JFrame(gc); // Create a new JFrame
        details_frame.setSize(500,700);

        DetailsUIController uidetails = new DetailsUIController(details_frame);

        details_frame.setVisible(true);
        // This next line closes the program when the frame is closed
        details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        JFrame email_frame= new JFrame(gc); // Create a new JFrame
        email_frame.setSize(500,300);

        EmailUIController uiemail = new EmailUIController(email_frame);

        email_frame.setVisible(true);
        // This next line closes the program when the frame is closed
        email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
