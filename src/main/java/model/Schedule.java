package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Schedule {
    private ArrayList<Event> events;

    public Schedule() {

        this.events = new ArrayList();
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
