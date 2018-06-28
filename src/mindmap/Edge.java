package mindmap;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Edge extends MMNode { // temp parent
    private Pane mainPane = Main.getPane();

    private ArrayList<MMLine> lineList = new ArrayList<MMLine>();
    private Line tempLine = new Line();
    private Ellipse tempCirc = new Ellipse();
    private boolean isTempLine = false;
    private int lineOffSet = 3;

    private EllipseNode elps;
    private Ellipse edge;
    private static ArrayList<Edge> edgeList = new ArrayList<Edge>();

    private boolean isFront;

    private Edge(EllipseNode elps,Boolean isFront) {
        //temp Color
        Ellipse edge = new Ellipse();
        this.isFront = isFront;
        this.elps = elps;
        this.edge = edge;

        edge.setRadiusX(6);
        edge.setRadiusY(6);
        edge.setFill(Global.COLHUB);
        poseEdge();
        edge.setTranslateY(0);

        edgeList.add(this);
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

    private Edge checkTempLine()
    {
        for(Edge edge : edgeList)
        {
            if(edge.getIsTempLine())
            {
                return edge;
            }
        }
        return null;
    }

    public void switchIsTempLine()
    {
        isTempLine = !isTempLine;
    }

    public boolean getIsTempLine()
    {
        return isTempLine;
    }

    public EllipseNode getElps() {
        return elps;
    }

    public double getX(){
        return getParent().getTranslateX()+getLayoutX();
    }

    public double getY(){
        return getParent().getTranslateY()+getLayoutY();
    }

    public void registerEvent() {
        Edge self = this;

        edge.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!Global.connecting) {
                    isTempLine = true;
                    Global.connecting = true;

                    tempCirc = new Ellipse();
                    tempCirc.setFill(Global.COLHUB);
                    tempCirc.setRadiusX(2);
                    tempCirc.setRadiusY(2);
                    tempCirc.setTranslateX(event.getSceneX()-lineOffSet);
                    tempCirc.setTranslateY(event.getSceneY()-lineOffSet);

                    mainPane.getChildren().add(tempCirc);

                    tempLine = new Line();
                    tempLine.setStroke(Global.COLHUB);
                    tempLine.setStrokeWidth(2);
                    tempLine.setStartX(elps.getTranslateX() + elps.getWidth() + edge.getTranslateX()+30); //30BUUG
                    tempLine.setStartY(elps.getTranslateY() + elps.getHeight() / 2);
                    tempLine.setEndX(event.getSceneX()-lineOffSet);
                    tempLine.setEndY(event.getSceneY()-lineOffSet);

                    mainPane.getChildren().add(tempLine);
                }
                else {
                    Edge tempLineEdge = self.checkTempLine();
                    if(tempLineEdge!=null)
                    {
                        MMLine.createLine(tempLine,self);
                        checkTempLine().switchIsTempLine();
                        Global.connecting = false;
                    }
                }
            }
        });

        mainPane.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isTempLine) {
                    tempLine.setEndX(event.getSceneX()-lineOffSet);
                    tempLine.setEndY(event.getSceneY()+lineOffSet);

                    tempCirc.setTranslateX(tempLine.getEndX());
                    tempCirc.setTranslateY(tempLine.getEndY());
                }
            }
        });
    }
}
