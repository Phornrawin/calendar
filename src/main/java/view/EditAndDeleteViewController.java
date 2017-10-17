package view;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Event;
import model.EventType;
import model.Schedule;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Phornrawin on 4/9/2560.
 */
public class EditAndDeleteViewController {
    @FXML private ChoiceBox choiceboxEvent;
    @FXML private DatePicker datePickerEdit;
    @FXML private Spinner spinnerHr, spinnerMins;
    @FXML private ChoiceBox choiceBoxRepeat;
    @FXML private TextField textFieldTopic;
    @FXML private TextArea textAreaDetail;
    private Event oldEvent;
    private MainController controller;
    private MainViewController mainView;

    @FXML
    public void initialize(){

    }

    /**
     * add event to choicebox
     * @param element
     */
    public void addElementToChoice(Event element){
        choiceboxEvent.getItems().add(element);
        choiceboxEvent.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                Event event = (Event) object;
                System.out.println(event.getDateToString() + " "+ event.getTopic());
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
                LocalDate oldLocalDate = oldEvent.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                datePickerEdit.setValue(oldLocalDate);

                int oldHr = Integer.parseInt(new SimpleDateFormat("HH").format(oldEvent.getDate()));
                int oldMin = Integer.parseInt(new SimpleDateFormat("mm").format(oldEvent.getDate()));
                spinnerHr.getEditor().setText(oldHr + "");
                spinnerMins.getEditor().setText(oldMin + "");
                choiceBoxRepeat.getSelectionModel().select(oldEvent.getType());
                textFieldTopic.setText(oldEvent.getTopic());
                textAreaDetail.setText(oldEvent.getDetail());

            }
        });

    }

    /**
     * set action listener edit button
     */
    @FXML
    public void onClickEdit(){
        LocalDate localDate = datePickerEdit.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date newDate = Date.from(instant);
        newDate.setHours((Integer) spinnerHr.getValue());
        newDate.setMinutes((Integer) spinnerMins.getValue());
        String newType = choiceBoxRepeat.getValue().toString();
        String newTopic = textFieldTopic.getText();
        String newDetail = textAreaDetail.getText();
        if(newTopic.equals("")){
            showTopicWarning();
        }else{
            if(showEditWarning()){
                Event newEvent = new Event(newTopic, newDetail, newDate, newType);

                controller.getSchedule().removeEvent(oldEvent);
                controller.getDbController().updateDatabase(oldEvent, newEvent);
                Schedule schedule = controller.getDbController().loadDatafromDB();
                controller.setSchedule(schedule);

                mainView.initTextArea(new Date());

                Stage stage = (Stage) textFieldTopic.getScene().getWindow();
                stage.close();
            }else {
                return;
            }
        }
    }

    /**
     * set action listener delete button
     */
    @FXML
    public void onClickDelete(){
        System.out.println("in onClickDelete method");
        if(showEditDelete()){
            controller.getSchedule().removeEvent(oldEvent);
            controller.getDbController().deleteDataFromDatabase(oldEvent);
            Schedule schedule = controller.getDbController().loadDatafromDB();
            controller.setSchedule(schedule);
            mainView.initTextArea(new Date());

            Stage stage = (Stage) textFieldTopic.getScene().getWindow();
            stage.close();
        }else {
            return;
        }


    }
    /**
     * create topic warning alert dialog
     */

    public void showTopicWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Topic");
        alert.setHeaderText("Look, You have not entered the topic text field yet.");
        alert.setContentText("Please enter a message in the topic field.");
        alert.showAndWait();
    }

    /**
     * create edit warning alert dialog
     */
    public boolean showEditWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Edit");
        alert.setHeaderText("Do you want to fix?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

    /**
     * create delete warning alert dialog
     */
    public boolean showEditDelete(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning delete");
        alert.setHeaderText("Do you want to delete?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            return true;
        }else{
            return false;
        }
    }

    /**
     * add event type to choicebox
     */
    public void addElementToChoiceRepeat(){
        String eventTypes[] = {EventType.EMPTY, EventType.DAILY, EventType.WEEKLY, EventType.MONTHLY, EventType.YEARLY};
        for(int i = 0; i < eventTypes.length; i++){
            choiceBoxRepeat.getItems().add(eventTypes[i]);
            System.out.println();
        }
        choiceBoxRepeat.getSelectionModel().selectFirst();

    }

    /**
     * get and add event list to choicebox
     * @param events
     */
    public void getListEvent(ArrayList<Event> events){
        for (Event e: events) {
            System.out.println("add to edit: \n" + e.toString());
            addElementToChoice(e);
            }
        }

    public void setController(MainController controller){
        this.controller = controller;

        getListEvent(this.controller.getSchedule().getEvents());
        getListEvent(this.controller.getSchedule().getDailys());
        getListEvent(this.controller.getSchedule().getMonthlys());
        getListEvent(this.controller.getSchedule().getWeeklys());
        getListEvent(this.controller.getSchedule().getYearlys());

        choiceboxEvent.getSelectionModel().selectFirst();
        addElementToChoiceRepeat();


    }

    public void setMainView(MainViewController mainView){

        this.mainView = mainView;
    }

}
