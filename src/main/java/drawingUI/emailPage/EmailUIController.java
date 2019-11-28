package drawingUI.emailPage;

import javax.swing.*;

public class EmailUIController {

    emailPanel epanel = new emailPanel();

    public EmailUIController(JFrame frame)
        {
            frame.getContentPane().add(new enter_email_Panel(epanel));
        }
    }
