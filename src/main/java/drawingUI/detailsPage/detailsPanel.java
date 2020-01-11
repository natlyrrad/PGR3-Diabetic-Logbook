package drawingUI.detailsPage; //Part of the detailsPage Package
//Java classes imports (JDK)
import drawingUI.logPage.LogUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import static SQLDatabase.pushAzure.pushUserDetails;
import static drawingUI.emailPage.emailPanel.etext;

public class detailsPanel extends JPanel
{
    public String id = etext.getText();

    // Declares a new Jbutton
    JButton buttonLogin = new JButton("Enter Details");

    //Declare loading frame
    JFrame load = new JFrame();

    static GraphicsConfiguration gc; // Class field containing config info

    // Constructor of panel with all tabs included as parameters
    public detailsPanel(personalTab ptab, diabetesTab dtab, doctorTab doctab) {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        /* Reference 4 - taken from https://www.javatpoint.com/java-jtabbedpane */
        JTabbedPane tp = new JTabbedPane(); // create a tabbed Pane (will include all tabs)
        // Add all the tabs to the pane
        tp.add("Personal Details",ptab);
        tp.add("Diabetes Information",dtab);
        tp.add("Doctor Details",doctab);
        /* end of reference 4 */
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(tp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        /* Button action: when the button is clicked, the current frame (details page) will become
         invisible and the calendar page will open*/
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Reference - https://stackoverflow.com/questions/34906220/running-two-tasks-at-the-same-time-in-java */
                CountDownLatch latch = new CountDownLatch(2);
                new Thread(new Runnable() {
                    public void run() {
                        /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
                        ImageIcon loading = new ImageIcon("ajax-loader.gif");

                        JLabel loadlabel = new JLabel(" Connecting... ", loading, JLabel.CENTER);
                        loadlabel.setFont(new Font("Monospaced", Font.PLAIN, 18));

                        load.add(loadlabel);
                        load.getContentPane().setBackground( Color.white );

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        load.setSize(400, 300);
                        load.setVisible(true);
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        //Following called methods will store the input selections into strings

                        String detail = String.join(";", ptab.getPersonal(), dtab.getDiabetes(), doctab.getDoctor());
                        System.out.println(detail);
                        pushUserDetails(detail);

                //create new frame to loghistory
                JFrame logframe= new JFrame(gc); // Create a new JFrame
                logframe.setSize(700,900);

                        LogUIController uilog = new LogUIController(logframe);

                        load.setVisible(false);

                        logframe.setVisible(true);
                        //This next line closes the program when the frame is closed
                        logframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        latch.countDown();
                    }
                }).start();
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
