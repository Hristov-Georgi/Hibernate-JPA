import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class Ex_12_EmployeesMaximumSalaries {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        List<Department> departments = entityManager.createQuery("SELECT d FROM Department d"
                ,Department.class).getResultList();

        for (Department e : departments) {

            BigDecimal value = BigDecimal.valueOf(0);
            for (Employee em : e.getEmployees()) {

                if(value.compareTo(em.getSalary()) < 0) {
                    value = em.getSalary();
                }
            }

            if(value.compareTo(BigDecimal.valueOf(30000)) < 0
                    || value.compareTo(BigDecimal.valueOf(70000)) > 0) {

                System.out.print(e.getName() + " ");
                System.out.println(value);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
