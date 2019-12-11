package drawingUI.detailsPage; //Part of the detailsPage Package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;

public class doctorTab extends JPanel {
    // Declare all the components included on the tab
    JLabel emaillabel = new JLabel("Email: ");
    public static JTextField emailtext = new JTextField(20);

    JLabel addresslabel = new JLabel("Address: ");
    public static JTextField addresstext = new JTextField(20);

    JLabel phonelabel = new JLabel("Emergency Phone: ");
    public static JTextField phonetext = new JTextField(20);

    public doctorTab(){
        /* Reference 1 - https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples */
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

    public String getDoctor()
    {
        String emailDoc = emailtext.getText();
        String addressDoc = addresstext.getText();
        String phoneDoc = phonetext.getText();
        String doc = String.join(";", emailDoc, addressDoc, phoneDoc);

        return doc;
    }

}
