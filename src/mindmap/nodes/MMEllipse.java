package mindmap.nodes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class MMEllipse extends Node{
    private Text text;
    private Ellipse ellipse; //新建椭圆形
    private Color fillColor = Color.WHITE;
    private Color stroke= Color.web("#00BCD4"); // 鼠标没碰到之前图形的颜色
    private EdgeGroup edgeGroup;

    public MMEllipse(String text){
        this.text=new Text(text);

        ellipse = new Ellipse();
        ellipse.setFill(this.fillColor);
        ellipse.setStroke(this.stroke);   // 设置椭圆形的长半径和短半径
        ellipse.setRadiusX(this.text.getBoundsInLocal().getWidth() + 5);
        ellipse.setRadiusY(25);
        ellipse.setId("ellipse-"+nodes.size());
        ellipse.getStyleClass().add("ellipse");


        getChildren().addAll(ellipse,this.text);
        edgeGroup =new EdgeGroup();
        edgeGroup.create(this);

        this.setLayoutX(0);
        this.setLayoutY(0);
        registerDrag();
    }

    private void registerDrag(){  // 拖动椭圆形
        MMEllipse self=this;
        //double delta = this.getS;

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { // 移动椭圆形
                self.setTranslateX(event.getX() + self.getTranslateX() - ellipse.getRadiusX() );
                self.setTranslateY(event.getY() + self.getTranslateY() - ellipse.getRadiusY() );

                updateEdges();
            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                self.edgeGroup.reconEdgesWithPeers();
            }
        });

    }

    private void updateEdges(){
        for(Edge edge:this.edgeGroup.getEdges()){
            ArrayList<Connector> connectors=edge.getConnectors();
            for(Connector c:connectors){
                c.updateLine();
            }
        }
    }




}
