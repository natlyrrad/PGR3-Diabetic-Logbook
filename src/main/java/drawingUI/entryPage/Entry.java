package drawingUI.entryPage;

import javax.swing.*;
import java.awt.*;

public class Entry extends JPanel {
    JLabel label1 = new JLabel();
//    DocumentListener date_l;
    JTextField info = new JTextField(10);

    public Entry(String des){
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        this.setLayout(layout);
        add(label1);
        add(info);
        label1.setText(des);
    }

    public void renameEntry(String prompt){
        label1.setText(prompt);
    }

    public void setInfo(String data){
        info.setText(data);
    }

    public String getInfo(){
        String data = info.getText();
        return data;
    }

}


