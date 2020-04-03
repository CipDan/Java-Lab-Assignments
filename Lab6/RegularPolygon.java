package Lab6;

import javafx.scene.shape.Polygon;

/**
 * A representation of a regular polygon.
 */
public class RegularPolygon extends Polygon {
    double[] xCoord;
    double[] yCoord;

    /**
     * Creates a new polygon.
     *
     * @param x0     horizontal coordinate of the polygon's center.
     * @param y0     vertical coordinate of the polygon's center.
     * @param radius the radius of the polygon.
     * @param edges  the number of edges the polygon haas.
     */
    public RegularPolygon(int x0, int y0, int radius, int edges) {
        xCoord = new double[edges];
        yCoord = new double[edges];
        double alpha = 2 * Math.PI / edges;
        for (int i = 0; i < edges; i++) {
            xCoord[i] = x0 + radius * Math.cos(alpha * i);
            yCoord[i] = y0 + radius * Math.sin(alpha * i);
            this.getPoints().addAll(xCoord[i], yCoord[i]);
        }
    }
}
