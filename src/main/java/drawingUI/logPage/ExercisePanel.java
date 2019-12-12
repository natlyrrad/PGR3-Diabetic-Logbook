package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;

public class ExercisePanel extends JPanel{
    JLabel ex = new JLabel("Type of Exercise: ");
    String[] type = {"Cardio", "Strength", "Other Activities"};
    JComboBox exCombo = new JComboBox(type);
    JLabel d = new JLabel("Duration (min): ");
    JTextField duration = new JTextField(5);

    public ExercisePanel(){
        // set panel layout
        FlowLayout flayout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(flayout);
        add(ex);
        add(exCombo);
        add(d);
        add(duration);
    }

    public String dataEx(){
        String exa= exCombo.getSelectedItem().toString() + ":" + duration.getText();
        return exa;
    }
}
