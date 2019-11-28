package drawingUI.entryPage;

import javax.swing.*;

public class Entry extends JPanel {
    JLabel label1 = new JLabel();
//    DocumentListener date_l;
    JTextField info = new JTextField(10);

    public Entry(){
        add(label1);
        add(info);
    }

    public void newEntry(String prompt){
        label1.setText(prompt);
    }

    public void setInfo(String data){
        info.setText(data);
    }

    public String getInfo(){
        System.out.println(info.getText());                 //store data
        return null;
    }

}


