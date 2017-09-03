package view;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Event;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class AddEventViewController {
    @FXML private DatePicker datePickerSelect;
    @FXML private Spinner<Integer> spinnerHour, spinnerMins;
    @FXML private TextField textfieldTopic;
    @FXML private TextArea textAreaDetail;
    @FXML private Button btnAddEvent;
    private MainController controller;
    private SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy HH:mm z");


    @FXML
    public void initialize(){

    }

    @FXML
    public void onClickAddEvent(){
        LocalDate localDate = datePickerSelect.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date startTime = Date.from(instant);
        startTime.setHours(spinnerHour.getValue());
        startTime.setMinutes(spinnerMins.getValue());
        String topic = textfieldTopic.getText();
        String detail = textAreaDetail.getText();
//        String dateformat = format.format(date);

        Event event = new Event(topic, detail, startTime);
        controller.addEventToSchedule(event);
        System.out.println("Add event to DB:\n" + event.toString());

        Stage stage = (Stage) textfieldTopic.getScene().getWindow();
        stage.close();



    }

    public void setController(MainController controller){
        this.controller = controller;
        datePickerSelect.setValue(LocalDate.now());

    }
}
