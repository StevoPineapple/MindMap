package mindmap;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Main extends Application {
    Stage stage;
    static private Scene scene;
    static private Pane pane;
    @Override
    public void start(Stage stage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("View.fxml"));
        stage.setTitle("MindMap");
        stage.setScene(new Scene(root));
        stage.show();
        Main.scene = stage.getScene();
        Main.pane = root;
    }

    public static Pane getPane()
    {
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
