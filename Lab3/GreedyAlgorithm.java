package Lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of a Greedy Algorithm.
 */
public class GreedyAlgorithm implements Algorithm {
    public GreedyAlgorithm() {
    }

    /**
     * Comparator.
     *
     * @param a first item to be compared
     * @param b second item to be compared
     * @return 1 if a < b, -1 if a > b and 0 if a = b
     */
    public static int compareByValue(Item a, Item b) {
        if (a.getValue() < b.getValue())
            return 1;
        else if (a.getValue() > b.getValue())
            return -1;
        else
            return 0;
    }

    @Override
    public void fillKnapsack(Knapsack knapsack, List<Item> itemsToChoose) {
        List<Item> aux = new ArrayList<>(itemsToChoose);
        aux.sort(GreedyAlgorithm::compareByValue);
        float currentWeight = 0;
        for (Item item : aux) {
            if (currentWeight + item.getWeight() <= 10) {
                currentWeight += item.getWeight();
                knapsack.addItem(item);
            }
        }
    }
}
