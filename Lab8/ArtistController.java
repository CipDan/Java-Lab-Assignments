package Lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of a DAO object.
 */
public class ArtistController {
    List<Artist> artists = new ArrayList<>();

    /**
     * Creates a new entry for the database.
     * @param id - entry's id.
     * @param name - name of the artist.
     * @param country - country of the artist.
     */
    public void create(int id, String name, String country) {
        artists.add(new Artist(id, name, country));
    }

    /**
     * Finds an entry based on the `name` field.
     * @param name - a name.
     * @return the artist found or <code>null</code>.
     */
    public Artist findByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equals(name))
                return artist;
        }
        return null;
    }
}
