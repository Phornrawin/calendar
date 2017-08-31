package model;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Event {
    private String topic, detail, startHrs, startMins;

    public String getStartHrs() {
        return startHrs;
    }

    public String getStartMins() {
        return startMins;
    }

    public Event(String topic, String detail, String startHrs, String startMins) {
        setTopic(topic);
        setDetail(detail);
        setStartHrs(startHrs);
        setStartMins(startMins);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStartHrs(String startHrs) {
        this.startHrs = startHrs;
    }

    public void setStartMins(String startMins) {
        this.startMins = startMins;
    }
}
