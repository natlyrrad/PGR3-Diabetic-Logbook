package drawingUI.logPage;

import drawingUI.QuestPage.Questionnaire;
import drawingUI.Graph.PlotGraph;
import drawingUI.calendarPage.CalendarUIController;
import drawingUI.detailsPage.DetailsUIController;
import drawingUI.entryPage.EntryUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static drawingUI.emailPage.emailPanel.etext;

public class loghistory extends JPanel {

    static GraphicsConfiguration gc; // Class field containing config info

    FlowLayout flayout = new FlowLayout();

    // Declare the buttons included on the panel
    JButton btgraph = new JButton("Graph");
    JButton calendar = new JButton("Calendar");
    JButton delete = new JButton("Delete");
    JButton newrow = new JButton("New");
    JButton Questionnaire = new JButton( " Questionnaire");
    JButton btedit = new JButton("Edit Details");
    table t = new table();

    public loghistory(){
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/FlowLayoutDemoProject/src/layout/FlowLayoutDemo.java
        JPanel bp = new JPanel(new GridLayout(1, 4));
        bp.setLayout(flayout);

        calendar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame cal_frame= new JFrame(gc); // Create a new JFrame
                cal_frame.setSize(600,400);

                CalendarUIController uical = new CalendarUIController(cal_frame);

                cal_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                cal_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        bp.add(calendar);

        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Deleting row of data");
            }
        });
        bp.add(delete);

        newrow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                EntryUIController frame = new EntryUIController();
                frame.show();

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame f = (JFrame) SwingUtilities.getRoot(component);
                f.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        bp.add(newrow);

        btgraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFrame g_frame= new JFrame(gc); // Create a new JFrame
//                g_frame.setSize(600,400);
//
//                PlotGraph uigraph = new PlotGraph("title", "id:123");
//
//                g_frame.add(uigraph);
//                g_frame.setVisible(true);
//                // This next line closes the program when the frame is closed
//                g_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                PlotGraph chart = new PlotGraph("title", SQLDatabase.pullAzure.pullUserID(etext.getText()));
                chart.pack();
                chart.setVisible(true);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */            }
        });
        bp.add(btgraph);

        /* Button Action: the "edit details button will take the user back to the details page
         with the filled in text fields previously entered, and will set the calendar frame
         as invisible */
        btedit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reopen the Details page
                JFrame details_frame= new JFrame(gc); // Create a new JFrame
                details_frame.setSize(500,450);

                DetailsUIController uidetails = new DetailsUIController(details_frame);

                details_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }
        });
        bp.add(btedit);

        constraints.gridx = 3;
        Questionnaire.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){


                System.out.println("View questionnaire");

                JFrame details_frame= new JFrame(gc); // Create a new JFrame
                details_frame.setSize(500,450);

                drawingUI.QuestPage.Questionnaire q = new Questionnaire();
                q.setVisible(true);
                details_frame.add(q);
                details_frame.setVisible(true);
                // This next line closes the program when the frame is closed
                details_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                /* Reference 2 - takn from http://www.java2s.com/Code/Java/Swing-JFC/GettheJFrameofacomponent.htm */
                Component component = (Component) e.getSource(); // Get the source of the current component (panel)
                // declare JFrame currently open as "frame"
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.setVisible(false); // set current open frame as invisible
                /* end of reference 2 */
            }

        });
        bp.add(Questionnaire);

        ///////////////////////////////////////////////////////////////////////////////////////

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(bp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(t, constraints);

        add(newPanel);
    }
}
