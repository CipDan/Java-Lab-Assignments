package Lab3;

public class Food implements Item {
    private String name;
    private int weight;

    public Food(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return weight * 2;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
