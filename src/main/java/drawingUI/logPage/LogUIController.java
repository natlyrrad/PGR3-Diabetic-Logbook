package drawingUI.logPage;

import javax.swing.*;

public class LogUIController
{
    loghistory log = new loghistory();

    public LogUIController(JFrame frame)
    {
        frame.getContentPane().add(new logHistoryPage(log));
    }
}
