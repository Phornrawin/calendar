package view;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;
import model.EventType;
import model.Schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class MainViewController{
    @FXML private TextArea showEventTextArea;
    @FXML private DatePicker datepickerSelectDay;
    private ArrayList<Event> events;
    private MainController controller;

    @FXML
    public void initialize(){

    }

    /**
     * set textArea is present
     */
    public void initTextArea(Date date){
        String s = "";
        events = new ArrayList<>();
        events.addAll(controller.getSchedule().getEvents());
        events.addAll(controller.getSchedule().getDailys());
        events.addAll(controller.getSchedule().getWeeklys());
        events.addAll(controller.getSchedule().getMonthlys());
        events.addAll(controller.getSchedule().getYearlys());
        ArrayList<Event> selectDays = showEventByDate(date, events);
        for(Event e : selectDays){
            s += e.toString();
            s += "=====================\n";
        }
        showEventTextArea.setText(s);
        showEventTextArea.setEditable(false);

    }

    public ArrayList<Event> showEventByDate(Date date, ArrayList<Event> events){
        ArrayList<Event> e = new ArrayList<>();
        for (Event i : events) {
            if (new SimpleDateFormat("E dd MMM yyyy").format(i.getDate()).equals(new SimpleDateFormat("E dd MMM yyyy").format(date))){
                e.add(i);
            }else if(i.getType().equals(EventType.DAILY)){
                e.add(i);
            }else if(i.getType().equals(EventType.WEEKLY) &&
                    new SimpleDateFormat("E").format(i.getDate()).equals(new SimpleDateFormat("E").format(date))){
                e.add(i);
            }else if(i.getType().equals(EventType.MONTHLY) &&
                    new SimpleDateFormat("dd").format(i.getDate()).equals(new SimpleDateFormat("dd").format(date))){
                e.add(i);
            }else if(i.getType().equals(EventType.YEARLY) &&
                    new SimpleDateFormat("dd MMM").format(i.getDate()).equals(new SimpleDateFormat("dd MMM").format(date))){
                e.add(i);
            }
        }
        return e;
    }


    @FXML
    public void onClickNewEvent(){
        try {
            System.out.println("In onClickNewEvent method in MainView");
            // Load root layout from fxml file.
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainViewController.class.getResource("/AddEventViewFX.fxml"));
            Pane mainLayout = (AnchorPane) loader.load();
            AddEventViewController addEventView = loader.getController();
            addEventView.setController(controller);
            addEventView.setMainView(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            secondStage.setScene(scene);
            secondStage.setResizable(false);
            secondStage.setTitle("New Event");
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickEditAndDelete(){
        try {
            System.out.println("In onClickEditAndDelete method in MainView");
            Stage editStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainViewController.class.getResource("/EditAndDeleteViewFX.fxml"));
            Pane layout = (AnchorPane) loader.load();
            EditAndDeleteViewController editView = loader.getController();
            editView.setController(controller);
            for (Event e: controller.getSchedule().getEvents()) {
                System.out.println("in onClickEdit method: \n" + e.toString());
            }
            editView.setMainView(this);

            Scene scene = new Scene(layout);
            editStage.setScene(scene);
            editStage.setResizable(false);
            editStage.setTitle("Edit and Delete Event");
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setOnActionForDatePicker(){
        datepickerSelectDay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate localDate = datepickerSelectDay.getValue();
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date selectDay = Date.from(instant);
                initTextArea(selectDay);
            }
        });
    }

    public void setController(MainController controller){
        this.controller = controller;
        datepickerSelectDay.setValue(LocalDate.now());
        setOnActionForDatePicker();
        LocalDate localDate = datepickerSelectDay.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date today = Date.from(instant);
        initTextArea(today);

    }

}
