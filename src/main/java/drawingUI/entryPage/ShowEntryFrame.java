package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;

public class ShowEntryFrame {
    EntryPanel mainPanel = new EntryPanel();
    JFrame frame= new JFrame("Blood Sugar Level Input");

    public ShowEntryFrame(){
//        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        frame.setSize(700,600);
        //https://www.daniweb.com/programming/software-development/threads/267500/trying-to-align-jpanel-to-top-left-of-jframe-using-gridbaglayout
        frame.setLayout( new BorderLayout() );
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public void show(){
        frame.setVisible(true);

    }

}
