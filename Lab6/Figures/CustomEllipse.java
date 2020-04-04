package Lab6.Figures;

import javafx.scene.shape.Ellipse;

/**
 * A representation of a custom ellipse.
 */
public class CustomEllipse extends Ellipse {

    /**
     * Creates a new custom ellipse.
     *
     * @param x0     horizontal coordinate of the ellipse's center.
     * @param y0     vertical coordinate of the ellipse's center.
     * @param radius width and height of the ellipse.
     */
    public CustomEllipse(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
    }
}
