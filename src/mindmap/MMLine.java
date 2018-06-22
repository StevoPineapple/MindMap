package mindmap;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class MMLine extends MMNode{
    private MMNode sourceNode;
    private MMNode destNode;
    private Line line = new Line();//10,10,200,200);
    private static ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Pane pane;
    private double tempX;
    private double tempY;
    private MMLine(MMNode sourceNode,MMNode destNode)
    {
        line = new Line(sourceNode.getTranslateX(),sourceNode.getTranslateY(),destNode.getTranslateX(),destNode.getTranslateY());
    }
    private MMLine()
    {
        //line.setLayoutX();
        Circle c = new Circle();
        c.setRadius(2.0);
        c.setFill(new Color(0,0,0,1));
        c.setCenterX(line.getStartX());
        c.setCenterY(line.getStartY());
        pane.getChildren().addAll(line,c);
    }
    public static MMLine CreateLine(MMNode sourceNode,MMNode destNode)
    {
        return new MMLine(sourceNode,destNode);
    }
    public static MMLine CreateLine()
    {
        return new MMLine();
    }
    public void setTempX(double x)
    {
        tempX = x;
    }
    public void setTempY(double y)
    {
        tempY = y;
    }
    public void createTempLine()
    {
        line.setStartY(1000);
        line.setStartX(1000);
        line.setEndX(800);
        line.setEndY(800);
        getChildren().add(line);
    }
}
