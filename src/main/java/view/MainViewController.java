package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class MainViewController{
    @FXML
    private DatePicker selectDateSpiner;

    @FXML
    private Label selectDayTextView;

    @FXML
    private TextArea showEventTextArea;

    @FXML
    private Button btnAddEvent;

    public MainViewController() {

    }

    @FXML
    public void initialize(){

    }



    @FXML
    public void onClickNewEvent(){
        try {
            // Load root layout from fxml file.
            Stage secondStage = new Stage();
            AnchorPane mainLayout;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainViewController.class.getResource("/AddEventViewFX.fxml"));
            mainLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            secondStage.setScene(scene);
            secondStage.show();
            secondStage.setResizable(false);
            secondStage.setTitle("New Event");
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
