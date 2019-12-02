package drawingUI.detailsPage; //Part of the detailsPage Package
import drawingUI.calendarPage.CalendarUIController; // Imports the calendar page
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonPanel extends JPanel {
    // Declares a new Jbutton
    JButton buttonLogin = new JButton("Enter Details");

    static GraphicsConfiguration gc; // Class field containing config info

    public buttonPanel(){
        // New panel with constraints
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        /* Button action: when the button is clicked, the current frame (details page) will become
         invisible and the calendar page will open*/
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cal_frame= new JFrame(gc); // Create a new JFrame
                cal_frame.setSize(600,700);

                CalendarUIController uical = new CalendarUIController(cal_frame);

                cal_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                cal_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        // this panel only contains the button
        newPanel.add(buttonLogin, constraints);
        // Center the button
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        add(newPanel);
    }
}
