import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import java.util.*;

public class Ex_08_GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        Scanner scanner = new Scanner(System.in);

        int id = Integer.parseInt(scanner.nextLine());

        entityManager.getTransaction().begin();


        Employee employeeById = entityManager.createQuery("SELECT e" +
                " FROM Employee e" +
                " WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        System.out.printf("%s %s - %s%n",
                employeeById.getFirstName(), employeeById.getLastName(), employeeById.getJobTitle());

        List<String> projectsList = new ArrayList<>();

        for (Project project : employeeById.getProjects()) {
            projectsList.add(project.getName());
        }

        Collections.sort(projectsList);

        for (String p : projectsList) {
            System.out.printf("     %s%n",p);

        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
