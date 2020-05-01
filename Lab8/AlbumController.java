package Lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of a DAO object
 */
public class AlbumController {
    List<Album> albums = new ArrayList<>();

    /**
     * Creates a new entry for the database.
     * @param id - entry's id.
     * @param name - name of the album.
     * @param artistId - id of artist.
     * @param releaseYear - album's year of release.
     */
    public void create(int id, String name, int artistId, int releaseYear) {
        albums.add(new Album(id, name, artistId, releaseYear));
    }

    /**
     * Finds an entry based on the `artistId` field.
     * @param artistId - an artist's id.
     * @return the album found or <code>null</code>.
     */
    public Album findByArtist(int artistId) {
        for (Album album : albums) {
            if (album.getArtistId() == artistId)
                return album;
        }
        return null;
    }
}
