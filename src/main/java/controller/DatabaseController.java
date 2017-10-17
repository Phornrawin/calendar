package controller;

import model.Event;
import model.Schedule;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Phornrawin on 2/9/2560.
 */
public class DatabaseController {
    private static DatabaseController dbController;
    private SimpleDateFormat dateFormat;

    private DatabaseController() {

        dateFormat = new SimpleDateFormat("E dd MMM yyyy HH:mm", Locale.ENGLISH);
    }

    public static DatabaseController getInstance(){
        if(dbController == null){
            dbController = new DatabaseController();
        }
        return dbController;
    }

    /**
     * load record from database for build schedule
     * @return schedule
     */
    public Schedule loadDatafromDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if(conn != null){
                System.out.println("Connected to the database....");

                String query = "Select * from event";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                Schedule schedule = new Schedule();
                while(resultSet.next()){
                    Date date = dateFormat.parse(resultSet.getString(1));
                    String topic = resultSet.getString(2);
                    String detail = resultSet.getString(3);
                    String type = resultSet.getString(4);

                    Event event = new Event(topic, detail, date, type);
                    schedule.addEvent(event);
                    String s = String.format("Topic: %s\n" + "Detail: %s\n" + "Date&Time: %s\n" + "Type: %s",topic,detail,date,type);
                    System.out.println(s);
                }
                conn.close();
                return schedule;

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * add record to database
     * @param event
     *  @return boolean
     */
    public boolean addDatatoDB(Event event) {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if(conn != null){
                System.out.println("Connected to the database....");
                String date = dateFormat.format(event.getDate());
                String topic = event.getTopic();
                String detail = event.getDetail();
                String type = event.getType();

                String query = String.format("insert into event values (\'%s\', \'%s\', \'%s\', \'%s\')", date, topic, detail, type);
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                conn.close();

                return true;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * update record when record was edited
     * @param oldEvent
     * @param newEvent
     * @return boolean
     */
    public boolean updateDatabase(Event oldEvent, Event newEvent){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if(conn != null){
                System.out.println("Connected to the database...");
                String oldEventDate = dateFormat.format(oldEvent.getDate());
                String oldEventTopic = oldEvent.getTopic();

                String newEventDate = dateFormat.format(newEvent.getDate());
                String newEventTopic = newEvent.getTopic();
                String newEventDetail = newEvent.getDetail();
                String newEventType = newEvent.getType();

                String query = String.format("update event set date=\'%s\', topic=\'%s\', detail=\'%s\', type=\'%s\' where date=\'%s\' and topic=\'%s\'",
                        newEventDate, newEventTopic, newEventDetail, newEventType, oldEventDate, oldEventTopic);
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                conn.close();

                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * delete record from database
     * @param event
     * @return boolean
     */
    public boolean deleteDataFromDatabase(Event event){
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:Schedule.db";
            Connection conn = DriverManager.getConnection(dbURL);

            if(conn != null){
                System.out.println("Connected to the database...");
                String date = dateFormat.format(event.getDate());
                String topic = event.getTopic();

                String query = String.format("DELETE FROM event WHERE date=\'%s\' and topic=\'%s\'", date, topic);
                System.out.println(query);
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);

                conn.close();

                return true;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
