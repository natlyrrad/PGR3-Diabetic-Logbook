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
    // Declare the button panel
    buttonPanel butpanel = new buttonPanel();

    public DetailsUIController(JFrame frame)
    {
        // Generate the contents of the combined details panel and button into the frame
        frame.getContentPane().add(new enter_details_Panel(dpanel, butpanel));
    }


}
