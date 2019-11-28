package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;

public class select_log_Panel extends JPanel {

    public select_log_Panel (logtypePanel lpanel)
    {
        setLayout(new GridLayout(1, 1));

        //https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel
        lpanel.setLayout(new BoxLayout(lpanel, BoxLayout.X_AXIS));
        add(lpanel);
    }

}
