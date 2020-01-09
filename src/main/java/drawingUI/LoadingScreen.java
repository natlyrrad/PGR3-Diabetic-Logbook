package drawingUI;

import javax.swing.*;

/* Reference Multithreading - https://medium.com/swlh/java-multithreading-b8dd4771a902*/
public class LoadingScreen extends Thread {
    @Override
    public void run(){
        /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
        JFrame frame = new JFrame("Test");

        ImageIcon loading = new ImageIcon("ajax-loader.gif");
        frame.add(new JLabel("loading... ", loading, JLabel.CENTER));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
