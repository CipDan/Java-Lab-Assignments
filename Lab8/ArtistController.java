package Lab8;

import java.util.ArrayList;
import java.util.List;

public class ArtistController {
    List<Artist> artists = new ArrayList<>();

    public void create(String name, String country) {
        artists.add(new Artist(name, country));
    }

    public Artist findByName(String name) {
        for (Artist artist : artists) {
            if (artist.getName().equals(name))
                return artist;
        }
        return null;
    }
}
