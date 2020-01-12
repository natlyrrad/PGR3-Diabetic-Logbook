package drawingUI.detailsPage; //Part of the detailsPage Package
//Java classes imports (JDK)
import drawingUI.LoadingFrame;
import drawingUI.logPage.LogUIController;
import drawingUI.logPage.createAndShowLog;

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
    LoadingFrame load = new LoadingFrame();

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
                        load.createframe();

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.showframe();
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
                        createAndShowLog uilog = new createAndShowLog();

                        load.setVisible(false);

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
