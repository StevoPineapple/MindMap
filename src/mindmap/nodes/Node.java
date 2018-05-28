package mindmap.nodes;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public abstract class Node extends StackPane{

    protected static ArrayList<Node> nodes= new ArrayList<Node>();
    protected Pane parent;
    public static Node create(String type,String text,Pane parent){
        Node n = NodeType.create(type,text);
        n.getStyleClass().add("node");
        n.parent=parent;
        nodes.add(n);
        return n;
    }
}
