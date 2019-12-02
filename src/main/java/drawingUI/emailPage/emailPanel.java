package drawingUI.emailPage;

import drawingUI.detailsPage.DetailsUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class emailPanel extends JPanel {

    JLabel elabel = new JLabel("Enter email: ");
    JTextField etext = new JTextField(20);
    JButton buttonLogin = new JButton("Login");

    static GraphicsConfiguration gc;

    public emailPanel() {
        // https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(elabel, constraints);

        constraints.gridx = 1;
        newPanel.add(etext, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame details_frame= new JFrame(gc); // Create a new JFrame
                details_frame.setSize(500,700);

                DetailsUIController uidetails = new DetailsUIController(details_frame);

                details_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                //http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false);
            }
        });

        newPanel.add(buttonLogin, constraints);

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        add(newPanel);
    }

}
