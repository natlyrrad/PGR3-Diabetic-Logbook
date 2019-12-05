package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intenPanel extends JPanel{
    //create all components
    protected entry meds = new entry(" Enter Medication/ Insulin and dose: ");
    JLabel food = new JLabel("  Food diary: ");
    protected entry item = new entry(" Enter food and amount:  ");
    JButton addItem = new JButton("Add food type");
    JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    int counter;


    public intenPanel(){
        //
        GridBagLayout grid = new GridBagLayout();
        this.setLayout(grid);
        GridBagConstraints gridc = new GridBagConstraints();
        gridc.anchor = GridBagConstraints.WEST;
        gridc.insets = new Insets(1, 1, 1, 10);

        //set up itemPanel
        GridBagLayout layout = new GridBagLayout();
        itemPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(1, 1, 1, 10);          //From maria

        // position components
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        itemPanel.add(addItem, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        itemPanel.add(item, c);

        counter = 1;

        //button addItem
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                itemPanel.removeAll();
                System.out.println("remove all");
                counter = counter +1;
                itemPanel.add(addItem);
                for(int i=0; i<counter; i++){
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = i+2;
                    System.out.println(i);
                    itemPanel.add(new entry(" Enter food and amount:  "), c);
                }
                itemPanel.revalidate();
                itemPanel.repaint();
                itemPanel.setVisible(true);

                System.out.println("add entry");

                gridc.gridx = 0;
                gridc.gridy = 2;
                gridc.gridheight = counter;

            }
        });

        //add components with layout
        gridc.fill = GridBagConstraints.HORIZONTAL;
        gridc.gridx = 0;
        gridc.gridy = 0;
        add(meds, gridc);

        gridc.fill = GridBagConstraints.HORIZONTAL;
        gridc.gridx = 0;
        gridc.gridy = 1;
        add(food, gridc);

        gridc.fill = GridBagConstraints.HORIZONTAL;
        gridc.gridx = 0;
        gridc.gridy = 2;
        add(itemPanel, gridc);

//        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public void getData(){
        meds.getInfo();
//        food.getInfo();
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