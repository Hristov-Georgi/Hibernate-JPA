import javax.persistence.EntityManager;
import java.util.List;

public class Ex_04_EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        List<String> employeesWithSalaryOver50000 =
                entityManager.createQuery("SELECT e.firstName FROM Employee e" +
                        " WHERE e.salary > 50000", String.class).getResultList();

        for (String employee : employeesWithSalaryOver50000) {
            System.out.println(employee);

        }

//        Query query = entityManager.createQuery("SELECT e FROM Employee e");
//        List<Employee> employeeList = query.getResultList();
//
//        for (Employee employee : employeeList) {
//            BigDecimal salary = new BigDecimal(String.valueOf(employee.getSalary()));
//            BigDecimal sumToCompare = new BigDecimal(50000);
//
//            if (salary.compareTo(sumToCompare) > 0) {
//                System.out.println(employee.getFirstName());
//            }
//        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
