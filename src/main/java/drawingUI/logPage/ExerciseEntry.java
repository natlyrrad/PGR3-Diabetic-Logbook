package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;

public class ExerciseEntry extends JPanel{
    JLabel ex = new JLabel("Type of Exercise: ");
    String[] type = {"Cardio", "Strength", "Other Activities"};
    JComboBox exCombo = new JComboBox(type);
    JLabel d = new JLabel("Duration (min): ");
    JTextField duration = new JTextField(5);

    public ExerciseEntry(){
        // set panel layout
        FlowLayout flayout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(flayout);
        add(ex);
        add(exCombo);
        add(d);
        add(duration);
    }

    ///// get exercise data method
    public String dataEx(){
        String exa= exCombo.getSelectedIndex() + ":" + duration.getText();
        return exa;
    }

    public void setEx(String e, String dur){
        exCombo.setSelectedIndex(Integer.parseInt(e));
        duration.setText(dur);
    }
}
