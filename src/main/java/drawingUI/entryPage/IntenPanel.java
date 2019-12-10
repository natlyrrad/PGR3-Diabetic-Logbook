package drawingUI.entryPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class IntenPanel<first> extends JPanel{
    // components for med
    JPanel medlog = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel mlabel = new JLabel("Insulin type: ");
    JLabel dlabel = new JLabel("Dose (units): ");
    String[] mstring = {"Rapid-acting", "Short-acting", "Immediate-acting", "Long-acting"};
    JComboBox meds = new JComboBox(mstring);
    JTextField dose = new JTextField(5);

    // components for food diary
    JPanel fdPanel = new JPanel(new GridBagLayout());
    FoodPanel fPanel = new FoodPanel();
    JLabel fdlabel = new JLabel("Food diary:");
    JButton addFood = new JButton("Add food type");

    //ArrayLists to collect data
    int counter = 0;
    ArrayList<String[]> foodList = new ArrayList<>();
    ArrayList<FoodPanel> entryList = new ArrayList<>();


    public IntenPanel(){
        //medLog layout
        medlog.add(mlabel);
        medlog.add(meds);
        medlog.add(dlabel);
        medlog.add(dose);

        //fdPanel layout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 10);          //From maria

        entryList.add(new FoodPanel());

        c.gridx = 0;
        c.gridy = 0;
        fdPanel.add(entryList.get(0), c);

        //intenpanel layout
        GridBagLayout gblayout = new GridBagLayout();
        this.setLayout(gblayout);
        GridBagConstraints c1 = new GridBagConstraints();
//        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        c1.insets = new Insets(5, 0, 5, 10);          //From maria

//        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.gridx = 0;
        c1.gridy = 0;
        add(medlog, c1);

        c1.gridy = 1;
        add(fdlabel, c1);

        c1.gridy = 2;
        add(addFood, c1);

        c1.gridy = 3;
        c1.gridheight = 5;
        add(fdPanel, c1);

        //add food button
        addFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter = counter + 1;
//                //obtain and save all info in array
//                for(int i=0; i<counter; i++){
//                    foodList.add(i, entryList.get(i).getData());
//                }
                entryList.add(counter, new FoodPanel());

                fdPanel.removeAll();
                for(int i=0; i<(counter+1); i++){
                    c.gridx = 0;
                    c.gridy = i+1;
                    fdPanel.add(entryList.get(i), c);
                }


                fdPanel.revalidate();
                fdPanel.repaint();
                fdPanel.setVisible(true);
            }
        });


        Border border = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(border);
        medlog.setBorder(border);
        fdPanel.setBorder(border);

    }

    public void getMed(){
        for(int i=0; i<(counter+1); i++){
            String[] m = {meds.getSelectedItem().toString(), dose.getText()};
            System.out.println(Arrays.toString(m));
        }
    }

    public void getFood(){
        for(int i=0; i<(counter+1); i++){
            foodList.add(i, entryList.get(i).getData());
            System.out.println(Arrays.toString(foodList.get(i)));
        }
    }
}


//senalka these are for you
//JLabel comments = new JLabel("  Additional Comments: ");
//JTextArea textbox = new JTextArea(1, 1);
//    //textArea
//    //https://stackoverflow.com/questions/42690425/jtextarea-border-in-java-swing
//    Border border = BorderFactory.createLineBorder(Color.BLACK);
//        textbox.setBorder(BorderFactory.createCompoundBorder(border,
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//        add(comments);
//        add(textbox);