import entities.Address;

import javax.persistence.EntityManager;

public class Ex_07_AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT a FROM Address a" +
                " ORDER BY size(a.employees) DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> System.out.printf("%s, %s - %d employees%n",
                        a.getText(), a.getTown(), a.getEmployees().size()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
