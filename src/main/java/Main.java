
import drawingUI.emailPage.EmailUIController;

import javax.swing.*;


    public class Main {
        public static void main(String[] args) throws Exception{

            JFrame email_frame= new JFrame(); // Create a new JFrame
            email_frame.setSize(500,300);

            EmailUIController uiemail = new EmailUIController(email_frame);

            email_frame.setVisible(true);
            //This next line closes the program when the frame is closed
            email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }
    }
