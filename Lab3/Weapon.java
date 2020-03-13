package Lab3;

public class Weapon implements Item {
    private WeaponType type;
    private int weight;
    private int value;

    public Weapon(WeaponType type, int weight, int value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
