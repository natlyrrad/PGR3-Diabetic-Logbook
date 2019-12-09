package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EntryPanel extends JPanel implements ActionListener{                        //shows date, select time, button to pick method, back and enter
    //create all components
    JLabel date = new JLabel();
    Entry time = new Entry(" Time: ");
    Entry bsl = new Entry(" Blood Sugar Level (MMol): ");
    JButton back = new JButton("< Back");
    JButton enter = new JButton("Enter");
    JButton localTime = new JButton("Current Time");
    JRadioButton simple = new JRadioButton("Simple Method", true);
    JRadioButton comp = new JRadioButton("Comprehensive Method");
    JRadioButton inten = new JRadioButton("Intensive Method");
    int met = 0;

    compPanel p2 = new compPanel();
    public intenPanel p3 = new intenPanel();


    public EntryPanel(){
        //set labels of entries
        date.setText("   Date:   " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy")));       //change into fetching date

        //button actions for back and enter
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Back to view");     //change once other pages are ready
            }
        });
        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println(date.getText());
                time.getInfo();
                bsl.getInfo();

                if (met == 1){
                    p2.getData();
                }
                if (met == 2){
                    p3.getMed();
                    p3.getFood();
                }
            }
        });


        //button actions to get local time
        localTime.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String t = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                time.setInfo(t);            //collect data to db
            }
        });

        //choosing input method
        simple.addActionListener(this);
        comp.addActionListener(this);
        inten.addActionListener(this);

        //grouping method radiobuttons
        ButtonGroup methods = new ButtonGroup();
        methods.add(simple);
        methods.add(comp);
        methods.add(inten);

        //create metPanel
        JPanel metPanel = new JPanel(new GridLayout(0,1));
        metPanel.add(simple);
        metPanel.add(comp);
        metPanel.add(inten);

        //add components with layout
        //https://examples.javacodegeeks.com/desktop-java/swing/java-swing-layout-example/
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);          //From maria


        // position components
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(back, c);

        c.gridx = 1;
        c.gridy = 1;
        add(date, c);

        c.gridx = 1;
        c.gridy = 2;
        add(time, c);

        c.gridx = 2;
        c.gridy = 2;
        add(localTime, c);

        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 2;
        add(metPanel, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(bsl, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(p2, c);
        p2.setVisible(false);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(p3, c);
        p3.setVisible(false);

        c.gridx = 3;
        c.gridy = 6;
        add(enter, c);

    }

    //radio buttons to select method
    @Override
    public void actionPerformed(ActionEvent e) {
        if(simple.isSelected()){
//            JOptionPane.showMessageDialog(this,"You are Male.");
//            System.out.println("You are Male");
            p2.setVisible(false);
            p3.setVisible(false);
            met = 0;
        }
        if(comp.isSelected()){
//            JOptionPane.showMessageDialog(this,"You are Female.");
            p2.setVisible(true);
            p3.setVisible(false);
            met = 1;
        }
        if(inten.isSelected()){
//            JOptionPane.showMessageDialog(this,"Intensive");
            p2.setVisible(false);
            p3.setVisible(true);
            met = 2;
        }
    }

}
