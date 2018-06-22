package mindmap;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edge extends MMNode { // temp parent
    private Color fillCol;
    private Ellipse edge = new Ellipse();
    private ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Boolean isTempLine = false;
    private double posX;
    private double posY;
    private Pane pane = Main.getPane();
    private Line tempLine;
    private EllipseNode elps;
    private Edge(EllipseNode elps) {
        //temp Color
        this.elps = elps;
        fillCol = Color.BLACK;
        edge.setRadiusX(5);
        edge.setRadiusY(5);
        edge.setTranslateX(elps.getSelfWidth()/2);
        edge.setTranslateY(0);
        getChildren().add(edge);
        registerEvent();
        System.out.println("Create Edge");
    }
    public static Edge createEdge(EllipseNode elps)
    {
        Edge rtnEdge = new Edge(elps);
        return rtnEdge;
    }
    public double getX()
    {
        return this.getTranslateX();
    }
    public double getY()
    {
        return this.getTranslateY();
    }
    public void registerEvent() {
        Edge self = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isTempLine = true;
                tempLine = new Line();
                tempLine.setStartX(edge.getTranslateX()+elps.getTranslateX()+elps.getWidth()+30); //30BUUG
                tempLine.setStartY(edge.getTranslateY()+elps.getTranslateY()+elps.getHeight()/2);
                tempLine.setEndX(event.getSceneX());
                tempLine.setEndY(event.getSceneY());
                pane.getChildren().add(tempLine);
            }
        });
        Main.getScene().addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isTempLine) {
                    tempLine.setEndX(event.getSceneX());
                    tempLine.setEndY(event.getSceneY());
                    System.out.println(tempLine.getStartX()+"sX");
                    System.out.println(tempLine.getStartY()+"sY");
                    System.out.println(tempLine.getEndX()+"eX");
                    System.out.println(tempLine.getEndY()+"eY");
                    System.out.println(event.getSceneX()+"X");
                    System.out.println(event.getSceneY());
                }
            }
        });
    }
}
