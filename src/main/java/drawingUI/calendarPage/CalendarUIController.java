package drawingUI.calendarPage; //Part of the calendarPage Package
//Java classes imports (JDK)
import javax.swing.*;

import javax.swing.*;

public class CalendarUIController {
    //Declare the calendar component wanted on the panel
    DatePicker dp = new DatePicker();

    public CalendarUIController(JFrame frame)
    {
        /* Reference 3 - taken from https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel */
        dp.setLayout(new BoxLayout(dp, BoxLayout.X_AXIS)); //Sets the panel at the center
        /* end of reference 3 */
        // Generate the contents of the calendar into the frame
        frame.getContentPane().add(dp);
    }
}

