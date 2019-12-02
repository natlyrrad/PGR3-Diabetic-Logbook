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
    protected Entry item = new Entry();
    JButton addItem = new JButton("+");
    JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel comments = new JLabel("  Additional Comments: ");
    JTextArea textbox = new JTextArea(1, 1);


    public intenPanel(){
        //set labels of entries
        item.newEntry(" Enter food and amount:  ");

        //set up itemPanel
        GridLayout itemLayout = new GridLayout(0,1);
        itemPanel.setLayout(itemLayout);
        itemPanel.add(item);
        itemPanel.add(addItem);

        //button addItem
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                itemPanel.remove(addItem);
                System.out.println("remove button");
                itemPanel.add(item);
                System.out.println("add entry");
                itemPanel.add(addItem);
            }
        });

        //add components with layout
        GridLayout layout = new GridLayout(0,1);
        this.setLayout(layout);
        add(food);
        add(itemPanel);
        add(comments);
        add(textbox);


        //textArea
        //https://stackoverflow.com/questions/42690425/jtextarea-border-in-java-swing
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        textbox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        setBorder(BorderFactory.createLineBorder(Color.black));

    }
}
