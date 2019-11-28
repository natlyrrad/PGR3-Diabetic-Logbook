package drawingUI.select_page;

import drawingUI.main_panels.select_log_Panel;

import javax.swing.*;

public class LogUIController {

    logtypePanel lpanel = new logtypePanel();

    public LogUIController(JFrame frame)
    {

        frame.getContentPane().add(new select_log_Panel(lpanel));
    }
}
