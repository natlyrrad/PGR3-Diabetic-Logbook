package drawingUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logtypePanel extends JPanel {

    JLabel label = new JLabel("Pick Log Book Method:");

    JButton method1 = new JButton("Simple Method");
    JButton method2 = new JButton("Comprehensive Method");
    JButton method3 = new JButton("Intensive Method");

    public logtypePanel (){
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        ///////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(label, constraints);

        ///////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;

        method1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        newPanel.add(method1, constraints);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        ///////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;

        method2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        newPanel.add(method2, constraints);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        ///////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;

        method3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        newPanel.add(method3, constraints);

        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        ///////////////////////////////////////////////////////////////////////////////////////
        add(newPanel);
    }
}
