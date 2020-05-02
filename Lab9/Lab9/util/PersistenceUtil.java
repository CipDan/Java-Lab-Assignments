package Lab9.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A <code>Singleton</code> implementation of a persistence utility tool.
 */
public class PersistenceUtil {
    private static PersistenceUtil singleInstance = null;
    private static EntityManagerFactory factory;

    /**
     * Creates a persistence utility tool.
     */
    private PersistenceUtil() {
        factory = Persistence.createEntityManagerFactory(
                "MusicAlbumsPU");
    }

    /**
     * Returns the <code>Singleton</code> instance of the object
     * (if the instance has not been created, the constructor is called).
     *
     * @return the <code>Singleton</code>.
     */
    public static PersistenceUtil getInstance() {
        if (singleInstance == null) {
            singleInstance = new PersistenceUtil();
        }
        return singleInstance;
    }

    /**
     * Returns a factory type entity.
     *
     * @return an <code>EntityFactoryManager</code>
     */
    public EntityManagerFactory getFactory() {
        return factory;
    }
}
