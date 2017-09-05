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
    private  MainViewController mainView;


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
        if(topic.equals("")){
            showTopicWarning();
        }else{
            Event event = new Event(topic, detail, startTime);
            controller.addEventToSchedule(event);
            System.out.println("Add event to DB:\n" + event.toString());
            mainView.initTextArea();

            Stage stage = (Stage) textfieldTopic.getScene().getWindow();
            stage.close();
        }

    }
    public void showTopicWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Topic");
        alert.setHeaderText("Look, You have not entered the topic text field yet.");
        alert.setContentText("Please enter a message in the topic field.");
        alert.showAndWait();
    }

    public void setController(MainController controller){
        this.controller = controller;
        datePickerSelect.setValue(LocalDate.now());

    }

    public void setMainView(MainViewController mainView){
        this.mainView = mainView;
    }

}
