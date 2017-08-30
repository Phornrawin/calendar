package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class DateTable {
    private Date date;
    private ArrayList<Event> events;

    public DateTable(Date date) {
        this.date = date;
        this.events = new ArrayList<Event>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
