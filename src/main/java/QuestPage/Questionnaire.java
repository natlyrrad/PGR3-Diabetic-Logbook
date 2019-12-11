package QuestPage;

import drawingUI.logPage.LogUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Questionnaire extends JPanel {

    String[] options = {"1", "2", "3"};
    JButton back = new JButton("< Back");

    public Questionnaire() {
        JPanel QN = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);


        JLabel header = new JLabel("Questionnaire");
        JLabel instr = new JLabel("1 = least likely and 3 = most likely ");
        JLabel q1 = new JLabel("1. I check my blood sugar levels daily");
        JLabel q2 = new JLabel("2. The food I choose to eat daily makes it easy to achieve optimal blood sugar levels.");
        JLabel q3 = new JLabel("3. I take my diabetes medication (e. g. insulin, tablets) as prescribed");
        JLabel q4 = new JLabel("4. I tend to eat foods rich in carbohydrates and sweets daily");
        JLabel q5 = new JLabel("5. I tend to avoid diabetes-related doctors’ appointments.");
        JLabel q6 = new JLabel("6. I strictly follow the dietary recommendations given by my doctor or diabetes specialist daily.");
        JLabel q7 = new JLabel("7. Sometimes I have real ‘food binges’ (not triggered by hypoglycaemia).");
        JLabel q8 = new JLabel("8. I do regular physical activity to achieve optimal blood sugar levels.");
        JLabel q9 = new JLabel("9. I tend to miss my dose of diabetes");
        JLabel q10 = new JLabel("10. I get good support system for it including  my doctors,family and friends");
        JLabel q11 = new JLabel("11. I tend to smoke daily");
        JLabel q12 = new JLabel("12. I have good diabetes self care");


        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //create new frame to loghistory
                JFrame logframe= new JFrame(); // Create a new JFrame
                logframe.setSize(800,900);

                LogUIController uilog = new LogUIController(logframe);

                logframe.setVisible(true);
                //This next line closes the program when the frame is closed
                logframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });

        for (int i = 0; i < 12; i++) {
            constraints.fill = GridBagConstraints.BOTH;
            constraints.gridy = i;
            for( int j = 0;j <14;j++) {
                QN.add(new JComboBox(options));
            }
        }
        constraints.gridx = 0;
        constraints.gridy = 1;
        QN.add(header,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        QN.add(instr,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        QN.add(q1,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        QN.add(q2,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        QN.add(q3,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        QN.add(q4,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        QN.add(q5,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        QN.add(q6,constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        QN.add(q7,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        QN.add(q8,constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        QN.add(q9,constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        QN.add(q10,constraints);

        constraints.gridx = 0;
        constraints.gridy = 13;
        QN.add(q11,constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        QN.add(q12,constraints);


        constraints.gridx = 14;
        constraints.gridy = 14;
        QN.add(back,constraints);


        add(QN);
    }




}












