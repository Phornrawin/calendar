package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Schedule {
    private ArrayList<DateTable> dateTables;


    public Schedule() {
        this.dateTables = new ArrayList<DateTable>();
    }

    public void addDateTable(DateTable dt){
        dateTables.add(dt);
    }

    public ArrayList<DateTable> getDateTables() {
        return dateTables;
    }

    public void setDateTables(ArrayList<DateTable> dateTables) {
        this.dateTables = dateTables;
    }
}
