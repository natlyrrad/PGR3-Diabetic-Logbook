package drawingUI.detailsPage; //Part of the detailsPage Package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;

public class detailsPanel extends JPanel
{
    // Constructor of panel with all tabs included as parameters
    public detailsPanel(personalTab ptab, diabetesTab dtab, doctorTab doctab)
    {
        /* Reference 4 - taken from https://www.javatpoint.com/java-jtabbedpane */
        JTabbedPane tp = new JTabbedPane(); // create a tabbed Pane (will include all tabs)
        // Add all the tabs to the pane
        tp.add("Personal Details",ptab);
        tp.add("Diabetes Information",dtab);
        tp.add("Doctor Details",doctab);
        /* end of reference 4 */

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        add(tp);
    }
}
