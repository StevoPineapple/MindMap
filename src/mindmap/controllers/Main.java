package mindmap.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import mindmap.nodes.Node;

public class Main {
    @FXML
    Pane mainPane;
    public void addEllipse(MouseEvent mouseEvent){
        if(mouseEvent.getClickCount()==1){
            mainPane.getChildren().add(Node.create("ellipse","Hello",mainPane));
        }
    }


}
