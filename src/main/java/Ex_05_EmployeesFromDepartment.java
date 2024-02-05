import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class Ex_05_EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        String department = "Research and Development";

        List<Employee> employeeList =
                entityManager.createQuery("SELECT e" +
                        " FROM Employee e WHERE e.department.name = :department" +
                        " ORDER BY e.salary ASC, e.id ASC", Employee.class)
                        .setParameter("department", department)
                        .getResultList();

        for (Employee employee : employeeList) {
            System.out.printf("%s %s from %s - $%.2f%n",
            employee.getFirstName(), employee.getLastName(), employee.getDepartment(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
