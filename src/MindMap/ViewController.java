package mindmap;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;

public class ViewController {
    @FXML
    Pane pane;
    public void clicked(MouseEvent mouseEvent){

        System.out.println("Clicked");
        pane.getChildren().add(MMNode.create("Elps","text",pane));
    }
}
