package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;

public class createAndShowLog
{
    static GraphicsConfiguration gc;
    loghistory log = new loghistory();

    public createAndShowLog()
    {
        JFrame logFrame= new JFrame(gc); // Create a new JFrame
        logFrame.setSize(700,900);

        logFrame.setVisible(true);
        // This next line closes the program when the frame is closed
        logFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Reference 3 - taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
//        log.setLayout(new BoxLayout(log, BoxLayout.X_AXIS)); //Sets the panel at the center
        logFrame.setLayout( new BorderLayout() );
        logFrame.add(log, BorderLayout.NORTH);
        /* end of reference 3 */
        logFrame.getContentPane().add(log);

//        if(change ==1){
//            log.changeQuest();
//        }
    }
}
