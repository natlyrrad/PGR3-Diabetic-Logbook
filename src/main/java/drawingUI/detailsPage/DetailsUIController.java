package drawingUI.detailsPage; //Part of the detailsPage Package
//Java classes imports (JDK)
import javax.swing.*;

public class DetailsUIController
{
    //Declare all the tabs wanted on the panel
    personalTab ptab = new personalTab();
    diabetesTab dtab = new diabetesTab();
    doctorTab doctab = new doctorTab();
    // Declare the panel with the tabs
    detailsPanel dpanel = new detailsPanel(ptab, dtab, doctab);

    public DetailsUIController(JFrame frame)
    {
        /* Reference 3 - taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
        dpanel.setLayout(new BoxLayout(dpanel, BoxLayout.X_AXIS)); //Sets the panel at the center
        /* end of reference 3 */

        // Generate the contents of the combined details panel and button into the frame
        frame.getContentPane().add(dpanel);
    }


}
