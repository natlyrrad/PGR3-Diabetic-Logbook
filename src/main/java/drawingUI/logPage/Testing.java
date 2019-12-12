package drawingUI.logPage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Testing{

    static GraphicsConfiguration gc; // Class field containing config info

    String[] colNames = {"Time", "BSL", "Food diary", "Medicine type", "Dose"};
    int size;

    public Testing(String[] entries){                                           //takes in String[] entries
        size = entries.length;
        String[][] data = new String[size][5];
        System.out.println(size);
        System.out.println(Arrays.deepToString(data));

        //for each entry
        for(int i = 0; i < (size); i++){
            data[i] = entries[i].split(";");
            System.out.println(i);
            System.out.println(Arrays.toString(data[i]));
//            for(int j = 0; j < 5; j++){
//            }
//            int j = 0;
//            int col = 0;
//            while(entries[i].charAt(j) != '%'){                  //% terminate!!
//                char c = entries[i].charAt(j);
//                if (c != ':') {
//                    data[col][i] += c;
//                    j = j+1;
//                }
//                if(c == ':'){
//                    j = j+2;
//                    col++;
//                }
//            }
        }


        JTable t = new JTable(data, colNames);
        JPanel p = new JPanel();

//        set table height for each row
        String[] food;
        for(int i = 0; i < size; i++){
            food = data[i][2].split(":");
            int c = food.length;
            int h = c*10;
            t.setRowHeight(i, h);
        }

        p.add(t.getTableHeader(), BorderLayout.NORTH);
        p.add(t);
        newFrame(p);
    }

    // word wrapping in jtable: https://stackoverflow.com/questions/37768335/how-to-word-wrap-inside-a-jtable-row

    public JPanel createTables(int c){
        JPanel a = new JPanel();
        JTable b = new JTable();
        return a;
    }

    public void newFrame(JPanel p){
        JFrame email_frame= new JFrame(gc); // Create a new JFrame that will contain the email frame
        email_frame.setSize(500,300); // set size of frame
        //Create a new class object that contains the email frame UI
        email_frame.add(p);
        //Set frame as VISIBLE
        email_frame.setVisible(true);
        //This next line closes the program when the frame is closed
        email_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

