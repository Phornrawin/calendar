package view;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Event;
import model.Schedule;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Phornrawin on 4/9/2560.
 */
public class EditAndDeleteViewController {
    @FXML private ChoiceBox choiceboxEvent;
    @FXML private DatePicker datePickerEdit;
    @FXML private Spinner spinerHr, spinerMins;
    @FXML private TextField textFieldTopic;
    @FXML private TextArea textAreaDetail;
    @FXML private Button btnEdit, btnDelete;
    private Event oldEvent;
    private MainController controller;
    private MainViewController mainView;

    @FXML
    public void initialize(){

    }
    public void addElementToChoice(Event element){
        choiceboxEvent.getItems().add(element);
        choiceboxEvent.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                Event event = (Event) object;
                return event.getDateToString() + " "+ event.getTopic();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });

        choiceboxEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(choiceboxEvent.getValue());
                oldEvent = (Event) choiceboxEvent.getValue();
                datePickerEdit.getEditor().setText(oldEvent.getDateToString());
                spinerHr.getEditor().setText(oldEvent.getDate().getHours() + "");
                spinerMins.getEditor().setText(oldEvent.getDate().getMinutes() + "");
                textFieldTopic.setText(oldEvent.getTopic());
                textAreaDetail.setText(oldEvent.getDetail());

            }
        });


    }

    @FXML
    public void onClickEdit(){
        LocalDate localDate = datePickerEdit.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date newDate = Date.from(instant);
        newDate.setHours((Integer) spinerHr.getValue());
        newDate.setMinutes((Integer) spinerMins.getValue());
        String newTopic = textFieldTopic.getText();
        String newDetail = textAreaDetail.getText();

        Event newEvent = new Event(newTopic, newDetail, newDate);

        controller.getSchedule().removeEvent(oldEvent);
        controller.getDbController().updateDatabase(oldEvent, newEvent);
        Schedule schedule = controller.getDbController().loadDatafromDB();
        controller.setSchedule(schedule);

        mainView.initTextArea();

        Stage stage = (Stage) textFieldTopic.getScene().getWindow();
        stage.close();


    }

    public void setController(MainController controller){
        this.controller = controller;
        Schedule schedule = controller.getSchedule();
        for (Event e: schedule.getEvents()) {
            addElementToChoice(e);
        }

    }

    public void setMainView(MainViewController mainView){

        this.mainView = mainView;
    }

}
