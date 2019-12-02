package drawingUI.entryPage;//public class drawingUI.entry_page.CompUI {

import SQLDatabase.connectDB;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.sql.*;

    public class compPanel extends JPanel {
        //create all components

        protected Entry bsl = new Entry();
        protected Entry Carbs = new Entry();
        protected Entry Meds = new Entry();
        protected JSlider Exerciselevel = new JSlider(1,10);
        protected JLabel exercise = new JLabel("Exercise Level:");
        protected JPanel sliderPanel = new JPanel();

        public compPanel(){
            //set labels of entries
            bsl.newEntry("Blood Sugar Level: ");
            Carbs.newEntry("Carbohydrates(g): ");
            Meds.newEntry ("Medication/ Insulin: ");

            ChangeListener ExerciselevelCL = new ChangeListener() {   //slider to indicate level of activity
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Slider 1 changed ");
                }
            };
            Exerciselevel.addChangeListener(ExerciselevelCL);

            //add components with layout
            GridLayout layout = new GridLayout(0,1);
            this.setLayout(layout);
            add(bsl);
            add(Carbs);
            add(Meds);
            add(exercise);
            add(Exerciselevel);

            setBorder(BorderFactory.createLineBorder(Color.black));

        }
    }

//}
