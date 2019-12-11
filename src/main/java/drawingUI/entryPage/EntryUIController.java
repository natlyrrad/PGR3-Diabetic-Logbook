package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;

public class EntryUIController {
    EntryPanel mainPanel = new EntryPanel();
    JFrame frame= new JFrame("Blood Sugar Level Input");

    public EntryUIController(){
//        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
        frame.setSize(600,600);
        //https://www.daniweb.com/programming/software-development/threads/267500/trying-to-align-jpanel-to-top-left-of-jframe-using-gridbaglayout
        frame.setLayout( new BorderLayout() );
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public void show(String date){
        frame.setVisible(true);
        mainPanel.date.setText("  Date:  " + date );
    }

}
