package drawingUI.detailsPage;//Part of the detailsPage Package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

import static SQLDatabase.pullAzure.*;
import static SQLDatabase.pullAzure.pullDoctorPhone;
import static drawingUI.emailPage.emailPanel.etext;

public class diabetesTab extends JPanel {
    // Declare all the components that will be included in the tab
    JLabel typelabel = new JLabel("Select Type of Diabetes: ");
    public static JRadioButton type1 = new JRadioButton("Type 1");
    public static JRadioButton type2 = new JRadioButton("Type 2");

    JLabel insulinlabel = new JLabel("Insulin Type: ");
    public static JRadioButton insulin1 = new JRadioButton("Rapid-acting insulin");
    public static JRadioButton insulin2 = new JRadioButton("Short-acting insulin");
    public static JRadioButton insulin3 = new JRadioButton("Intermediate-acting insulin");
    public static JRadioButton insulin4 = new JRadioButton("Long-acting insulin");

    JLabel adminlabel = new JLabel("Insulin Administration Method: ");
    public static JRadioButton admin1 = new JRadioButton("Pen");
    public static JRadioButton admin2 = new JRadioButton("Injection");
    public static JRadioButton admin3 = new JRadioButton("Pump");

    ButtonGroup bg1 = new ButtonGroup();
    ButtonGroup bg2 = new ButtonGroup();
    ButtonGroup bg3 = new ButtonGroup();

    public diabetesTab(){
        /* Reference 1 - https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples */
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        //Diabetes Type Selection Field ----------------------------------------------------------------
        bg1.add(type1);
        bg1.add(type2);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(typelabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(type1 , constraints);

        constraints.gridx = 1;
        newPanel.add(type2 , constraints);

        type1.setSelected(true);

        //Insulin Type Selection Field ----------------------------------------------------------------
        bg2.add(insulin1);
        bg2.add(insulin2);
        bg2.add(insulin3);
        bg2.add(insulin4);

        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(insulinlabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        newPanel.add(insulin1 , constraints);

        constraints.gridx = 1;
        newPanel.add(insulin2 , constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        newPanel.add(insulin3 , constraints);

        constraints.gridx = 1;
        newPanel.add(insulin4 , constraints);

        insulin1.setSelected(true);

        //Insulin Adminstration Type Selection Field ----------------------------------------------------------------
        bg3.add(admin1);
        bg3.add(admin2);
        bg3.add(admin3);

        constraints.gridx = 0;
        constraints.gridy = 7;
        newPanel.add(adminlabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        newPanel.add(admin1 , constraints);

        constraints.gridx = 1;
        newPanel.add(admin2 , constraints);

        constraints.gridx = 2;
        newPanel.add(admin3 , constraints);

        admin1.setSelected(true);

        add(newPanel);
    }

    public String getDiabetes()
    {
        //get type of diabetes
        String a = getSelectedButtonText(bg1);
        //get insulin type
        String b = getSelectedButtonText(bg2);
        //get injection type
        String c = getSelectedButtonText(bg3);

        String d = String.join(";", a, b, c);
        return d;
    }

    public static void fillDiabetes(){
        String id = pullUserID(etext.getText());

        String type = pullDiabetesType(id);
        if (type == "Type 1")
        {
            type1.setSelected(true);
        }
        else
        {
            type2.setSelected(true);
        }

        String insulin = pullDiabetesInsulin(id);
        if (insulin == "Rapid-acting insulin")
        {
            insulin1.setSelected(true);
        }
        else if (insulin == "Short-acting insulin")
        {
            insulin2.setSelected(true);
        }
        else if (insulin == "Intermediate-acting insulin")
        {
            insulin3.setSelected(true);
        }
        else
        {
            insulin4.setSelected(true);
        }

        String admin = pullDiabetesAdmin(id);
        if (admin == "Pen")
        {
            admin1.setSelected(true);
        }
        else if (admin == "Injection")
        {
            admin2.setSelected(true);
        }
        else
        {
            admin3.setSelected(true);
        }
    }

    //https://stackoverflow.com/questions/201287/how-do-i-get-which-jradiobutton-is-selected-from-a-buttongroup
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    //end of reference
}

