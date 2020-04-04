package Lab6.Figures;

import javafx.scene.shape.Ellipse;

public class CustomEllipse extends Ellipse {
    public CustomEllipse(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }
}
