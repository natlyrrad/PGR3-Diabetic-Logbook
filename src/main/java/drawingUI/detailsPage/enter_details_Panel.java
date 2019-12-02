package drawingUI.detailsPage;

import javax.swing.*;
import java.awt.*;

public class enter_details_Panel extends JPanel {

    public enter_details_Panel (detailsPanel dpanel, buttonPanel butpanel)
    {
        setLayout(new GridLayout(2, 1));

        //https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel
        dpanel.setLayout(new BoxLayout(dpanel, BoxLayout.X_AXIS));
        add(dpanel);

        add(butpanel);

    }

}
