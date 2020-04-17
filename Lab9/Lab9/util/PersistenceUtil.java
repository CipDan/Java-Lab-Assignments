package Lab9.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static PersistenceUtil singleInstance = null;
    private static EntityManagerFactory factory;

    private PersistenceUtil() {
        factory = Persistence.createEntityManagerFactory(
                "MusicAlbumsPU");
    }

    public static PersistenceUtil getInstance() {
        if (singleInstance == null) {
            singleInstance = new PersistenceUtil();
        }
        return singleInstance;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }
}
