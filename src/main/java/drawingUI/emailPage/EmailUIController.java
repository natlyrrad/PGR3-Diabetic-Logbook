package drawingUI.emailPage; //Includes the class in the emailPage package
//Java classes imports (JDK)
import javax.swing.*;

public class EmailUIController {
    // Declares an new emailPanel
    emailPanel epanel = new emailPanel();

    public EmailUIController(JFrame frame)
        {
             /* Reference 3 -
            taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
            epanel.setLayout(new BoxLayout(epanel, BoxLayout.X_AXIS)); //Sets the panel at the center
            // /* end of reference 3 */

            // Set the enter_email_panel as the contents in the frame (contains the emailPanel)
            frame.getContentPane().add(epanel);
        }
    }
