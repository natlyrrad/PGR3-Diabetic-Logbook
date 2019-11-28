package drawingUI.calendarPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calendarPanel extends JPanel
{
    static GraphicsConfiguration gc; // Class field containing config info

    JLabel clabel = new JLabel("Date: ");
    JTextField ctext = new JTextField(20);
    JButton bt = new JButton("Select");

    public calendarPanel()
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(clabel, constraints);

        constraints.gridx = 1;
        newPanel.add(ctext, constraints);

        constraints.gridx = 2;

        JFrame f= new JFrame(gc); // Create a new JFrame
        f.setSize(500,300);

        DatePicker dp = new DatePicker(f);

        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ctext.setText(dp.setPickedDate());
            }
        });
        newPanel.add(bt, constraints);
        //////////////////////////////////////////////////////////////////////////////////////
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        newPanel.add(dp, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        //////////////////////////////////////////////////////////////////////////////////////

        add(newPanel);
    }


}
