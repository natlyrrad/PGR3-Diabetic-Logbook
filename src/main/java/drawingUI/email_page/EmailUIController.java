package drawingUI.email_page;

import drawingUI.main_panels.enter_email_Panel;

import javax.swing.*;

public class EmailUIController {

    emailPanel epanel = new emailPanel();

    public EmailUIController(JFrame frame)
        {
            frame.getContentPane().add(new enter_email_Panel(epanel));
        }
    }
