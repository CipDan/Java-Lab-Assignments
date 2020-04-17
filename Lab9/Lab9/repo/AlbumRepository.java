package Lab9.repo;

import Lab9.entity.AlbumsEntity;
import Lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AlbumRepository {
    private EntityManagerFactory factory;

    public AlbumRepository() {
        factory = PersistenceUtil.getInstance().getFactory();
    }

    public void create(AlbumsEntity album) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public AlbumsEntity findById(int id) {
        EntityManager entityManager = factory.createEntityManager();
        AlbumsEntity result = entityManager.find(AlbumsEntity.class, id);
        entityManager.close();
        return result;
    }

    public List<AlbumsEntity> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<AlbumsEntity> result = (List<AlbumsEntity>) entityManager.createNamedQuery("AlbumsEntity.findByName").
                setParameter("name", name).getResultList();
        entityManager.close();
        return result;
    }

    public List<AlbumsEntity> findByArtist(int id) {
        EntityManager entityManager = factory.createEntityManager();
        List<AlbumsEntity> result = (List<AlbumsEntity>) entityManager.createNamedQuery("AlbumsEntity.findByArtist").
                setParameter("artistId", id).getResultList();
        entityManager.close();
        return result;
    }
}
