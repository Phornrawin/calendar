package common.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Event implements Serializable{
    private String topic, detail;
    private Date date;
    private SimpleDateFormat format;
    private String type;

    public String getType() {
        return type;
    }

    public Event(String topic, String detail, Date date, String type) {
        this.topic = topic;
        this.detail = detail;
        this.date = date;
        this.format = new SimpleDateFormat("E dd MMM yyyy HH:mm", Locale.ENGLISH);
        this.type = type;

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

    public String getDateToString(){
        return format.format(date);
    }

    public String toString(){
        return String.format("Date: %s\n" + "Topic: %s\n" + "Detail: %s\n" + "Type: %s\n", format.format(date), topic, detail, type);
    }


}
