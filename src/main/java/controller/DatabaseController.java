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

    public DatabaseController() {

        dateFormat = new SimpleDateFormat("E dd MMM yyyy HH:mm", Locale.ENGLISH);
    }

    public static DatabaseController getInstance(){
        if(dbController == null){
            dbController = new DatabaseController();
        }
        return dbController;
    }

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

                    Event event = new Event(topic, detail, date);
                    schedule.addEvent(event);
                    String s = String.format("Topic: %s\n" + "Detail: %s\n" + "Date&Time: %s",topic,detail,date);
                    System.out.println(s);
                }
                return schedule;
            }
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

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

                String query = String.format("insert into event values (\'%s\', \'%s\', \'%s\')", date, topic, detail);
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
