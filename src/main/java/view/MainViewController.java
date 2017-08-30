package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by Phornrawin on 30/8/2560.
 */
public class MainViewController {
    @FXML
    private DatePicker selectDateSpiner;

    @FXML
    private Label selectDayTextView;

    @FXML
    private TextArea showEventTextArea;

    @FXML
    private Button btnAddEvent;

    private MainApp mainApp;

}
