import entities.Project;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ex_09_FindLatest10Projects {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery(
                "SELECT p FROM Project p" +
                " ORDER BY p.name ASC", Project.class)
                .setMaxResults(10)
                .getResultList();

        for (Project p : projects) {

            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s\n" +
                    " \tProject Start Date: %s\n" +
                    " \tProject End Date: %s\n",
                    p.getName(),
                    p.getDescription(),
                    p.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")),
                    p.getEndDate() == null ? "null" : p.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
