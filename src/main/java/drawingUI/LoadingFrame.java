package drawingUI;

import javax.swing.*;
import java.awt.*;

public class LoadingFrame extends JFrame {
    public void createframe(){
        /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
        ImageIcon loading = new ImageIcon("ajax-loader.gif");

        JLabel loadlabel = new JLabel(" Connecting... ", loading, JLabel.CENTER);
        loadlabel.setFont(new Font("Monospaced", Font.PLAIN, 18));

        add(loadlabel);
        getContentPane().setBackground( Color.white );
    }
    public void showframe(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}
