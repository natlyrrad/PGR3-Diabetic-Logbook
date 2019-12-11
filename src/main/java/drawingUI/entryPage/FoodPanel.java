package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FoodPanel extends JPanel {
    JLabel ftlabel = new JLabel("Food type: ");
    JLabel falabel = new JLabel("Amount(g): ");
    String[] fstring = {"Comples Carbs (fibre)", "Simple Carbs (Sugar)", "Fats", "Protein", "Fruit and veg", "Dairy"};
    JComboBox foods = new JComboBox(fstring);
    JTextField amount = new JTextField(5);

    public FoodPanel(){
        // set panel layout
        FlowLayout flayout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(flayout);

        add(ftlabel);
        add(foods);
        add(falabel);
        add(amount);

    }

    public String getData(){
        String fa= foods.getSelectedItem().toString() + ":" + amount.getText();
        return fa;
    }
}
