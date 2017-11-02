package common;

import common.model.Event;
import common.model.Schedule;

import java.sql.Connection;

public interface DatabaseManager {
    Schedule loadDatafromDB();
    boolean addDatatoDB(Event event);
    boolean updateDatabase(Event oldEvent, Event newEvent);
    boolean deleteDataFromDatabase(Event event);
    Connection setURLDatabase(String url);
}
