package drawingUI.detailsPage;

import javax.swing.*;
import java.awt.*;

public class doctorTab extends JPanel {

    JLabel emaillabel = new JLabel("Email: ");
    JTextField emailtext = new JTextField(20);

    JLabel addresslabel = new JLabel("Address: ");
    JTextField addresstext = new JTextField(20);

    JLabel phonelabel = new JLabel("Emergency Phone: ");
    JTextField phonetext = new JTextField(20);

    public doctorTab(){
        // https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        //Email Field -----------------------------------------------------------------------------
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(emaillabel, constraints);

        constraints.gridx = 1;
        newPanel.add(emailtext, constraints);

        //Address Field -----------------------------------------------------------------------------
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(addresslabel, constraints);

        constraints.gridx = 1;
        newPanel.add(addresstext, constraints);

        //Phone Number Field -----------------------------------------------------------------------------
        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(phonelabel, constraints);

        constraints.gridx = 1;
        newPanel.add(phonetext, constraints);

        add(newPanel);
    }

}
