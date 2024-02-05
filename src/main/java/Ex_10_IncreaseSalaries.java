import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class Ex_10_IncreaseSalaries {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employeesSalaryPromotion = entityManager.createQuery("SELECT e FROM Employee e" +
                " WHERE e.department.name " +
                        "IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        for (Employee e : employeesSalaryPromotion) {

            BigDecimal newSalary = e.getSalary().multiply(BigDecimal.valueOf(1.12));

            entityManager.createQuery("UPDATE Employee e " +
                            " SET e.salary = :newSalary" +
                    " WHERE e.id = :id")
                    .setParameter("newSalary", newSalary)
                    .setParameter("id", e.getId())
                    .executeUpdate();

            System.out.printf("%s %s ($%.2f)\n", e.getFirstName(), e.getLastName(), e.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
