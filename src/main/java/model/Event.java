package model;

/**
 * Created by Phornrawin on 27/8/2560.
 */
public class Event {
    private String name, topic, detail;

    public Event(String name, String topic, String detail) {
        setName(name);
        setTopic(topic);
        setDetail(detail);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
