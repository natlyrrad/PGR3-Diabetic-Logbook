package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createAndShowLog
{
    static GraphicsConfiguration gc;
    public static Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    JFrame logFrame= new JFrame(gc); // Create a new JFrame

    public createAndShowLog()
    {
        logFrame.setSize(700,900);
//        logFrame.setVisible(true);
        // This next line closes the program when the frame is closed
        logFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void showToday(){
        loghistory today = new loghistory(dateFormat.format(date));

        logFrame.setLayout( new BorderLayout() );
        logFrame.add(today, BorderLayout.NORTH);
        logFrame.getContentPane().add(today);
        logFrame.setVisible(true);

    }
    public void showDate(String date){
        String[] a = date.split("/");
        System.out.println(a);
        String aDate = String.join("/", a[2] ,a[1] ,a[0]);
        loghistory dayLog = new loghistory(aDate);

        logFrame.setLayout( new BorderLayout() );
        logFrame.add(dayLog, BorderLayout.NORTH);
        logFrame.getContentPane().add(dayLog);
        logFrame.setVisible(true);

    }
}
