package model;

import java.util.Date;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Event {
    private String topic, detail;
    private Date date;



    public Event(String topic, String detail,Date date) {
        this.topic = topic;
        this.detail = detail;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
