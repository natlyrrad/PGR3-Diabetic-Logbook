package drawingUI.calendarPage;

public class pick_date_Panel extends JPanel {

    public pick_date_Panel(calendarPanel cpanel)
    {
        setLayout(new GridLayout(1, 1));

        //https://stackoverflow.com/questions/5345609/java-align-jlabel-in-center-of-jpanel

        add(cpanel);
    }

}
