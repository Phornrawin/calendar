package view;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Event;

//import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class AddEventViewController {
    @FXML
    private DatePicker datePickerSelect;

    @FXML
    private Spinner<Integer> spinnerHour, spinnerMins;

    @FXML
    private TextField textfieldTopic;

    @FXML
    private TextArea textAreaDetail;

    @FXML
    private Button btnAddEvent;

    private MainController controller;

    public AddEventViewController() {
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void onClickAddEvent(){
//        Date date = Date.from(datePickerSelect.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String startHrs = spinnerHour.getValue().toString();
        String startmins = spinnerMins.getValue().toString();
        String topic = textfieldTopic.getText().toString();
        String detail = textAreaDetail.getText().toString();

//        Event event = new Event(topic, detail, startHrs, startmins);
//        if (controller.getSchedule().checkDate(date)){
//
//        }



    }

    public void setController(MainController controller){
        this.controller = controller;
    }
}
