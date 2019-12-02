package drawingUI.calendarPage;

import drawingUI.detailsPage.DetailsUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calendarPanel extends JPanel {
    static GraphicsConfiguration gc; // Class field containing config info

    //JLabel clabel = new JLabel("Date: ");
    //JTextField ctext = new JTextField(20);
    JButton bt = new JButton("Select");
    JButton btgraph = new JButton("Graph");
    JButton btedit = new JButton("Edit Details");

    public calendarPanel() {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        btgraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        newPanel.add(btgraph, constraints);

        constraints.gridx = 1;
        btedit.addActionListener(new ActionListener() {
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
        newPanel.add(btedit, constraints);

        JFrame f = new JFrame(gc); // Create a new JFrame
        f.setSize(500, 500);

        DatePicker dp = new DatePicker(f);

        //////////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        newPanel.add(dp, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        //////////////////////////////////////////////////////////////////////////////////////

        add(newPanel);
    }
}
