package drawingUI.emailPage; //Includes the class in the emailPage package
import drawingUI.LoadingFrame;
import drawingUI.detailsPage.DetailsUIController; //imports Details page
import drawingUI.logPage.createAndShowLog;
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import static SQLDatabase.pullAzure.pullUserID;

// States this class as an extension of JPanel, meaning it is a panel
public class emailPanel extends JPanel {
    //Declare classes, labels, text fields, and buttons
    JLabel elabel = new JLabel("Enter email: ");
    public static JTextField etext = new JTextField(20);  // text field of size 20
    JButton buttonLogin = new JButton("Login");

    LoadingFrame load = new LoadingFrame();

    static GraphicsConfiguration gc;
    //Class Constructor
    public emailPanel() {
        /* Reference 1 - taken from https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples */
        JPanel newPanel = new JPanel(new GridBagLayout()); // create a new panel with GridBagLayout manager
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add label to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(elabel, constraints);

        // add text to the panel
        constraints.gridx = 1;
        newPanel.add(etext, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Will center the button in the panel

        buttonLogin.addActionListener(new ActionListener() {
            /* The following action performed when clicking the button will open the details page
             and set the email page as invisible*/
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
                        boolean email_verify = SQLDatabase.pullAzure.verifyEmail(setEmail());

                        if (email_verify == false)
                        {
                            //Open a Details UI frame when the button is clicked
                            JFrame details_frame = new JFrame(gc); // Create a new JFrame
                            details_frame.setSize(500, 450);

                            /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                            Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                            // declare JFrame currently open as "frame"
                            JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                            frame.setVisible(false); // set current open frame as invisible
                            /* end of reference 2 */

                            DetailsUIController uidetails = new DetailsUIController(details_frame, 0);

                            details_frame.setVisible(true);

                            load.setVisible(false);

                            // This next line closes the program when the frame is closed
                            details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        }
                        else if (email_verify == true)
                        {
                            createAndShowLog uilog = new createAndShowLog();

                            load.setVisible(false);

                            /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                            Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                            // declare JFrame currently open as "frame"
                            JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                            frame.setVisible(false); // set current open frame as invisible
                            /* end of reference 2 */
                        }
                        latch.countDown();
                    }
                }).start();
            }
        });
        // add button to the panel
        newPanel.add(buttonLogin, constraints);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));
        // Will allign the panel to the frame
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        // add the panel to this frame
        add(newPanel);
        /* end of reference 1 */
    }

    public String setEmail()
    {
        String email = etext.getText();
        System.out.println("email: "+ email);
        return email;
    }

    public static String userID(){
        return pullUserID(etext.getText());
    }

}
