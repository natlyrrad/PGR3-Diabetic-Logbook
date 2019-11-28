package drawingUI.calendarPage;

import javax.swing.*;

public class CalendarUIController {

    calendarPanel cpanel = new calendarPanel();

    public CalendarUIController(JFrame frame)
    {
        frame.getContentPane().add(new pick_date_Panel(cpanel));
    }
}

