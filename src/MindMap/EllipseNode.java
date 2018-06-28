package mindmap;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EllipseNode extends MMNode {
    private Text text = new Text();
    private TextField tField = new TextField(text.getText());
    private boolean editing = false;

    private Color fillCol;
    private Color edgeCol;

    private double width = 60;
    private double height = 45;
    private double posX;// = self.getTranslateX();
    private double posY;// = self.getTranslateY();

    private double lastPosX = 0.0;
    private double lastPosY = 0.0;

    private EllipseNode self = this;
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    private Pane mainPane = Main.getPane();

    Ellipse elps = new Ellipse();


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
        elps.getStyleClass().add("ellipse");
        addEdge();
        getChildren().addAll(elps,text);

        this.setTranslateX(90); //set init XY
        this.setTranslateY(90);


        registerEvent();
        System.out.println("Create Ellipse in Constructor");
    }

    private void resizeElps()
    {
        if(width<60) {
            elps.setRadiusX(30);
            width = 60;
        }
        else
            elps.setRadiusX(width/2);
        if(height<45) {
            elps.setRadiusY(22.5);
            height = 45;
        }
        else
            elps.setRadiusY(height/2);
        System.out.println("width:"+width);
        System.out.println("hieght:"+height);
    }

    private void reposEdge()
    {
        for(Edge edge: edges) {
            edge.poseEdge();
            System.out.println("Reposed Edge");
        }
    }

    private void setNodeText()
    {
        text.setText(tField.getText());
        getChildren().remove(tField);
        System.out.println("set text");
        width = text.getBoundsInLocal().getWidth() + 20;
        resizeElps();
        reposEdge();
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

    public double getPosChangedX() {return self.getTranslateX()-lastPosX;}

    public double getPosChangedY() {return self.getTranslateY()-lastPosY;}

    protected boolean checkSelfEdge(Edge checkEdge)
    {
        for (Edge edge : edges)
        {
            if(edge.equals(checkEdge))
                return true;
        }
        return false;
    }

    public double getSelfHeight()
    {
        return height;
    }
    private void registerEvent() {
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                editing = true;
                System.out.println("Clicked");
                tField.setText(text.getText());
                getChildren().add(tField);
            }
        });
        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                posX = event.getSceneX() - self.getTranslateX();
                posY = event.getSceneY() - self.getTranslateY();
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!Global.connecting) {
                    lastPosX = self.getTranslateX();
                    lastPosY = self.getTranslateY();
                    self.setTranslateX(event.getSceneX() - posX);//-(event.getSceneX()-self.getTranslateX()));
                    self.setTranslateY(event.getSceneY() - posY);
                    MMLine.updateLinePos();
                }
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(Edge edge: edges)
                {
                    if(!edge.getIsTempLine())
                        edge.toBack();
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
                }
                self.toFront();
                text.toFront();
            }
        });
        mainPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (editing) {
                    setNodeText();
                    editing = false;
                }
            }
        });
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER && editing)
                {
                    setNodeText();
                    editing = false;
                }
            }
        });
    }
}


