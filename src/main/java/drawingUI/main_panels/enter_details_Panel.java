package drawingUI.main_panels;

import drawingUI.details_page.buttonPanel;
import drawingUI.details_page.detailsPanel;
import drawingUI.logtypePanel;

import javax.swing.*;
import java.awt.*;

public class enter_details_Panel extends JPanel {

    public enter_details_Panel (detailsPanel dpanel, buttonPanel butpanel, logtypePanel lpanel)
    {
        setLayout(new GridLayout(2, 1));

        //https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel
        dpanel.setLayout(new BoxLayout(dpanel, BoxLayout.X_AXIS));
        add(dpanel);

        //add(lpanel);

        add(butpanel);

    }

}
