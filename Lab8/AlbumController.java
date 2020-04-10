package Lab8;

import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    List<Album> albums = new ArrayList<>();

    public void create(String name, int artistId, int releaseYear) {
        albums.add(new Album(name, artistId, releaseYear));
    }

    public Album findByArtist(int artistId) {
        for (Album album : albums) {
            if (album.getArtistId() == artistId)
                return album;
        }
        return null;
    }
}
