package drawingUI.logPage;

/*
Takes in string of data directly from the database and determines number of table needed
Pass data into RepTable
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShowEntries {
    static GraphicsConfiguration gc; // Class field containing config info

    int size;
    RepTable[] tables = new RepTable[5];
    ArrayList<JTable> t = new ArrayList<>();
    String[] col = {"time", "BSL", "medication", "dose", "food diary"};

    public ShowEntries(String[] entries){
        size = entries.length;
        String[][] data = new String[size][5];
        System.out.println(size);
        System.out.println(Arrays.deepToString(data));

        //for each entry
        for(int i = 0; i < (size); i++){
            data[i] = entries[i].split(";");
            System.out.println(i);
            System.out.println(Arrays.toString(data[i]));
            //tables[i].fill(data[i]);
            t.add(new JTable(data, col));
        }
    }
}
