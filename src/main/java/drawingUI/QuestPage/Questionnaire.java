package drawingUI.QuestPage;

import drawingUI.LoadingFrame;
import drawingUI.detailsPage.DetailsUIController;
import drawingUI.logPage.createAndShowLog;
import drawingUI.logPage.table;

import javax.security.auth.Refreshable;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static drawingUI.logPage.table.RefreshTable;

public class Questionnaire<max, count1, count2, count3> extends JPanel {
    private List<JComboBox> combos = new ArrayList<>();
    private JTextField resultField = new JTextField(10);
    String[] options = {"1", "2", "3"};
    JLabel[] questions =new JLabel[17];
    JButton back = new JButton("< Back");
    JPanel scoreboard = new JPanel();
    int sum = 0;

    LoadingFrame load = new LoadingFrame();

    public static JLabel score = new JLabel("14");

    public Questionnaire() {
        GridBagLayout grid = new GridBagLayout();
        this.setLayout(grid);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                /* Reference - https://stackoverflow.com/questions/34906220/running-two-tasks-at-the-same-time-in-java */
                CountDownLatch latch = new CountDownLatch(2);
                new Thread(new Runnable() {
                    public void run() {
                        load.createframe();

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        load.showframe();
                        latch.countDown();
                    }
                }).start();

                new Thread(new Runnable() {
                    public void run() {
                        //create new frame to loghistory
                        createAndShowLog uilog = new createAndShowLog();
                        table.enterQscore(score.getText());
                        RefreshTable();

                        load.setVisible(false);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */

                        latch.countDown();
                    }
                }).start();
            }
        });

        questions[0]  = new JLabel("Questionnaire");
        questions[1] = new JLabel("Instructions: choose 1 for least likely and choose 3 for most likely ");
        questions[2] = new JLabel("1. I check my blood sugar levels daily");
        questions[3]= new JLabel("2. There have not been major fluctuations in my weight (no unexplained weight losses.");
        questions[4] = new JLabel("3. I take my diabetes medication (e. g. insulin, tablets) as prescribed");
        questions[5]= new JLabel("4. I do not tend to eat foods rich in carbohydrates and sweets daily and experience extreme hunger");
        questions[6] = new JLabel("5. I try to make it to all my diabetes-related doctor appointments.");
        questions[7] = new JLabel("6. I strictly follow the dietary recommendations given by my doctor or diabetes specialist daily.");
        questions[8] = new JLabel("7. I have not experienced very dry skin levels.");
        questions[9] = new JLabel("8. I do regular physical activity to achieve optimal blood sugar levels.");
        questions[10]= new JLabel("9. I do not tend to get very tired quite often.");
        questions[11] = new JLabel("10. I get good support system for it including  my doctors, family and friends");
        questions[12]= new JLabel("11. I do not tend to smoke daily");
        questions[13] = new JLabel("12. I have not experienced sores that take a long time to heal");
        questions[14] = new JLabel ("13. I have not experienced sudden vision changes");
        questions[15] = new JLabel ("14. I have not recently experienced tingling in my hands or feet.");
        questions[16] = new JLabel ("12. I have good diabetes self care");

        questions[0].setFont(new Font("Dialog", Font.BOLD, 16));
        questions[0].setForeground(Color.BLUE);
        questions[1].setFont(new Font("Dialog", Font.BOLD, 14));

        //positioning of components
        for (int i =  0; i < 16; i++) {
            constraints.gridx = 0;
            add(questions[i],constraints);
            System.out.println(i);
        }

        JLabel title = new JLabel ("Your questionnaire score is: " );  //creating a new panel to add the two JComponents into, in a border layout.
        scoreboard.add(title);
        scoreboard.add(score);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
        scoreboard.setBorder(border);
        add(scoreboard);

        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // iterate through the list, adding the values
                for (JComboBox<Integer> jComboBox : combos) {
                    Integer selection = Integer.parseInt((String) jComboBox.getSelectedItem());   //Gets selected item from the combobox and converts it to an integer called selection,which is summed.
                    sum += selection;
                }
                score.setText(String.valueOf(sum));
            }
        };

        for (int i =  2; i < 16; i++) {     //creates a JComboBox per question. comboboxes are stored in an ArrayList.
            constraints.gridx = 1;
            constraints.gridy = i;

            JComboBox<String> combo = new JComboBox<>();
            combo.setModel(new DefaultComboBoxModel<>(options));
            combo.setSelectedItem(0);
            // add the ActionListener to the combo box
            combo.addActionListener(comboListener);
            // add the combo box to the list
            combos.add(combo);
            constraints.gridx = 1;
            constraints.gridy = i;
            add(combo, constraints);
        }

        constraints.gridx = 1;
        constraints.gridy = 16;
        add(back,constraints);
    }

}






