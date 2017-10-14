package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Schedule {
    private ArrayList<Event> events;
    private ArrayList<Event> dailys;
    private ArrayList<Event> weeklys;
    private ArrayList<Event> monthlys;
    private ArrayList<Event> yearlys;

    public Schedule() {
        this.events = new ArrayList<>();
        this.dailys = new ArrayList<>();
        this.weeklys = new ArrayList<>();
        this.monthlys = new ArrayList<>();
        this.yearlys = new ArrayList<>();
    }

    public void addEvent(Event event){
        if(EventType.EMPTY.equals(event.getType())){
            this.events.add(event);
        }else if(EventType.DAILY.equals(event.getType())){
            this.dailys.add(event);
        }else if(EventType.WEEKLY.equals(event.getType())){
            this.weeklys.add(event);
        }else if(EventType.MONTHLY.equals(event.getType())){
            this.monthlys.add(event);
        }else if(EventType.YEARLY.equals(event.getType())){
            this.yearlys.add(event);
        }
    }

    public ArrayList<Event> getEvents() {

        return events;
    }
    public ArrayList<Event> getDailys() {
        return dailys;
    }

    public ArrayList<Event> getWeeklys() {
        return weeklys;
    }

    public ArrayList<Event> getMonthlys() {
        return monthlys;
    }

    public ArrayList<Event> getYearlys() {
        return yearlys;
    }


    /**
     * remove event from arraylist
     * @param event
     */
    public void removeEvent(Event event){
        if(EventType.EMPTY.equals(event.getType())){
            for (Event e: events) {
                if(e.getDateToString().equals(event.getDateToString()) && e.getTopic().equals(event.getTopic())){
                    events.remove(e);
                    return;
                }
            }
        }else if(EventType.DAILY.equals(event.getType())){
            for (Event e: dailys) {
                if(e.getDateToString().equals(event.getDateToString()) && e.getTopic().equals(event.getTopic())){
                    dailys.remove(e);
                    return;
                }
            }
        }else if(EventType.WEEKLY.equals(event.getType())){
            for (Event e: weeklys) {
                if(e.getDateToString().equals(event.getDateToString()) && e.getTopic().equals(event.getTopic())){
                    weeklys.remove(e);
                    return;
                }
            }
        }else if(EventType.MONTHLY.equals(event.getType())){
            for (Event e: monthlys) {
                if(e.getDateToString().equals(event.getDateToString()) && e.getTopic().equals(event.getTopic())){
                    monthlys.remove(e);
                    return;
                }
            }
        }else if(EventType.YEARLY.equals(event.getType())){
            for (Event e: yearlys) {
                if(e.getDateToString().equals(event.getDateToString()) && e.getTopic().equals(event.getTopic())){
                    yearlys.remove(e);
                    return;
                }
            }
        }


    }
}
