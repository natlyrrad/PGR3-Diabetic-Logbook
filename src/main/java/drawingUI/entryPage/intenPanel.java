package drawingUI.entryPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import drawingUI.entryPage.entryPanel;

public class intenPanel extends JPanel{
    //create all components
    JLabel food = new JLabel("  Food diary: ");
    protected Entry item = new Entry(" Enter food and amount:  ");
    JButton addItem = new JButton("Add food type");
    JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    int counter;
    protected Entry med = new Entry("Enter Medication/ Insulin and dose: ");


    public intenPanel(){
        //set labels of entries

        //set up itemPanel
//        GridLayout itemLayout = new GridLayout(0,1);
//        itemPanel.setLayout(itemLayout);

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
                    itemPanel.add(new Entry(" Enter food and amount:  "), c);
                }
                itemPanel.revalidate();
                itemPanel.repaint();
                itemPanel.setVisible(true);

                System.out.println("add entry");


            }
        });

        //add components with layout
        GridLayout grid = new GridLayout(0,1);
        this.setLayout(grid);
        add(med);
        add(food);
        add(itemPanel);


        setBorder(BorderFactory.createLineBorder(Color.black));

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