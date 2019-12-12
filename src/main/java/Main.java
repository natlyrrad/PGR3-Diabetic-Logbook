
import SQLDatabase.pullAzure;
import drawingUI.emailPage.EmailUIController;
import drawingUI.logPage.Testing;

import javax.swing.*;
import java.awt.*;

public class Main {

    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) throws Exception{
//            JFrame email_frame= new JFrame(gc); // Create a new JFrame that will contain the email frame
//            email_frame.setSize(500,300); // set size of frame
//            //Create a new class object that contains the email frame UI
//            EmailUIController uiemail = new EmailUIController(email_frame);
//
//            //Set frame as VISIBLE
//            email_frame.setVisible(true);
//            //This next line closes the program when the frame is closed
//            email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String[] data = {"2019-12-11 00:12:12;11.1;meat:12;rapid;4",
                "2019-12-11 00:12:12;7;Veg:25;rapid;5",
                "2019-12-11 00:12:12;8;Carbs:7, Veg:5;rapid;6"};
        Testing t = new Testing(data);
        }
}
