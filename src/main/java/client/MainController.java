package client;

import common.DatabaseManager;
import common.model.Event;
import common.model.Schedule;
import server.DatabaseController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Phornrawin on 28/8/2560.
 */
public class MainController {
    private Schedule schedule;
    private DatabaseManager dbController;

    public void startControll() throws Exception{
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

    public DatabaseManager getDbController() {
        return dbController;
    }

    public void setDbController(DatabaseManager dbController) {
        this.dbController = dbController;
    }

    public Schedule getSchedule() {

        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
