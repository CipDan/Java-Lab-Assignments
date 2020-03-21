package Lab4;

import java.util.Objects;

/**
 * A representation of a resident.
 */
public class Resident {
    private String name;

    /**
     * Creates a new resident.
     *
     * @param name the resident's name.
     */
    public Resident(String name) {
        this.name = name;
    }

    /**
     * Returns the resident's name.
     *
     * @return a <code>String</code> representing the resident's name.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return name.equals(resident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Resident{" +
                "name=" + name + '}';
    }
}