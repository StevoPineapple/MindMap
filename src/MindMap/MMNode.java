package MindMap;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public abstract class MMNode extends StackPane {
    protected static ArrayList<MMNode> nodeList= new ArrayList<MMNode>();
    protected Pane pane;
    String type;
    String text;
    public static MMNode create(String type, String text, Pane pane)
    {
        switch(type)
        {
            case("Elps"):
            {
                System.out.println("ELPS");
                MMNode node = new EllipseNode();
                node.pane = pane;
                nodeList.add(node);
                return node;
            }
        }
        return new EllipseNode();
    }
}
