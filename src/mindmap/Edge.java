package mindmap;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edge extends MMNode { // temp parent
    private Pane mainPane = Main.getPane();
    private Scene mainScene = Main.getScene();

    private ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Line tempLine;
    private Boolean isTempLine = false;
    private int lineOffSet = 3;

    private EllipseNode elps;
    private Ellipse edge;

    private boolean isFront;

    private Edge(EllipseNode elps,Boolean isFront) {
        //temp Color
        Ellipse edge = new Ellipse();
        this.isFront = isFront;
        this.elps = elps;
        this.edge = edge;
        edge.setRadiusX(4);
        edge.setRadiusY(4);
        edge.setFill(Global.COLHUB);
        poseEdge();
        edge.setTranslateY(0);

        getChildren().add(edge);
        registerEvent();
        System.out.println("Create Edge");
    }
    public static Edge createFrontEdge(EllipseNode elps)
    {
        Edge rtnEdge = new Edge(elps,true);
        return rtnEdge;
    }

    public static Edge createBackEdge(EllipseNode elps)
    {
        Edge rtnEdge = new Edge(elps,false);
        return rtnEdge;
    }

    public void poseEdge()
    {
        if(isFront)
            edge.setTranslateX(elps.getSelfWidth() / 2);
        else
            edge.setTranslateX(elps.getSelfWidth() / -2);
    }

    public void connectLines(Edge dest)
    {
        MMLine.addLine(this,dest);
    }



    public double getX()
    {
        return this.getTranslateX();
    }

    public double getY()
    {
        return this.getTranslateY();
    }

    public void setVisible()
    {
        this.setVisible(false);
    }

    public boolean getIsTempLine()
    {
        return isTempLine;
    }
    public void registerEvent() {
        Edge self = this;
        edge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!isTempLine) {
                    isTempLine = true;
                    Ellipse tempCirc = new Ellipse();
                    tempCirc.setFill(Global.COLHUB);
                    tempLine = new Line();
                    tempLine.setStroke(Global.COLHUB);
                    tempLine.setStrokeWidth(2);
                    tempLine.setStartX(elps.getTranslateX() + elps.getWidth() + edge.getTranslateX()+30); //30BUUG
                    tempLine.setStartY(elps.getTranslateY() + elps.getHeight() / 2);
                    tempLine.setEndX(event.getSceneX()-lineOffSet);
                    tempLine.setEndY(event.getSceneY()-lineOffSet);
                    mainPane.getChildren().add(tempLine);
                }
            }
        });

        mainPane.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isTempLine) {
                    self.setVisible(true);
                    tempLine.setEndX(event.getSceneX()-lineOffSet);
                    tempLine.setEndY(event.getSceneY()+lineOffSet);
                    System.out.println(tempLine.getStartX()+"sX");
                    System.out.println(tempLine.getStartY()+"sY");
                    System.out.println(tempLine.getEndX()+"eX");
                    System.out.println(tempLine.getEndY()+"eY");
                    System.out.println(event.getSceneX()+"X");
                    System.out.println(event.getSceneY());
                }
            }
        });
        mainPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isTempLine) {
                    mainPane.getChildren().remove(tempLine);
                        isTempLine = !isTempLine;
                        self.toBack();
                        }
            }
        });
    }
}
