package QuestPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class Questionnaire extends JPanel {
    String[] options = {"1", "2", "3"};
    public ArrayList<String> Q = new ArrayList<String>();


    public Questionnaire() {
        JPanel QN = new JPanel();


        Q.add("I check my blood sugar levels daily");
        Q.add("The food I choose to eat daily makes it easy to achieve optimal blood sugar levels.");
        Q.add("I take my diabetes medication (e. g. insulin, tablets) as prescribed");
        Q.add("I tend to eat foods rich in carbohydrates and sweets daily");
        Q.add("I tend to avoid diabetes-related doctors’ appointments.");
        Q.add("I strictly follow the dietary recommendations given by my doctor or diabetes specialist daily.");
        Q.add("Sometimes I have real ‘food binges’ (not triggered by hypoglycaemia).");
        Q.add("I do regular physical activity to achieve optimal blood sugar levels.");
        Q.add("I tend to miss my dose of diabetes");
        Q.add("I get good support system for it including  my doctors,family and friends");
        Q.add("I tend to smoke daily");
        Q.add("I have good diabetes self care");


        add(QN);

    }

    public void getans() {

        int question = 0;
        for (int i = 0; i < 12; i++) {
            question++;
            System.out.println((question + 1) + Q.get(i));
            JComboBox a = new JComboBox(options);

        }


    }
}











