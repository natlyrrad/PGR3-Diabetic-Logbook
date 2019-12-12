
import SQLDatabase.pullAzure;
import drawingUI.emailPage.EmailUIController;
import drawingUI.logPage.loghistory;

import javax.swing.*;
import java.awt.*;

public class Main {

    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) throws Exception{
            JFrame email_frame= new JFrame(gc); // Create a new JFrame that will contain the email frame
            email_frame.setSize(500,300); // set size of frame
//            //Create a new class object that contains the email frame UI
//        EmailUIController e = new EmailUIController(email_frame);
            loghistory log = new loghistory();
            email_frame.add(log);
            //Set frame as VISIBLE
            email_frame.setVisible(true);
            //This next line closes the program when the frame is closed
            email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
}
