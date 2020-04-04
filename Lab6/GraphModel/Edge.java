package Lab6.GraphModel;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Edge {
    private Vertex startVertex, endVertex;
    private boolean selected;

    public Edge(Vertex start, Vertex end) {
        startVertex = start;
        endVertex = end;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public Vertex otherEndFrom(Vertex aVertex) {
        if (startVertex == aVertex)
            return endVertex;
        else
            return startVertex;
    }

    public boolean isSelected() {
        return selected;
    }

    public void toggleSelected() {
        selected = !selected;
    }

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
