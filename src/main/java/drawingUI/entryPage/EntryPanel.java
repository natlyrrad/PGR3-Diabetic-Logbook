package drawingUI.entryPage;

import drawingUI.detailsPage.DetailsUIController;
import drawingUI.logPage.LogUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

import javaMailAPI.jakartaMailAPI;
import static SQLDatabase.pullAzure.*;
import static SQLDatabase.pushAzure.pushEntryDetails;
import static drawingUI.emailPage.emailPanel.etext;
import static drawingUI.logPage.table.ltext;


public class EntryPanel extends JPanel implements ActionListener{                        //shows date, select time, button to pick method, back and enter

    static GraphicsConfiguration gc; // Class field containing config info

    //create all components
    JLabel date = new JLabel();
    Entry time = new Entry(" Time: ");
    Entry bsl = new Entry(" Blood Sugar Level (MMol): ");
    JButton back = new JButton("< Back");
    JButton enter = new JButton("Enter");
    JButton localTime = new JButton("Current Time");
    JRadioButton simple = new JRadioButton("Simple Method", true);
    JRadioButton comp = new JRadioButton("Comprehensive Method");
    JRadioButton inten = new JRadioButton("Intensive Method");
    int met = 0;

    JFrame load = new JFrame();

    CompPanel p2 = new CompPanel();
    public IntenPanel p3 = new IntenPanel();

    //set patient id
    public String id = pullUserID(etext.getText());


    public EntryPanel(){
        //set labels of entries
        date.setText("  Date: " + ltext.getText());
        date.setForeground(Color.red);
        date.setFont(new Font("Dialog", Font.BOLD, 13));


        //button actions for back and enter
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //create new frame to loghistory
                JFrame logframe= new JFrame(); // Create a new JFrame
                logframe.setSize(700,900);

                LogUIController uilog = new LogUIController(logframe);

                logframe.setVisible(true);
                //This next line closes the program when the frame is closed
                logframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
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
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
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
                        //set date time as one single entry
                        String[] d = ltext.getText().split("/");
                String americanDate = String.join("/", d[2], d[1], d[0]);
                String dt = americanDate+ " " + time.getInfo();
                        //create three strings for three conditions
                        String m1 = String.join(";" , id, dt, bsl.getInfo(), "", "", "");
                        String m2 = String.join(";" , id, dt, bsl.getInfo(), p2.getFood(), p2.getMed(), "");
                        String m3 = String.join(";" , id, dt, bsl.getInfo(), p3.getFood(), p3.getMed(), "");

                        //Alert if blood sugar level is high
                        int ibsl= Integer.parseInt(bsl.getInfo());
                        if(ibsl>9){
                            jakartaMailAPI.printmessage();
                            jakartaMailAPI email=new jakartaMailAPI();
                            try {
                                email.sendMail(pullDoctorEmail(id));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        //push details under different condition
                        if (met == 0){
                            pushEntryDetails(m1);
                            System.out.println(m1);
                        }
                        if (met == 1){
                            pushEntryDetails(m2);
                            System.out.println(m2);
                        }
                        if (met == 2){
                            pushEntryDetails(m3);
                            System.out.println(m3);
                        }

                        //return to log page
                        JFrame logFrame= new JFrame(gc); // Create a new JFrame
                        logFrame.setSize(700,900);
                        load.setVisible(false);

                        LogUIController uihis = new LogUIController(logFrame);

                        logFrame.setVisible(true);
                        // This next line closes the program when the frame is closed
                        logFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                        /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                        Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                        // declare JFrame currently open as "frame"
                        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                        frame.setVisible(false); // set current open frame as invisible
                        /* end of reference 2 */
                        latch.countDown();
                    }
                }).start();
            }
        });


        //button actions to get local time
        localTime.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String t = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                time.setInfo(t);            //collect data to db
            }
        });

        //choosing input method
        simple.addActionListener(this);
        comp.addActionListener(this);
        inten.addActionListener(this);

        //grouping method radiobuttons
        ButtonGroup methods = new ButtonGroup();
        methods.add(simple);
        methods.add(comp);
        methods.add(inten);

        //create metPanel
        JPanel metPanel = new JPanel(new GridLayout(0,1));
        metPanel.add(simple);
        metPanel.add(comp);
        metPanel.add(inten);

        //add components with layout
        //https://examples.javacodegeeks.com/desktop-java/swing/java-swing-layout-example/
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);          //From maria


        // position components
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(back, c);

        c.gridx = 0;
        c.gridy = 1;
        add(date, c);

        c.gridx = 0;
        c.gridy = 2;
        add(time, c);

        c.gridx = 1;
        c.gridy = 2;
        add(localTime, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 2;
        add(metPanel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(bsl, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(p2, c);
        p2.setVisible(false);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 3;
        add(p3, c);
        p3.setVisible(false);

        c.gridx = 2;
        c.gridy = 6;
        add(enter, c);
    }

    //radio buttons to select method
    @Override
    public void actionPerformed(ActionEvent e) {
        if(simple.isSelected()){
//            JOptionPane.showMessageDialog(this,"You are Male.");
            p2.setVisible(false);
            p3.setVisible(false);
            met = 0;
        }
        if(comp.isSelected()){
//            JOptionPane.showMessageDialog(this,"You are Female.");
            p2.setVisible(true);
            p3.setVisible(false);
            met = 1;
        }
        if(inten.isSelected()){
//            JOptionPane.showMessageDialog(this,"Intensive");
            p2.setVisible(false);
            p3.setVisible(true);
            met = 2;
        }
    }
}
