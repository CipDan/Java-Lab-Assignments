package Lab4;

public class Resident {
    private String name;

    public Resident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "name=" + name + '}';
    }
}