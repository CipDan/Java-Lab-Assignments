package Lab6;

import javafx.scene.shape.Ellipse;

public class NodeShape extends Ellipse {
    public NodeShape(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }
}
