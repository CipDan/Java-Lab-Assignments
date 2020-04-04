package Lab6.GraphModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A representation of an edge.
 */
public class Edge {
    private Vertex startVertex, endVertex;
    private boolean selected;

    /**
     * Creates an edge.
     *
     * @param start starting point of edge.
     * @param end   ending point of edge.
     */
    public Edge(Vertex start, Vertex end) {
        startVertex = start;
        endVertex = end;
    }

    /**
     * Returns the starting point of the edge.
     *
     * @return a <code>Vertex</code>.
     */
    public Vertex getStartVertex() {
        return startVertex;
    }

    /**
     * Returns the ending point of the edge.
     *
     * @return a <code>Vertex</code>.
     */
    public Vertex getEndVertex() {
        return endVertex;
    }

    /**
     * Returns opposite vertex to the given vertex.
     *
     * @param aVertex a <code>Vertex</code>.
     * @return a <code>Vertex</code> opposite to the given vertex.
     */
    public Vertex otherEndFrom(Vertex aVertex) {
        if (startVertex == aVertex)
            return endVertex;
        else
            return startVertex;
    }

    /**
     * Checks if the edge is selected.
     *
     * @return true if it is, otherwise false.
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

    /**
     * Draws the edge.
     *
     * @param aPen the designated `pen` of the canvas that is used for drawing.
     */
    public void draw(GraphicsContext aPen) {
        // Draw line from center of startVertex to center of endVertex
        if (selected)
            aPen.setStroke(Color.RED);
        else
            aPen.setStroke(Color.BLACK);

        aPen.strokeLine(startVertex.getLocation().getX(),
                startVertex.getLocation().getY(),
                endVertex.getLocation().getX(),
                endVertex.getLocation().getY());
    }
}
