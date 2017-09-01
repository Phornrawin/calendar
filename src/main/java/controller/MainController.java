package controller;

import model.DateTable;
import model.Schedule;

/**
 * Created by Phornrawin on 28/8/2560.
 */
public class MainController {
    private Schedule schedule;

    public MainController() {

        schedule = new Schedule();
    }

    public void addEventToDateTable(DateTable date){

        schedule.addDateTable(date);
    }

    public Schedule getSchedule() {

        return schedule;
    }
}
