package drawingUI.entryPage;//public class drawingUI.entry_page.CompUI {

import SQLDatabase.connectDB;
import jdk.nashorn.internal.objects.NativeRegExpExecResult;

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
        protected Entry meds = new Entry("Medication/ Insulin: ");
        protected Entry carbs = new Entry("Carbohydrates(g): ");

        public compPanel(){

            //add components with layout
            GridLayout layout = new GridLayout(0,1);
            this.setLayout(layout);
            add(meds);
            add(carbs);

            setBorder(BorderFactory.createLineBorder(Color.black));

        }
    }

//}


//        protected JLabel exercise = new JLabel("  Exercise Level:");
//        protected JSlider Exerciselevel = new JSlider(1,10);
//            Exerciselevel.setMinorTickSpacing(1);
//            Exerciselevel.setPaintTicks(true);
//ChangeListener ExerciselevelCL = new ChangeListener() {   //slider to indicate level of activity
//    @Override
//    public void stateChanged(ChangeEvent e) {
//        System.out.println("Slider 1 changed ");
//    }
//};
//            Exerciselevel.addChangeListener(ExerciselevelCL);
//            add(exercise);
//            add(Exerciselevel);
