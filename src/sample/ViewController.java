package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class ViewController {
    @FXML
    Pane pane = new Pane();
    public void clicked(MouseEvent mouseEvent){

        System.out.print("Clicked");
        pane.getChildren().add(new EllipseNode());
    }
}
