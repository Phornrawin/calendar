package controller;

import model.Event;
import model.Schedule;

/**
 * Created by Phornrawin on 28/8/2560.
 */
public class MainController {
    private Schedule schedule;
    private DatabaseController dbController;

    public void startControll(){
        this.dbController = DatabaseController.getInstance();
        this.schedule = dbController.loadDatafromDB();
    }
    public void addEventToSchedule(Event event){
        schedule.addEvent(event);
        dbController.addDatatoDB(event);
    }

    public Schedule getSchedule() {

        return schedule;
    }
}
