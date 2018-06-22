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
    private Line tempLine = new Line();//10,10,200,200);
    private static ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private static Pane pane;
    private double tempX;
    private double tempY;
    private MMLine(MMNode sourceNode,MMNode destNode)
    {
        tempLine = new Line(sourceNode.getTranslateX(),sourceNode.getTranslateY(),destNode.getTranslateX(),destNode.getTranslateY());
    }
    private MMLine()
    {
        //line.setLayoutX();
        Circle c = new Circle();
        c.setRadius(2.0);
        c.setFill(new Color(0,0,0,1));
        c.setCenterX(tempLine.getStartX());
        c.setCenterY(tempLine.getStartY());
        pane.getChildren().addAll(tempLine,c);
    }
    public static MMLine CreateLine(MMNode sourceNode,MMNode destNode)
    {
        return new MMLine(sourceNode,destNode);
    }
    public static MMLine CreateLine()
    {
        return new MMLine();
    }
    public static void addLine(Edge src, Edge dest)
    {
        Line newLine = new Line(src.getTranslateX(),src.getTranslateY(),dest.getTranslateX(),dest.getTranslateY());
        pane.getChildren().add(newLine);
    }
    private void removeTempLine()
    {
        pane.getChildren().remove(tempLine);
    }
}
