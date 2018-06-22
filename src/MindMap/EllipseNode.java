package mindmap;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EllipseNode extends MMNode {
    private Text text = new Text();
    private TextField tField = new TextField(text.getText());
    private Color fillCol;
    private Color edgeCol;
    private double width = 60;
    private double height = 45;
    private double posX;// = self.getTranslateX();
    private double posY;// = self.getTranslateY();
    private EllipseNode self = this;
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    Ellipse elps = new Ellipse();

    EllipseNode temp;
    double tempX;
    double tempY;


    public EllipseNode()
    {
        //temp Color
        fillCol = Color.WHITE;
        edgeCol = Color.web("#449488");
        text.setText("text");
        elps.setRadiusX(width/2);
        elps.setRadiusY(height/2);
        elps.setFill(fillCol);
        elps.setStroke(edgeCol);
        //elps.setId("ellipse-"+nodes.size());
        elps.getStyleClass().add("ellipse");
        getChildren().addAll(elps,text);

        this.setTranslateX(90); //set init XY
        this.setTranslateY(90);
        addEdge();

        registerEvent();
        System.out.println("Create Ellipse in Constructor");
    }
    private void resizeElps()
    {
        if(width<60)
            elps.setRadiusX(30);
        else
            elps.setRadiusX(width/2);
        if(height<45)
            elps.setRadiusY(22.5);
        else
            elps.setRadiusY(height/2);
        System.out.println("width:"+width);
        System.out.println("hieght:"+height);
    }
    public void addEdge()
    {
        Edge fEdge = Edge.createFrontEdge(this);
        Edge bEdge = Edge.createBackEdge(this);
        getChildren().add(fEdge);
        getChildren().add(bEdge);
        fEdge.toBack();
        bEdge.toBack();
        edges.add(fEdge);
        edges.add(bEdge);
    }
    public double getSelfWidth()
    {
        return width;
    }

    public double getSelfHeight()
    {
        return height;
    }
    private void registerEvent() {
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Movededdedede");
                posX = event.getSceneX() - self.getTranslateX();
                posY = event.getSceneY() - self.getTranslateY();
                //getChildren().add(line);
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Dragged");
                //self.toFront();
                self.setTranslateX(event.getSceneX() - posX);//-(event.getSceneX()-self.getTranslateX()));
                self.setTranslateY(event.getSceneY() - posY);//-height);
                /*System.out.println(self.getTranslateX()+"X");
                System.out.println(event.getSceneX()+"XM");
                System.out.println(posX+"XX");
                System.out.println(posY+"YY");
                System.out.println(self.getTranslateX());*/
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(Edge edge: edges)
                {
                    if(!edge.getIsTempLine())
                        edge.toBack();
                    //edge.setVisible(false);
                }
                self.toBack();
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(Edge edge: edges)
                {
                    edge.toFront();
                    edge.setVisible(true);
                }
                self.toFront();
            }
        });
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked");
                tField.setText(text.getText());
                getChildren().add(tField);
            }
        });
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    text.setText(tField.getText());
                    getChildren().remove(tField);
                    System.out.println("set text");
                    width = text.getBoundsInLocal().getWidth() + 15;
                    resizeElps();
                }
            }
        });
    }
}


