package common.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Phornrawin on 4/9/2560.
 */
public class EventTest {
    private String topic;
    private String detail;
    private Date date;
    private Event event;
    private String type;

    @Before
    public void setup(){
        topic = "topic test";
        detail = "detail test";
        type = EventType.EMPTY;
        date = new Date();
        event = new Event(topic, detail, date, type);
    }

    @Test
    public void testGetTopic(){
        assertEquals(topic, event.getTopic());
    }

    @Test
    public void testGetDetail(){
        assertEquals(detail, event.getDetail());
    }

    @Test
    public void testSetDetail(){
        String detail = "detail change";
        event.setDetail(detail);
        assertEquals(detail, event.getDetail());
    }
}