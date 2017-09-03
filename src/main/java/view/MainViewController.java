package view;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Event;
import model.Schedule;

import java.io.IOException;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class MainViewController{
    @FXML private TextArea showEventTextArea;
    @FXML private Button btnAddEvent;
    @FXML private Button btnEditAndDelete;
    private MainController controller;
    private ObservableList<Event> data;

    @FXML
    public void initialize(){

    }

    public void initTextArea(){
        String s = "";
        Schedule schedule = controller.getSchedule();
        for(Event e : schedule.getEvents()){
            s += e.toString();
            s += "=====================\n";
        }
        showEventTextArea.setText(s);
        showEventTextArea.setEditable(false);

    }



    @FXML
    public void onClickNewEvent(){
        try {
            System.out.println("In onClickNewEvent in MainView");
            // Load root layout from fxml file.
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainViewController.class.getResource("/AddEventViewFX.fxml"));
            Pane mainLayout = (AnchorPane) loader.load();
            AddEventViewController addEventView = loader.getController();
            addEventView.setController(controller);


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

    public void setController(MainController controller){
        this.controller = controller;
        this.data = FXCollections.observableList(controller.getSchedule().getEvents());
        initTextArea();

    }
}
