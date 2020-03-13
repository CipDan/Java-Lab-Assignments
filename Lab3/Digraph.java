package Lab3;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a digraph.
 */
public class Digraph {
    List<Vertex> vertices = new LinkedList<>();
    List<Edge> edges = new LinkedList<>();

    public Digraph() {

    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Looks for a vertex with the coordinates (i,j) in the digraph's list of vertices.
     *
     * @param i the first coordinate
     * @param j the second coordinate
     * @return the found vertex, if it exists; otherwise, <code>null</code>
     */
    public Vertex findVertex(int i, int j) {
        for (Vertex vertex : vertices)
            if (vertex.checkIfEqual(i, j))
                return vertex;
        return null;
    }
}

