import controllers.MapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    private final static HashMap<Integer, AudioClip> clips = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("/views/sample.fxml"));
        primaryStage.setTitle("HAL 9000 Defender");
        primaryStage.setScene(new Scene(root, 1724, 1080));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
