package drawingUI.emailPage; //Includes the class in the emailPage package
//Java classes imports (JDK)
import javax.swing.*;

public class EmailUIController {
    // Declares an new emailPanel
    emailPanel epanel = new emailPanel();

    public EmailUIController(JFrame frame)
        {
            // Set the enter_email_panel as the contents in the frame (contains the emailPanel)
            frame.getContentPane().add(new enter_email_Panel(epanel));
        }
    }
