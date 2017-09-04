package view;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.Event;
import model.Schedule;

import java.time.LocalDate;

/**
 * Created by Phornrawin on 4/9/2560.
 */
public class EditAndDeleteViewController {
    @FXML private ChoiceBox choiceboxEvent;
    @FXML private DatePicker datePickerEdit;
    @FXML private Spinner spinerHr, spinerMins;
    @FXML private TextField textFieldTopic;
    @FXML private TextArea textAreaDetail;
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
                Event e = (Event) choiceboxEvent.getValue();
                datePickerEdit.getEditor().setText(e.getDateToString());
                spinerHr.getEditor().setText(e.getDate().getHours() + "");
                spinerMins.getEditor().setText(e.getDate().getMinutes() + "");
                textFieldTopic.setText(e.getTopic());
                textAreaDetail.setText(e.getDetail());

            }
        });


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
