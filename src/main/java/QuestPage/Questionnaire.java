package QuestPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class Questionnaire extends JPanel {

    String[] options = {"1", "2", "3"};

    public Questionnaire() {
        JPanel QN = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);



        JLabel q1 = new JLabel("I check my blood sugar levels daily");
        JLabel q2 = new JLabel("The food I choose to eat daily makes it easy to achieve optimal blood sugar levels.");
        JLabel q3 = new JLabel("I take my diabetes medication (e. g. insulin, tablets) as prescribed");
        JLabel q4 = new JLabel("I tend to eat foods rich in carbohydrates and sweets daily");
        JLabel q5 = new JLabel("I tend to avoid diabetes-related doctors’ appointments.");
        JLabel q6 = new JLabel("I strictly follow the dietary recommendations given by my doctor or diabetes specialist daily.");
        JLabel q7 = new JLabel("Sometimes I have real ‘food binges’ (not triggered by hypoglycaemia).");
        JLabel q8 = new JLabel("I do regular physical activity to achieve optimal blood sugar levels.");
        JLabel q9 = new JLabel("I tend to miss my dose of diabetes");
        JLabel q10 = new JLabel("I get good support system for it including  my doctors,family and friends");
        JLabel q11 = new JLabel("I tend to smoke daily");
        JLabel q12 = new JLabel("I have good diabetes self care");


        for (int i = 0; i < 12; i++) {
            constraints.gridx = 1;
            QN.add(new JComboBox(options));
        }
        constraints.gridx = 0;
        constraints.gridy = 0;
        QN.add(q1,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        QN.add(q2,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        QN.add(q3,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        QN.add(q4,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        QN.add(q5,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        QN.add(q6,constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        QN.add(q7,constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        QN.add(q8,constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        QN.add(q9,constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        QN.add(q10,constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        QN.add(q11,constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        QN.add(q12,constraints);


        add(QN);
    }




    }












