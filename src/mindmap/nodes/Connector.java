package mindmap.nodes;

import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Connector{
    public static boolean lookout=false;
    public static Edge currentSource;

    private static ArrayList<Connector> collection= new ArrayList<Connector>();

    private Line line;
    private Edge src;
    private Edge dest;

    private Connector(Edge src, Edge dest){
        this.line = createLine(src,dest);
        updateEdges(src,dest);
    }

    public static Connector createConnector(Edge src, Edge dest){
        if(src.getEdgeGroup() == dest.getEdgeGroup()) return null;
        boolean notExists = src.getEdgeGroup().addPeer(dest.getEdgeGroup());
        if(!notExists) return null;
        Connector cc = new Connector(src,dest);
        Connector.collection.add(cc);
        return cc;
    }

    public void updateLine(){
        this.line.setStartX(src.getX());
        this.line.setStartY(src.getY());
        this.line.setEndX(dest.getX());
        this.line.setEndY(dest.getY());
    }

    private Line createLine(Edge src, Edge dest){
        Line line = new Line(
                src.getX(),src.getY(),
                dest.getX(),dest.getY() );
        return line;

    }

    public Line getLine(){
        return line;
    }

    public Edge getSrc(){
        return src;
    }

    public Edge getDest() {
        return dest;
    }

    public void updateEdges(Edge src,Edge dest){
        if(this.src !=null)
            this.src.removeConnector(this);
        if(this.dest !=null)
            this.dest.removeConnector(this);
        this.src =src;
        this.dest = dest;
        src.addConnector(this);
        dest.addConnector(this);
    }

    public static ArrayList<Connector> getConnectorsWithEdgesGroups(EdgeGroup e1, EdgeGroup e2){
        ArrayList<Connector> list= new ArrayList<>();
        for(Connector c:collection){
            if( (c.getSrc().getEdgeGroup()==e1 && c.getDest().getEdgeGroup()==e2) ||
                    (c.getSrc().getEdgeGroup()==e2 && c.getDest().getEdgeGroup()==e1)   ){
                list.add(c);
            }
        }
        return list;
    }
}
