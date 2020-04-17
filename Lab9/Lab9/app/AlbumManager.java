package Lab9.app;

import Lab9.entity.AlbumsEntity;
import Lab9.entity.ArtistsEntity;
import Lab9.repo.AlbumRepository;
import Lab9.repo.ArtistRepository;
import Lab9.util.PersistenceUtil;

import java.util.List;

public class AlbumManager {
    public static void main(String[] args){
        PersistenceUtil.getInstance();
        ArtistRepository artistRepository = new ArtistRepository();
        AlbumRepository albumRepository = new AlbumRepository();
        /*ArtistsEntity artist1 = new ArtistsEntity();
        artist1.setId(1);
        artist1.setName("The Rolling Stones");
        artist1.setCountry("United Kingdom");
        AlbumsEntity album1 = new AlbumsEntity();
        album1.setId(1);
        album1.setName("Aftermath");
        album1.setReleaseYear(1966);
        album1.setArtistId(artist1.getId());
        System.out.println(artist1.getId());
        System.out.println(album1.getId());
        artistRepository.create(artist1);
        albumRepository.create(album1);*/
        List<AlbumsEntity> album1 = albumRepository.findByArtist(1);
        List<AlbumsEntity> album2 = albumRepository.findByName("Aftermath");
        System.out.println("name of 1st album is " + album1.get(0).getName() + " and the name of the 2nd is " +
                album2.get(0).getName());
        ArtistsEntity artist1 = artistRepository.findById(1);
        List<ArtistsEntity> artist2 = artistRepository.findByName("The Rolling Stones");
        System.out.println("name of 1st artist is " + artist1.getName() + " and the name of the 2nd is " +
                artist2.get(0).getName());
        System.out.println("\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D\uD83D\uDC4D");
    }
}
