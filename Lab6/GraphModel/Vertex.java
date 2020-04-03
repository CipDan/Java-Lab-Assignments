package Lab6.GraphModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private Point2D location;
    private List<Edge> incidentEdges = new ArrayList<>();
    public static double RADIUS = 15.0;
    private boolean selected = false;

    public Vertex(double x, double y) {
        this(new Point2D(x, y));
    }

    public Vertex(Point2D aPoint) {
        location = aPoint;
    }

    public List<Edge> incidentEdges() {
        return incidentEdges;
    }

    public void addIncidentEdge(Edge e) {
        incidentEdges.add(e);
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D aPoint) {
        location = aPoint;
    }

    public void setLocation(double x, double y) {
        location = new Point2D(x, y);
    }

    public List<Vertex> neighbours() {
        List<Vertex> result = new ArrayList<>();
        for (Edge e : incidentEdges)
            result.add(e.otherEndFrom(this));
        return result;
    }

    public void draw(GraphicsContext aPen) {
        // Draw a blue-filled circle around the center of the vertex
        if (selected)
            aPen.setFill(Color.RED);
        else
            aPen.setFill(Color.LIGHTBLUE);

        aPen.fillOval(location.getX() - RADIUS, location.getY() - RADIUS,
                RADIUS * 2, RADIUS * 2);
        // Draw a black border around the circle
        aPen.setStroke(Color.BLACK);
        aPen.strokeOval(location.getX() - RADIUS, location.getY() - RADIUS,
                RADIUS * 2, RADIUS * 2);
    }


    public boolean isSelected() {
        return selected;
    }

    public void toggleSelected() {
        selected = !selected;
    }
}
