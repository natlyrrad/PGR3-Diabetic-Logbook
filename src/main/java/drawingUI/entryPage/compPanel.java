package drawingUI.entryPage;//public class drawingUI.entry_page.CompUI {

import javax.swing.*;
import java.awt.*;

public class compPanel extends JPanel {
        //create all components
        protected entry med = new entry(" Medication/ Insulin: ");
        protected entry carbs = new entry(" Carbohydrates(g): ");

        public compPanel(){

            //add components with layout
            GridLayout layout = new GridLayout(0,1);
            this.setLayout(layout);
            layout.setVgap(10);
            add(med);
            add(carbs);
//            setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void getData(){
            med.getInfo();
            carbs.getInfo();
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
