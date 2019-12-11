package drawingUI.logPage;

import javax.swing.*;
import java.lang.reflect.Array;

public class Testing{

    int size;

    public Testing(String[] entries){
        size = entries.length;
        String[][] data = new String[11][size];

        //for each entry
        for(int i = 0; i < size; i++){
            int j = 0;
            int col = 0;
            while(entries[i].charAt(j) != '%'){                  //% terminate!!
                char c = entries[i].charAt(j);
                if (c != ':') {
                    data[col][i] += c;
                    j = j+1;
                }
                if(c == ':'){
                    j = j+2;
                    col++;
                }
            }
        }


        createTables(size);
    }

    public JPanel createTables(int c){
        JPanel a = new JPanel();
        JTable b = new JTable();
        return a;
    }
}
