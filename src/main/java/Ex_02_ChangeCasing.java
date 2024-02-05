import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class Ex_02_ChangeCasing {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        Query townQuery = entityManager.createQuery("SELECT t FROM Town t", Town.class);

        List<Town> townsList = townQuery.getResultList();

        for (Town town : townsList) {
            String townName = town.getName();

            if (townName.length() <= 5) {
                String upperCase = townName.toUpperCase();
                town.setName(upperCase);

                entityManager.persist(town);
            }

        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
