package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
