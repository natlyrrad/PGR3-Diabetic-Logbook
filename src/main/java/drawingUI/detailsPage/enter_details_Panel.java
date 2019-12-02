package drawingUI.detailsPage; //Includes the class in the detailsPage package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;

public class enter_details_Panel extends JPanel {

    public enter_details_Panel (detailsPanel dpanel, buttonPanel butpanel)
    {
        //Set layout of the main panel (which includes the buttonPanel and detailsPanel)
        setLayout(new GridLayout(2, 1)); //Includes 2 panels on top of each other
        /* Reference 3 -
        taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
        dpanel.setLayout(new BoxLayout(dpanel, BoxLayout.X_AXIS));
        // Add the panels
        add(dpanel);
        add(butpanel);
    }

}
