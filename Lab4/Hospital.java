package Lab4;

public class Hospital implements Comparable{
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Hospital aux = (Hospital) o;
        return this.getName().compareTo(((Hospital) o).getName());
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name=" + name +
                ", capacity=" + capacity + '}';
    }
}
