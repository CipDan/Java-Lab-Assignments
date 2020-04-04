package Lab6.GraphModel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.List;

/**
 * A representation of a graph.
 */
public class Graph {
    private List<Vertex> vertices;

    /**
     * Creates a graph.
     */
    public Graph() {
        this(new ArrayList<>());
    }

    /**
     * Creates a graph.
     *
     * @param initialVertices the vertices of the graph.
     */
    public Graph(ArrayList<Vertex> initialVertices) {
        vertices = initialVertices;
    }

    /**
     * Returns the graph's edges.
     *
     * @return a list of the graph's edges.
     */
    public List<Edge> getEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (Vertex n : vertices) {
            for (Edge e : n.incidentEdges()) {
                if (!edges.contains(e)) //so that it is not added twice
                    edges.add(e);
            }
        }
        return edges;
    }

    /**
     * Adds a new vertex.
     *
     * @param aVertex a vertex to be added.
     */
    public void addVertex(Vertex aVertex) {
        vertices.add(aVertex);
    }

    /**
     * Adds a new edge.
     *
     * @param start starting point of edge.
     * @param end   ending point of edge.
     */
    public void addEdge(Vertex start, Vertex end) {
        // First make the edge
        Edge anEdge = new Edge(start, end);
        // Now tell the vertices about the edge
        start.addIncidentEdge(anEdge);
        end.addIncidentEdge(anEdge);
    }

    /**
     * Removes the given edge from the graph.
     *
     * @param anEdge the edge to be removed.
     */
    public void deleteEdge(Edge anEdge) {
        // Just ask the vertices to remove it
        anEdge.getStartVertex().incidentEdges().remove(anEdge);
        anEdge.getEndVertex().incidentEdges().remove(anEdge);
    }

    /**
     * Removes the given vertex from the graph.
     *
     * @param aVertex the vertex to be removed.
     */
    public void deleteVertex(Vertex aVertex) {
        // Remove the opposite vertex's incident edges
        for (Edge e : aVertex.incidentEdges())
            e.otherEndFrom(aVertex).incidentEdges().remove(e);
        vertices.remove(aVertex); // Remove the vertex now
    }

    /**
     * Searches for vertices near the given location.
     *
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @return a vertex if there was one near, otherwise <code>null</code>.
     */
    public Vertex vertexAt(double x, double y) {
        for (int i = vertices.size() - 1; i >= 0; i--) {
            Vertex n = vertices.get(i);
            Point2D c = n.getLocation();
            double d = (x - c.getX()) * (x - c.getX()) + (y - c.getY()) * (y - c.getY());
            if (d <= (Vertex.RADIUS * Vertex.RADIUS))
                return n;
        }
        return null;
    }

    /**
     * Searches for edges near the given location.
     *
     * @param x horizontal coordinate.
     * @param y vertical coordinate.
     * @return an edge if there was one near, otherwise <code>null</code>.
     */
    public Edge edgeAt(double x, double y) {
        for (Edge e : getEdges()) {
            Vertex n1 = e.getStartVertex();
            Vertex n2 = e.getEndVertex();
            double xDiff = n2.getLocation().getX() - n1.getLocation().getX();
            double yDiff = n2.getLocation().getY() - n1.getLocation().getY();
            double distance = Math.abs(xDiff * (n1.getLocation().getY() - y) -
                    (n1.getLocation().getX() - x) * yDiff) /
                    Math.sqrt(xDiff * xDiff + yDiff * yDiff);
            if (distance <= 5) {
                if (Math.abs(xDiff) > Math.abs(yDiff)) {
                    if (((x < n1.getLocation().getX()) &&
                            (x > n2.getLocation().getX())) ||
                            ((x > n1.getLocation().getX()) &&
                                    (x < n2.getLocation().getX()))) {
                        return e;
                    }
                } else if (((y < n1.getLocation().getY()) &&
                        (y > n2.getLocation().getY())) ||
                        ((y > n1.getLocation().getY()) &&
                                (y < n2.getLocation().getY()))) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Returns the vertices that are selected.
     *
     * @return a list of selected vertices.
     */
    public List<Vertex> selectedVertices() {
        List<Vertex> selected = new ArrayList<>();
        for (Vertex n : vertices)
            if (n.isSelected())
                selected.add(n);
        return selected;
    }

    /**
     * Returns the edges that are selected.
     *
     * @return a list of selected edges.
     */
    public List<Edge> selectedEdges() {
        List<Edge> selected = new ArrayList<>();
        for (Edge e : getEdges())
            if (e.isSelected())
                selected.add(e);
        return selected;
    }

    /**
     * Draws the graph.
     *
     * @param aPen the designated `pen` of the canvas that is used for drawing.
     */
    public void draw(GraphicsContext aPen) {
        List<Edge> edges = getEdges();
        for (Edge e : edges)
            e.draw(aPen);
        for (Vertex n : vertices)
            n.draw(aPen);
    }
}
