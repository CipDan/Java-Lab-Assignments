package Lab3;

import java.util.List;

/**
 * Interface for grouping different algorithms used for solving The Knapsack Problem.
 */
public interface Algorithm {
    void fillKnapsack(Knapsack knapsack, List<Item> itemsToChoose);
}
