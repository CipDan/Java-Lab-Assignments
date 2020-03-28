package Lab6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Implementation of the application. Loads all the components, prepares the scene and displays the content.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        primaryStage.setTitle("Let's have fun drawing figures!");
        primaryStage.setScene(new Scene(root, 1400, 750));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
