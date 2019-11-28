

import drawingUI.details_page.DetailsUIController;
import drawingUI.email_page.EmailUIController;

import javax.swing.*;
import java.awt.*;

public class Main {

    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) {
        JFrame email_frame= new JFrame(gc); // Create a new JFrame
        email_frame.setSize(500,300);

        EmailUIController uiemail = new EmailUIController(email_frame);

        email_frame.setVisible(true);
        // This next line closes the program when the frame is closed
        email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


       //Here is the frame for the actual log itself
        //create new frame
        JFrame frame= new JFrame("Blood Sugar Level Input");
        frame.setSize(700,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //add panel to frame
        EntryPanel mainPanel = new EntryPanel();
        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);

    }
}
