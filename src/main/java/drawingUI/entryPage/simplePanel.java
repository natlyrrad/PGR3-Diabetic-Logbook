package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;

public class simplePanel extends JPanel {

    //create component
    protected entry bsl = new entry("Blood Sugar Level: ");

    public simplePanel() {

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(layout);
        add(bsl);
    }

    public void getData(){
        bsl.getInfo();
    }
}
