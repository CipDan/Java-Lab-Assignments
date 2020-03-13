package Lab3;

import java.util.List;

/**
 * Model of a dynamic programming algorithm.
 */
public class DynamicProgrammingAlgorithm implements Algorithm {
    public DynamicProgrammingAlgorithm() {
    }

    @Override
    public void fillKnapsack(Knapsack knapsack, List<Item> itemsToChoose) {
        int numItems = itemsToChoose.size();
        int knapsackCapacity = knapsack.getCapacity();
        float[][] m = new float[numItems + 1][knapsackCapacity + 1];
        int i, j;
        for (i = 1; i <= numItems; ++i) {
            for (j = 0; j < knapsackCapacity + 1; ++j) {
                if (itemsToChoose.get(i - 1).getWeight() > j)
                    m[i][j] = m[i - 1][j];
                else
                    m[i][j] = Math.max(m[i - 1][j],
                            m[i - 1][j - itemsToChoose.get(i - 1).getWeight()] + itemsToChoose.get(i - 1).getValue());
            }
        }
        float result = m[numItems][knapsackCapacity];
        int weight = knapsackCapacity;
        for (i = numItems; i > 0 && result > 0; --i) {
            if (result == m[i - 1][weight])
                continue;
            else {
                knapsack.addItem(itemsToChoose.get(i - 1));
                result -= itemsToChoose.get(i - 1).getValue();
                weight -= itemsToChoose.get(i - 1).getWeight();
            }
        }
    }
}
