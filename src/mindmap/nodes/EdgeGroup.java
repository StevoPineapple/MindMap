package mindmap.nodes;

import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashSet;

public class EdgeGroup {

    private Edge[] circles;
    private Node parent;
    private static PseudoClass CIRCLE_PSEUDO_CLASS = PseudoClass.getPseudoClass("circleClicked");
    private boolean clickedState=false;
    private HashSet<EdgeGroup> connectedPeers = new HashSet<EdgeGroup>();

    public EdgeGroup create(Node parent){
        this.parent =parent;
        circles =new Edge[4];
        for(int i =0;i<4;i++)
        {
            circles[i] = new Edge(5,this);
            circles[i].setVisible(false);
            parent.getChildren().add(circles[i]);
            circles[i].getStyleClass().add("circle");
            registerOnclick(circles[i]);
        }
        StackPane.setMargin(circles[0],new Insets(0,0,0,parent.getBoundsInParent().getWidth()));
        StackPane.setMargin(circles[1],new Insets(0,0,parent.getBoundsInParent().getHeight(),0));
        StackPane.setMargin(circles[2],new Insets(0,parent.getBoundsInParent().getWidth(),0,0));
        StackPane.setMargin(circles[3],new Insets(parent.getBoundsInParent().getHeight(),0,0,0));

        registerVisibility();
        return  this;
    }

    private void registerVisibility(){
        this.parent.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(Circle c :circles)
                    c.setVisible(true);
            }
        });

        this.parent.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(Circle c :circles)
                    c.setVisible(false);
            }
        });
    }

    private void registerOnclick(Edge c)
    {

        c.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clickedState = !clickedState;

                if(clickedState){
                    if(Connector.lookout)
                    {
                        Connector cc = Connector.createConnector(c, Connector.currentSource);
                        if(cc!=null)
                            parent.parent.getChildren().add(cc.getLine());


                        clickedState = false;
                        Connector.currentSource.getEdgeGroup().clickedState = false;
                        Connector.currentSource.getEdgeGroup().getParent().pseudoClassStateChanged(CIRCLE_PSEUDO_CLASS,false);

                        Connector.lookout=false;
                        Connector.currentSource = null;
                    }
                    else
                    {
                        Connector.lookout = true;
                        Connector.currentSource = c;
                        parent.pseudoClassStateChanged(CIRCLE_PSEUDO_CLASS,clickedState);
                    }

                }
            }
        });
    }

    public Edge[] getEdges(){
        return circles;
    }

    public Node getParent(){
        return parent;
    }

    public boolean addPeer(EdgeGroup destGroup){
        return this.connectedPeers.add(destGroup);
    }

    public void reconEdgesWithPeers(){
        for(EdgeGroup edgeGroup:connectedPeers){
            Edge[] e= findShortestEdge(edgeGroup);
            for(Connector c:Connector.getConnectorsWithEdgesGroups(this,edgeGroup)){
                c.updateEdges(e[0],e[1]);
                c.updateLine();
            }
        }
    }

    private Edge[] findShortestEdge(EdgeGroup edgeGrp){
        double shortest =0;
        Edge[] minima = new Edge[2];
        for(Edge e1:circles){
            for(Edge e2:edgeGrp.getEdges()){
                double distance = e1.distance(e2);
                if(shortest==0 || distance<shortest){
                    shortest = distance;
                    minima[0]=e1;minima[1] =e2;
                }
            }
        }
        return minima;
    }
}

class Edge extends Circle{
    private ArrayList<Connector> connectors;
    private EdgeGroup edgeGroup;
    Edge(int r,EdgeGroup edgeGroup){
        super(r);
        connectors= new ArrayList<>();
        this.edgeGroup = edgeGroup;
    }

    public void addConnector(Connector c){
        connectors.add(c);
    }

    public void removeConnector(Connector c){ connectors.remove(c); }


    public ArrayList<Connector> getConnectors() {
        return connectors;
    }

    public EdgeGroup getEdgeGroup() {
        return edgeGroup;
    }

    public double getX(){
       return getParent().getTranslateX()+getLayoutX();
    }

    public double getY(){
        return getParent().getTranslateY()+getLayoutY();
    }

    public double distance(Edge e){
        return Math.sqrt( Math.pow(e.getX() -getX(),2)  + Math.pow(e.getY() -getY(),2) );
    }

}