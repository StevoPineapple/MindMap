package mindmap;

import javafx.scene.layout.Pane;
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
        this.line = line;
        this.edge = edge;
        this.elps = edge.getElps();
        line.toBack();
        lineList.add(this);
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
