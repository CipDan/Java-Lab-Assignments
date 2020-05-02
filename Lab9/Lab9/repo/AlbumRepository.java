package Lab9.repo;

import Lab9.entity.AlbumsEntity;
import Lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * An implementation of a repository for albums.
 */
public class AlbumRepository {
    private EntityManagerFactory factory;

    /**
     * Creates a new album repository.
     */
    public AlbumRepository() {
        factory = PersistenceUtil.getInstance().getFactory();
    }

    /**
     * Creates a new entry in the database using an <code>EntityManagerFactory</code>.
     *
     * @param album - the new entity to be added.
     */
    public void create(AlbumsEntity album) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Finds an entry based on its id.
     *
     * @param id - the album's id.
     * @return the found instance if the key exists or null otherwise.
     */
    public AlbumsEntity findById(int id) {
        EntityManager entityManager = factory.createEntityManager();
        AlbumsEntity result = entityManager.find(AlbumsEntity.class, id);
        entityManager.close();
        return result;
    }

    /**
     * Find an entry based on its name.
     *
     * @param name - the album's name.
     * @return the found instance if the name exists or null otherwise.
     */
    public List<AlbumsEntity> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<AlbumsEntity> result = (List<AlbumsEntity>) entityManager.createNamedQuery("AlbumsEntity.findByName").
                setParameter("name", name).getResultList();
        entityManager.close();
        return result;
    }

    /**
     * Find an entry based on its artist's id.
     *
     * @param id - the album's artist's id.
     * @return the found instance if the id exists or null otherwise.
     */
    public List<AlbumsEntity> findByArtist(int id) {
        EntityManager entityManager = factory.createEntityManager();
        List<AlbumsEntity> result = (List<AlbumsEntity>) entityManager.createNamedQuery("AlbumsEntity.findByArtist").
                setParameter("artistId", id).getResultList();
        entityManager.close();
        return result;
    }
}
