package mindmap;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class EllipseNode extends MMNode {
    private Text text = new Text();
    private Color fillCol;
    private Color edgeCol;
    private double radius = 30;
    private double width = radius*2;
    private double height = radius*2*0.75;
    private double posX;// = self.getTranslateX();
    private double posY;// = self.getTranslateY();
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
        //elps.setId("ellipse-"+nodes.size());
        elps.getStyleClass().add("ellipse");
        getChildren().addAll(elps,text);
        registerDrag();
        System.out.println("Create Ellipse in Constructor");
    }
    private void registerDrag() {
        EllipseNode self = this;
        this.setOnMouseMoved(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                //System.out.println("Movededdedede");
                posX = event.getSceneX()-self.getTranslateX();
                posY = event.getSceneY()-self.getTranslateY();
                //System.out.println(posX+"XX");
                //System.out.println(posY+"YY");
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Dragged");
                //self.toFront();
                self.setTranslateX(event.getSceneX()-posX);//-(event.getSceneX()-self.getTranslateX()));
                self.setTranslateY(event.getSceneY()-posY);//-height);
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
                self.toBack();
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                self.toFront();
            }
        });
    }
}
