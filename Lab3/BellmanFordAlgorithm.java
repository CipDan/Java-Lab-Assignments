package Lab3;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BellmanFordAlgorithm implements Algorithm {
    public BellmanFordAlgorithm() {
    }

    /**
     * Generates a digraph using the knapsack and the list of items.
     *
     * @param knapsack      an object representing the knapsack
     * @param itemsToChoose a list of items
     * @return the newly generated dag
     */
    public static Digraph generateDAG(Knapsack knapsack, List<Item> itemsToChoose) {
        Digraph dag = new Digraph();
        for (int i = 0; i <= itemsToChoose.size(); ++i)
            for (int j = 0; j <= knapsack.getCapacity(); ++j)
                dag.addVertex(new Vertex(i, j));
        for (int i = 0; i < itemsToChoose.size(); ++i) {
            for (int j = 0; j <= knapsack.getCapacity(); ++j) {
                dag.addEdge(new Edge(0, dag.findVertex(i, j), dag.findVertex(i + 1, j)));
                //System.out.println(dag.getEdges().get(dag.getEdges().size() - 1));
                if (j + itemsToChoose.get(i).getWeight() <= knapsack.getCapacity()) {
                    dag.addEdge(new Edge(itemsToChoose.get(i).getValue(), dag.findVertex(i, j), dag.findVertex(i + 1, j + itemsToChoose.get(i).getWeight())));
                    //System.out.println(dag.getEdges().get(dag.getEdges().size() - 1));
                }
            }
        }
        return dag;
    }

    static void addItemToKnapsack(Map<Vertex, Vertex> parent, Vertex v, Knapsack knapsack, List<Item> items) {
        if (v == null || parent.get(v) == null)
            return;
        else {
            if (v.getCurrWeight() != parent.get(v).getCurrWeight())
                knapsack.addItem(items.get(v.getCurrItem() - 1));
            addItemToKnapsack(parent, parent.get(v), knapsack, items);
        }
    }

    static void printPath(Map<Vertex, Vertex> parent, Vertex v) {
        if (v == null)
            return;
        printPath(parent, parent.get(v));
        System.out.print(v + " ");
    }

    static void printArr(Map<Vertex, Integer> dist, int numNodes, Digraph dag, Map<Vertex, Vertex> parent) {
        for (int i = 0; i < numNodes; ++i) {
            System.out.print("Distance of vertex " + dag.getVertices().get(i) + " from the source is "
                    + dist.get(dag.getVertices().get(i)) + ". Its path is: ");
            printPath(parent, dag.getVertices().get(i));
            System.out.println();
        }
    }

    @Override
    public void fillKnapsack(Knapsack knapsack, List<Item> itemsToChoose) {
        Digraph dag = generateDAG(knapsack, itemsToChoose);
        int numNodes = dag.getVertices().size();
        int numEdges = dag.getEdges().size();
        Map<Vertex, Integer> distances = new LinkedHashMap<>();
        Map<Vertex, Vertex> parent = new LinkedHashMap<>();
        int i, j;
        for (i = 0; i < numNodes; ++i) {
            distances.put(dag.getVertices().get(i), Integer.MIN_VALUE);
            parent.put(dag.getVertices().get(i), null);
        }
        distances.replace(dag.getVertices().get(0), 0);

        for (i = 1; i < numNodes; ++i) {
            for (j = 0; j < numEdges; ++j) {
                Vertex u = dag.getEdges().get(j).getStart();
                Vertex v = dag.getEdges().get(j).getEnd();
                int weight = dag.getEdges().get(j).getWeight();
                if (distances.get(u) != Integer.MIN_VALUE && distances.get(u) + weight > distances.get(v)) {
                    distances.replace(v, distances.get(u) + weight);
                    parent.replace(v, u);
                }
            }
        }

        //printArr(distances, numNodes, dag, parent);

        int maxWeight = distances.get(dag.getVertices().get(0));
        Vertex startPoint = dag.getVertices().get(0);
        for (i = 1; i < numNodes; ++i)
            if (maxWeight <= distances.get(dag.getVertices().get(i))) {
                maxWeight = distances.get(dag.getVertices().get(i));
                startPoint = dag.getVertices().get(i);
            }
        addItemToKnapsack(parent, startPoint, knapsack, itemsToChoose);
    }
}
