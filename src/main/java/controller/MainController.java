package controller;

import model.Event;
import model.Schedule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Phornrawin on 28/8/2560.
 */
public class MainController {
    private Schedule schedule;
    private DatabaseController dbController;

    public void startControll(){
        ApplicationContext bf = new ClassPathXmlApplicationContext("maincon.xml");
        this.dbController = (DatabaseController) bf.getBean("dbController");
        this.schedule = dbController.loadDatafromDB();
    }

    /**
     * add event to schedule and database
     * @param event
     */
    public void addEventToSchedule(Event event){
        schedule.addEvent(event);
        dbController.addDatatoDB(event);
    }

    public DatabaseController getDbController() {
        return dbController;
    }

    public Schedule getSchedule() {

        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
