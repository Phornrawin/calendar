package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Schedule {
    private ArrayList<Date> dateArrayList;

    public Schedule() {
        this.dateArrayList = new ArrayList<Date>();
    }

    public void addDate(Date date){
        dateArrayList.add(date);
    }

    public ArrayList<Date> getDateArrayList() {
        return dateArrayList;
    }

    public void setDateArrayList(ArrayList<Date> dateArrayList) {
        this.dateArrayList = dateArrayList;
    }
}
