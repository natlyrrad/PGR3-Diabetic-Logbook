package drawingUI.main_panels;

import drawingUI.email_page.emailPanel;

import javax.swing.*;
import java.awt.*;

public class enter_email_Panel extends JPanel {

    public enter_email_Panel (emailPanel epanel)
    {
        setLayout(new GridLayout(1, 1));

        //https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel
        epanel.setLayout(new BoxLayout(epanel, BoxLayout.X_AXIS));
        add(epanel);
    }

}
