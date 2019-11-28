package drawingUI.detailsPage;

import javax.swing.*;
import java.awt.*;

public class detailsPanel extends JPanel {

    public detailsPanel(personalTab ptab, diabetesTab dtab, doctorTab doctab)
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        //https://www.javatpoint.com/java-jtabbedpane
        JTabbedPane tp = new JTabbedPane();
        tp.add("Personal Details",ptab);
        tp.add("Diabetes Information",dtab);
        tp.add("Doctor Details",doctab);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        add(tp);
    }
}
