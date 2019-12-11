package drawingUI.entryPage;//public class drawingUI.entry_page.CompUI {

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CompPanel extends JPanel {
        //create all components
        protected Entry med = new Entry(" Medication/ Insulin: ");
        protected Entry carbs = new Entry(" Carbohydrates(g): ");

        public CompPanel(){
            //add components with layout
            GridLayout layout = new GridLayout(0,1);
            this.setLayout(layout);
            layout.setVgap(10);
            add(med);
            add(carbs);
        }

        public void getData(){
            System.out.println("Default med");
            med.getInfo();
            System.out.println("Default food type: Carbs");
            carbs.getInfo();
        }
    }