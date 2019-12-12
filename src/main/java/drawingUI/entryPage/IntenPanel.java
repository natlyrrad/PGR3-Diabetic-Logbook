package drawingUI.entryPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    ArrayList<String> foodList = new ArrayList<>();
    ArrayList<FoodPanel> entryList = new ArrayList<>();


    public IntenPanel(){

        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

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
        c1.insets = new Insets(1, 1, 1, 1);          //From maria

        c1.gridx = 0;
        c1.gridy = 0;
        newPanel.add(medlog, c1);

        c1.gridy = 1;
        newPanel.add(fdlabel, c1);

        c1.gridy = 2;
        newPanel.add(addFood, c1);

        c1.gridy = 3;
        c1.gridheight = 5;
        newPanel.add(fdPanel, c1);

        //add food button
        addFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter = counter + 1;
                entryList.add(counter, new FoodPanel());

                //remove all then add new components
                fdPanel.removeAll();
                for(int i=0; i<(counter+1); i++){
                    c.gridx = 0;
                    c.gridy = i+1;
                    fdPanel.add(entryList.get(i), c);
                }

                //revalidate and display new fdPanel
                fdPanel.revalidate();
                fdPanel.repaint();
                fdPanel.setVisible(true);
            }
        });

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        medlog.setBorder(border);
        fdPanel.setBorder(border);

        JScrollPane scrollPane = new JScrollPane(newPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        add(scrollPane);
    }

    String getMed(){
        String m = meds.getSelectedItem().toString() +";" + dose.getText();
        return m;
    }

    String getFood(){
        String listString = new String();
        for(int i=0; i<(counter+1); i++){
            foodList.add(i, entryList.get(i).getData());
            listString += (foodList.get(i) + ", ");
        }
        return listString;
    }
}

