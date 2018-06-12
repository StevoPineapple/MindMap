package MindMap;

import javafx.scene.shape.Ellipse;

import javafx.scene.paint.Color;

public class EllipseNode extends MMNode {
    private String text;
    private Color fillCol;
    private Color edgeCol;
    private double radius = 30;
    public EllipseNode()
    {
        //temp Color
        fillCol = Color.WHITE;
        edgeCol = Color.web("#449488");
        text = "";
        Ellipse elps = new Ellipse();
        elps.setRadiusX(radius);
        elps.setRadiusY(radius*0.75);
        elps.setFill(fillCol);
        elps.setStroke(edgeCol);
        //elps.setId("ellipse-"+nodes.size());
        elps.getStyleClass().add("ellipse");
        getChildren().add(elps);

        System.out.println("Create Ellipse in Constructor");
    }
}
