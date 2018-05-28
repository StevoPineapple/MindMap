package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Test extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing lines");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10,10,10,10));

        Text t1 = new Text("Chess Game");
        root.setTop(t1);
        t1.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(t1, Pos.CENTER);

        Text t2 = new Text("Left");
        root.setLeft(t2);

        Text t3 = new Text("Right");
        root.setRight(t3);

        GridPane gp = new GridPane();
        root.setCenter(gp);
        gp.setStyle("-fx-background-color: #eeeeee");


        Text t4 = new Text("Bottom");
        root.setBottom(t4);



        Scene scene = new Scene(root,600,300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
