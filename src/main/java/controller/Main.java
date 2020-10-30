package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Main class
 */
public class Main extends Application {

    private Stage primaryStage;

    /**
     * Entry point
     *
     * @param args application arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the primary stage
     *
     * @param primaryStage primary stage to be set
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    /**
     * Initializes the primary stage and shows the login view
     */
    public void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
            AnchorPane pane = loader.load();

            Scene scene = new Scene(pane);
            this.primaryStage.setTitle("NHPlus");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            LoginController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}