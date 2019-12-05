package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;

public class logHistoryPage extends JPanel {
    public logHistoryPage (loghistory log)
    {
        setLayout(new GridLayout(1, 1));

        add(log);
    }
}
