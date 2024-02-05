import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateConnection {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private CreateConnection(){}

    public static EntityManager getEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PU_name");
        return entityManager = entityManagerFactory.createEntityManager();
    }
}