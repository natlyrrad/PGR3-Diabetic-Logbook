package drawingUI.calendarPage; //Part of the calendarPage Package
//Java classes imports (JDK)
import javax.swing.*;
import java.awt.*;

public class pick_date_Panel extends JPanel {

    public pick_date_Panel(calendarPanel cpanel)
    {
        setLayout(new GridLayout(1, 1));
        cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.X_AXIS));
        add(cpanel);
    }

}
