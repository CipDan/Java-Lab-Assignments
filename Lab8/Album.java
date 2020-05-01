package Lab8;

/**
 * An implementation of an album.
 */
public class Album {

    private int id;
    private String name;
    private int artistId;
    private int releaseYear;

    /**
     * Creates a new album.
     * @param id - album's id.
     * @param name - album's name.
     * @param artistId - album's artist (given through its id).
     * @param releaseYear - album's release year.
     */
    public Album(int id, String name, int artistId, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    /**
     * Returns the album's name.
     * @return A <code>String</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the album's artist (through its id).
     * @return an <code>integer</code>.
     */
    public int getArtistId() {
        return artistId;
    }

    /**
     * Returns the album's release year.
     * @return an <code>integer</code>.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Returns the album's id.
     * @return an <code>integer</code>.
     */
    public int getId() {
        return id;
    }
}
