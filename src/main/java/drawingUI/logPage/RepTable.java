package drawingUI.logPage;

/*
RepTable = Repeatable table for every entry
    Simple method: {time, BSL, default medication, default dose, null}
    Comprehensive: {time, BSL, medication, dose, null}
    Intensive: {time, BSL, medication, dose, food diary}
 */

import javax.swing.*;

public class RepTable {
    JTable t = new JTable();

    public RepTable(){
        //construct empty table
    }

    void fill(String[] data){
        // fill in data
    }
}
