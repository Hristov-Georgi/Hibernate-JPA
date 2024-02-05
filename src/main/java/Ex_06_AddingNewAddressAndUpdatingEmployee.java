import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Ex_06_AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);

        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e" +
                " WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName)
                .getSingleResult();

        employee.setAddress(address);
        entityManager.persist(employee);



//        entityManager.createQuery("UPDATE Employee e" +
//                " SET e.address = :address" +
//                " WHERE e.lastName = :lastName")
//                .setParameter("lastName", lastName)
//                .setParameter("address", address).executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
