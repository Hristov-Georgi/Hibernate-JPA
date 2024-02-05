
import entities.Employee;
import javax.persistence.*;
import java.util.Scanner;

public class Ex_03_ContainsEmployee {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String[] name = scanner.nextLine().split("\\s+");
        String firstName = name[0];
        String lastName = name[1];

        try {
            Employee result = entityManager.createQuery("SELECT e FROM Employee e" +
                            " WHERE firstName = :firstName AND lastName = :lastName", Employee.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getSingleResult();

            System.out.println("Yes");

        } catch (NoResultException ex) {
            System.out.println("No");

        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
