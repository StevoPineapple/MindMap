package mindmap;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class MMLine extends MMNode{

    private static ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Pane mainPane = Main.getPane();

    private EllipseNode elps;
    private Line line;
    private Edge srcEdge;
    private Edge destEdge;

    private MMLine(Line line,Edge srcEdge, Edge destEdge)
    {
        this.line = line;
        this.srcEdge = srcEdge;
        this.destEdge = destEdge;
        line.toBack();
        lineList.add(this);
    }
    public static MMLine createLine(Line line,Edge srcEdge, Edge destEdge)
    {
        return new MMLine(line,srcEdge,destEdge);
    }

    public static ArrayList<MMLine> getLineList() {
        return lineList;
    }

    public Line getLine() {
        return line;
    }

    public Edge getDestEdge() {
        return destEdge;
    }

    public Edge getSrcEdge() {
        return srcEdge;
    }
}
