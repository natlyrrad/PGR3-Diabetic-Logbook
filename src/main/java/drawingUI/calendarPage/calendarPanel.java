package drawingUI.calendarPage; //Part of the calendarPage Package
import drawingUI.detailsPage.DetailsUIController; //Imports the details page
//Java classes imports (JDK)

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calendarPanel extends JPanel {
    static GraphicsConfiguration gc; // Class field containing config info
    // Declare the buttons included on the panel
    JButton btgraph = new JButton("Graph");
    JButton btedit = new JButton("Edit Details");

    public calendarPanel() {
        /* Reference 1 - taken from https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples */
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        btgraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action of the graph button (will take to graph panel)
            }
        });
        newPanel.add(btgraph, constraints);

        constraints.gridx = 1;
        /* Button Action: the "edit details button will take the user back to the details page
         with the filled in text fields previously entered, and will set the calendar frame
         as invisible */
        btedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reopen the Details page
                JFrame details_frame= new JFrame(gc); // Create a new JFrame
                details_frame.setSize(500,450);

                DetailsUIController uidetails = new DetailsUIController(details_frame);

                details_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        newPanel.add(btedit, constraints);

        // New frame to be used in DatePicker class
        JFrame f = new JFrame(gc); // Create a new JFrame
        f.setSize(500, 500);

        // Will open the DatePicker class (contains the calendar)
        DatePicker dp = new DatePicker(f);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        newPanel.add(dp, constraints);
        constraints.anchor = GridBagConstraints.CENTER;

        add(newPanel);
    }
}
