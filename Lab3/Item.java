package Lab3;

/**
 * Interface for grouping different kinds of items.
 */
public interface Item {
    String getName();

    int getValue();

    int getWeight();

    /**
     * Computes the profit factor of the item.
     *
     * @return a <code>float</code> representing the profit factor of the item
     */
    default float profitFactor() {
        return (float) getValue() / getWeight();
    }
}
