package Lab3;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int capacity;
    private List<Item> items = new ArrayList<>();

    public Knapsack() {
    }

    public Knapsack(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public static int compareByName(Item a, Item b) {
        return a.getName().compareTo(b.getName());
    }

    public int getTotal_value() {
        int val = 0;
        for (Item item : items) {
            val += item.getValue();
        }
        return val;
    }

    public int getTotal_weight() {
        int weight = 0;
        for (Item item : items) {
            weight += item.getWeight();
        }
        return weight;
    }

    public void emptyKnapsack() {
        items.clear();
    }

    @Override
    public String toString() {
        items.sort(Knapsack::compareByName);
        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < items.size() - 1; ++i) {
            aux.append(items.get(i).getName());
            aux.append(", ");
        }
        aux.append(items.get(items.size() - 1).getName());
        return "selected items: " + aux + "(total weight=" + getTotal_weight() + ", total value=" + getTotal_value() + ")";
    }
}
