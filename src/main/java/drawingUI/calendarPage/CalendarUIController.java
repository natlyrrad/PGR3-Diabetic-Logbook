package drawingUI.calendarPage; //Part of the calendarPage Package
//Java classes imports (JDK)
import javax.swing.*;

import javax.swing.*;

public class CalendarUIController {
    //Declare the calendar component wanted on the panel
    calendarPanel cpanel = new calendarPanel();

    public CalendarUIController(JFrame frame)
    {
        // Generate the contents of the calendar into the frame
        frame.getContentPane().add(new pick_date_Panel(cpanel));
    }
}

