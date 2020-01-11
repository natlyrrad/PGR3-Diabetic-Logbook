package drawingUI.calendarPage; //Part of the calendarPage Package
//Java classes imports (JDK)
import drawingUI.detailsPage.DetailsUIController;
import drawingUI.logPage.LogUIController;
import drawingUI.logPage.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/* Reference 5 -  taken from https://examples.javacodegeeks.com/desktop-java/swing/java-swing-date-picker-example/
   (this class was taken from reference 5 and then modified to better suit the project) */

public class DatePicker extends JPanel
{
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year

    int cday = java.util.Calendar.getInstance().get(Calendar.DATE);; // Get current Day
    int cmonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH); // Get current Month
    int cyear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);; // Get current Year

    // Declare empty classes to start
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JButton[] button = new JButton[49]; // Declare an array of 49 buttons (grid of 7x7)

    JFrame load = new JFrame();

    public static JLabel clabel = new JLabel("Today's Date: ");
    public static JLabel dlabel = new JLabel();

    public DatePicker() {
        /* Reference 1 - taken from https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples */
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Declare the first panel that contains the textfield where the date will appear
        JPanel p1 = new JPanel(new GridLayout(1, 1));
        clabel.setFont(new Font("Dialog", Font.BOLD, 14));
        p1.add(clabel);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dlabel.setText(dateFormat.format(date));
        dlabel.setFont(new Font("Dialog", Font.BOLD, 14));
        p1.add(dlabel);

        // Declare the second panel that will contain the calendar grid
        String[] header = { "SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT" }; // Weekdays headers
        JPanel p2 = new JPanel(new GridLayout(7, 7));
        p2.setPreferredSize(new Dimension(600, 600)); //Dimensions of the calendar

        for (int x = 0; x < button.length; x++) {
            final int selection = x; // the selction = date position picked
            button[x] = new JButton(); // Make the dates on the calendar buttons
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white); // background of calendar
            button[x].setFont(new Font("Dialog", Font.BOLD, 16)); // Font of numbers on the calendar
            if (x > 6) // If the selection is not a header display the date (do action)#
            {
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        /* Reference - https://stackoverflow.com/questions/34906220/running-two-tasks-at-the-same-time-in-java */
                        CountDownLatch latch = new CountDownLatch(2);
                        new Thread(new Runnable() {
                            public void run() {
                                /* Reference loading frame - https://stackoverflow.com/questions/7634402/creating-a-nice-loading-animation */
                                ImageIcon loading = new ImageIcon("ajax-loader.gif");

                                JLabel loadlabel = new JLabel(" Connecting... ", loading, JLabel.CENTER);
                                loadlabel.setFont(new Font("Monospaced", Font.PLAIN, 18));

                                load.add(loadlabel);
                                load.getContentPane().setBackground( Color.white );

                                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                                Component component = (Component) ae.getSource(); // Get the source of the current component (panel)
                                // declare JFrame currently open as "frame"
                                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                                frame.setVisible(false); // set current open frame as invisible
                                /* end of reference 2 */

                                load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                load.setSize(400, 300);
                                load.setVisible(true);
                                latch.countDown();
                            }
                        }).start();

                        new Thread(new Runnable() {
                            public void run() {
                                day = button[selection].getActionCommand(); // the day = day number selected
                                dlabel.setText(setPickedDate()); // call the setPickedDate method below to display the date

                                JFrame logframe = new JFrame(); // Create a new JFrame
                                logframe.setSize(700, 900);

                                LogUIController uilog = new LogUIController(logframe);

                                logframe.setVisible(true);
                                //This next line closes the program when the frame is closed
                                logframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                                table.ltext.setText(setPickedDate());

                                load.setVisible(false);

                                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                                Component component = (Component) ae.getSource(); // Get the source of the current component (panel)
                                // declare JFrame currently open as "frame"
                                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                                frame.setVisible(false); // set current open frame as invisible
                                /* end of reference 2 */
                                latch.countDown();
                            }
                        }).start();

                    }
                });
            }
            if (x < 7) { // If the selection is a header, then set the text as the corresponding header
                button[x].setText(header[x]);
                button[x].setBackground(Color.lightGray);
                button[x].setForeground(Color.RED); // set color as blue
                button[x].setFont(new Font("Dialog", Font.BOLD, 14)); // edit font
            }
            // Add the components (buttons) to the second panel
            p2.add(button[x]);
        }

        // Declare a third panel that contains the next and previous buttons (as well as the month/year)
        JPanel p3 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        // Previous button action: decrease the month by 1 and display new date
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate(); //Display the new month calendar
            }
        });
        p3.add(previous); // Add previous button to panel
        l.setFont(new Font("Dialog", Font.PLAIN, 12)); //Set font of the moth displayed
        p3.add(l); // Display the moth and year
        // Next button action: increase the month by 1 and display new date
        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate(); //Display the new month calendar
            }
        });
        p3.add(next);

        // Add all three panels to the newPanel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(p1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(p2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(p3, constraints);

        displayDate();
        add(newPanel);
    }

    /* The following method will display the date selected as the corresponding calendar*/
    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
        {
            button[x].setText("" + day);
            if ((day == cday) && (cmonth == month) && (year == cyear))
            {
                button[x].setBackground(Color.pink);
                button[x].setForeground(Color.black);
            }
            else if ((month == (cmonth + 1)) || (month == (cmonth - 1)))
            {
                button[x].setBackground(Color.white);
                button[x].setForeground(Color.black);
            }
        }
        l.setText(sdf.format(cal.getTime()));

    }

    /* The following method will take in the selection and declare the date, month, and year in
     the format (DD-MM-YYYY) and will set the date as selected*/
    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd/MM/yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }

}


