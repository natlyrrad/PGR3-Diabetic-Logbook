package drawingUI.logPage;

import javax.swing.*;

public class LogUIController
{
    loghistory log = new loghistory();

    public LogUIController(JFrame frame)
    {
        /* Reference 3 - taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
        log.setLayout(new BoxLayout(log, BoxLayout.X_AXIS)); //Sets the panel at the center
        /* end of reference 3 */
        frame.getContentPane().add(log);
    }
}
