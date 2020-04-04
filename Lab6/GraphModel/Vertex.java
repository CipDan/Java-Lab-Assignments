package Lab6.GraphModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a vertex.
 */
public class Vertex {
    private Point2D location;
    private List<Edge> incidentEdges = new ArrayList<>();
    public static double RADIUS = 15.0;
    private boolean selected = false;

    /**
     * Creates a new vertex.
     *
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     */
    public Vertex(double x, double y) {
        this(new Point2D(x, y));
    }

    /**
     * Creates a new vertex.
     *
     * @param aPoint designated location of the vertex.
     */
    public Vertex(Point2D aPoint) {
        location = aPoint;
    }

    /**
     * Returns all the incident edges.
     *
     * @return a list of edges.
     */
    public List<Edge> incidentEdges() {
        return incidentEdges;
    }

    /**
     * Adds a new incident edge to the list.
     *
     * @param e the new incident edge.
     */
    public void addIncidentEdge(Edge e) {
        incidentEdges.add(e);
    }

    /**
     * Returns the location of the vertex.
     *
     * @return a 2D location.
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets a new location for the vertex.
     *
     * @param aPoint the new location.
     */
    public void setLocation(Point2D aPoint) {
        location = aPoint;
    }

    /**
     * Draws the vertex.
     *
     * @param aPen the designated `pen` of the canvas that is used for drawing.
     */
    public void draw(GraphicsContext aPen) {
        if (selected)
            aPen.setFill(Color.RED);
        else
            aPen.setFill(Color.LIGHTBLUE);

        aPen.fillOval(location.getX() - RADIUS, location.getY() - RADIUS,
                RADIUS * 2, RADIUS * 2);
        aPen.setStroke(Color.BLACK);
        aPen.strokeOval(location.getX() - RADIUS, location.getY() - RADIUS,
                RADIUS * 2, RADIUS * 2);
    }

    /**
     * Checks if the vertex is selected.
     *
     * @return true if it is, false otherwise.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Toggle the `selected` field of the edge.
     */
    public void toggleSelected() {
        selected = !selected;
    }
}
