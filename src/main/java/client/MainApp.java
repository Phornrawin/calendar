package client; /**
 * Created by Phornrawin on 30/8/2560.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import client.view.MainViewController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private MainController controller;
    private MainViewController mainView;
    private Pane mainLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Appointment Schedule");
        ApplicationContext bf = new ClassPathXmlApplicationContext("client-config.xml");
        this.controller = (MainController) bf.getBean("server");

//        this.server.controller = new MainController();
        this.controller.startControll();
        initMainLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initMainLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/MainViewFX.fxml"));
            mainLayout = (AnchorPane) loader.load();
            mainView = loader.getController();
            mainView.setController(controller);

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Pane getMainLayout() {
        return mainLayout;
    }
}

