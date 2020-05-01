package Lab8;

/**
 * An implementation of a artist.
 */
public class Artist {

    private int id;
    private String name;
    private String country;

    /**
     * Creates a new artist.
     * @param id - artist's id.
     * @param name - artist's name.
     * @param country - artist's country.
     */
    public Artist(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    /**
     * Returns the artist's name.
     * @return a <code>String</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the artist's country.
     * @return a <code>String</code>.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the artist's id.
     * @return an <code>integer</code>
     */
    public int getId() {
        return id;
    }
}
