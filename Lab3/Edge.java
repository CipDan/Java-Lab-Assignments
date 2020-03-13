package Lab3;

/**
 * Model of a weighted graph edge.
 */
public class Edge {
    private int weight;
    private Vertex start, end;

    public Edge(int weight, Vertex start, Vertex end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder edgeNodes = new StringBuilder();
        edgeNodes.append(start).append(", ").append(end);
        return '{' + edgeNodes.toString() + ": " + weight + '}';
    }
}
