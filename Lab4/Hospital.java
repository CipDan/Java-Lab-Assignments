package Lab4;

import java.util.Objects;

public class Hospital implements Comparable {
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public int compareTo(Object o) {
        Hospital aux = (Hospital) o;
        return this.getName().compareTo(((Hospital) o).getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return capacity == hospital.capacity &&
                name.equals(hospital.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name=" + name +
                ", capacity=" + capacity + '}';
    }
}
