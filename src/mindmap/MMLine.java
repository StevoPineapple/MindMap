package mindmap;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class MMLine extends MMNode{

    private static ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Pane mainPane = Main.getPane();

    private EllipseNode elps;
    private Line line;
    private Edge edge;

    private MMLine(Line line,Edge edge)
    {
//        ///line = new Line(srcEdge.getElps().getTranslateX(),srcEdge.getElps().getTranslateY(),destEdge.getElps().getTranslateX(),destEdge.getElps().getTranslateY());
//        this.srcEdge = srcEdge;
//        this.destEdge = destEdge;
//        line.setStrokeWidth(5);
//        line.setStroke(Color.BLACK);
        this.line = line;
        this.edge = edge;
        this.elps = edge.getElps();
        line.toBack();
        //mainPane.getChildren().add(line);
        lineList.add(this);
    }
    private MMLine()
    {
//        //line.setLayoutX();
//        Circle c = new Circle();
//        c.setRadius(2.0);
//        c.setFill(new Color(0,0,0,1));
//        c.setCenterX(tempLine.getStartX());
//        c.setCenterY(tempLine.getStartY());
//        pane.getChildren().addAll(tempLine,c);
    }
    public static MMLine createLine(Line line,Edge edge)
    {
        return new MMLine(line,edge);
    }

    public static void updateLinePos()
    {
        for(MMLine mLine : lineList)
        {
            mLine.line.setStartX(mLine.edge.getX());
            mLine.line.setStartX(mLine.edge.getY());
        }
    }
}
