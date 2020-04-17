package Lab9.repo;

import Lab9.entity.ArtistsEntity;
import Lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ArtistRepository {
    private EntityManagerFactory factory;

    public ArtistRepository(){
        factory = PersistenceUtil.getInstance().getFactory();
    }

    public void create(ArtistsEntity artist){
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public ArtistsEntity findById(int id) {
        EntityManager entityManager = factory.createEntityManager();
        ArtistsEntity result = entityManager.find(ArtistsEntity.class, id);
        entityManager.close();
        return result;
    }

    public List<ArtistsEntity> findByName(String name) {
        EntityManager entityManager = factory.createEntityManager();
        List<ArtistsEntity> result = (List<ArtistsEntity>) entityManager.createNamedQuery("ArtistsEntity.findByName").
                setParameter("name", name).getResultList();
        entityManager.close();
        return result;
    }
}
