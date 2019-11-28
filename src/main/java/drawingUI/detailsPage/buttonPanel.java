package drawingUI.detailsPage;

import drawingUI.logPage.LogUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonPanel extends JPanel {

    JButton buttonLogin = new JButton("Enter Details");

    static GraphicsConfiguration gc; // Class field containing config info

    public buttonPanel(){
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame log_frame= new JFrame(gc); // Create a new JFrame
                log_frame.setSize(500,300);

                LogUIController uilog = new LogUIController(log_frame);

                log_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                log_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });

        newPanel.add(buttonLogin, constraints);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        add(newPanel);
    }
}
