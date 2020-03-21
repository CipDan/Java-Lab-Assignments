package Lab4;

import java.util.Objects;

/**
 * A representation of a hospital.
 */
public class Hospital implements Comparable {
    private String name;
    private int capacity;

    /**
     * Creates a new hospital.
     *
     * @param name     the hospital's name.
     * @param capacity the hospital's resident capacity.
     */
    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Returns the hospital's name.
     *
     * @return a <code>String</code> representing the hospital's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the hospital's resident capacity.
     *
     * @return an <code>integer</code> representing the number of residents a hospital can have.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Implementation of <code>Comparable</code>'s <code>compareTo</code>.
     *
     * @param o The object with which we are making the comparison.
     * @return 1 if the current object's name is lexicographically bigger than <b>o</b>'s name,
     * 0 if they are equal and -1 otherwise .
     */
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
