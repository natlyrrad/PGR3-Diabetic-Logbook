package QuestPage;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import drawingUI.logPage.LogUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class Questionnaire extends JPanel {

    String[] options = {"1", "2", "3"};
    JLabel[] questions =new JLabel[14];
    JButton back = new JButton("< Back");


    public Questionnaire() {
        GridBagLayout grid = new GridBagLayout();
        this.setLayout(grid);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

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


        questions[0]  = new JLabel("Questionnaire");
        questions[1] = new JLabel("Instructions: choose 1 for least likely and choose 3 for most likely ");
        questions[2] = new JLabel("1. I check my blood sugar levels daily");
        questions[3]= new JLabel("2. The food I choose to eat daily makes it easy to achieve optimal blood sugar levels.");
        questions[4] = new JLabel("3. I take my diabetes medication (e. g. insulin, tablets) as prescribed");
        questions[5]= new JLabel("4. I tend to eat foods rich in carbohydrates and sweets daily");
        questions[6] = new JLabel("5. I tend to avoid diabetes-related doctor appointments.");
        questions[7] = new JLabel("6. I strictly follow the dietary recommendations given by my doctor or diabetes specialist daily.");
        questions[8] = new JLabel("7. Sometimes I have real 'food binges' (not triggered by hypoglycaemia).");
        questions[9] = new JLabel("8. I do regular physical activity to achieve optimal blood sugar levels.");
        questions[10]= new JLabel("9. I tend to miss my dose of diabetes");
        questions[11] = new JLabel("10. I get good support system for it including  my doctors, family and friends");
        questions[12]= new JLabel("11. I tend to smoke daily");
        questions[13] = new JLabel("12. I have good diabetes self care");

        questions[0].setFont(new Font("Dialog", Font.BOLD, 16));
        questions[0].setForeground(Color.BLUE);
        questions[1].setFont(new Font("Dialog", Font.BOLD, 14));

        //positioning of components
        for (int i =  0; i < 14; i++) {
            constraints.gridx = 0;
            add(questions[i],constraints);
            System.out.println(i);
        }

        for (int i =  2; i < 14; i++) {
            constraints.gridx = 1;
            constraints.gridy = i;
            add(new JComboBox(options), constraints);
        }

        constraints.gridx = 1;
        constraints.gridy = 14;
        add(back,constraints);

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        String x = JComboBox.getSelectedItem().toString();
        if (x == "1")
        {
            count1++;
            System.out.println("The number of 1's chosen are:" + count1);
        }
        else if (x == "2")
        {
            count2++;
            System.out.println("The number of 2's chosen are:" + count2);
        }
       else if (x == "3")
        {
            count3++;
            System.out.println("The number of 3's chosen are:" + count3);
        }



    }


}












