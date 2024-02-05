import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Ex_11_FindEmployeesByFirstName {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();

        List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e" +
                " WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + '%')
                .getResultList();


        for (Employee e : employeeList) {

            System.out.printf("%s %s - %s - ($%.2f)\n",
                    e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
