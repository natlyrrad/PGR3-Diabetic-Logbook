package drawingUI.emailPage; //Includes the class in the emailPage package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;

public class enter_email_Panel extends JPanel {

    public enter_email_Panel (emailPanel epanel)
    {
        setLayout(new GridLayout(1, 1)); // Panel composed of 1 row and 1 column
        /* Reference 3 -
        taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
        epanel.setLayout(new BoxLayout(epanel, BoxLayout.X_AXIS)); //Sets the panel at the center
        add(epanel); // Adds emailPanel to the main email panel
        /* end of reference 3 */
    }

}
