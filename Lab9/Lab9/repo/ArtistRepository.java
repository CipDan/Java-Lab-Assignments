package Lab9.repo;

import Lab9.entity.ArtistsEntity;
import Lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * An implementation of a repository for artists.
 */
public class ArtistRepository {
    private EntityManagerFactory factory;

    /**
     * Creates a new artist repository.
     */
    public ArtistRepository() {
        factory = PersistenceUtil.getInstance().getFactory();
    }

    /**
     * Creates a new entry in the database using an <code>EntityManagerFactory</code>.
     *
     * @param artist - the new entity to be added.
     */
    public void create(ArtistsEntity artist) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Finds an entry based on its id.
     *
     * @param id - the artist's id.
     * @return the found instance if the key exists or null otherwise.
     */
    public ArtistsEntity findById(int id) {
        EntityManager entityManager = factory.createEntityManager();
        ArtistsEntity result = entityManager.find(ArtistsEntity.class, id);
        entityManager.close();
        return result;
    }

    /**
     * Find an entry based on its name.
     *
     * @param name - the artist's name.
     * @return the found instance if the name exists or null otherwise.
     */
    public List<ArtistsEntity> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<ArtistsEntity> result = (List<ArtistsEntity>) entityManager.createNamedQuery("ArtistsEntity.findByName").
                setParameter("name", name).getResultList();
        entityManager.close();
        return result;
    }
}
