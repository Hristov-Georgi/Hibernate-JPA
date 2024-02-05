import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Ex_13_RemoveTowns {
    public static void main(String[] args) {

        EntityManager entityManager = CreateConnection.getEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String townName = scanner.nextLine();

        Town town = entityManager.createQuery("SELECT t FROM Town t" +
                " WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address a" +
                " JOIN a.town t WHERE t.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();

        entityManager.createQuery("UPDATE Employee e" +
                " SET e.address = NULL" +
                " WHERE e.address IN (:addresses)")
                .setParameter("addresses", addresses)
                .executeUpdate();

        int deletedAddresses = 0;
        for (Address a: addresses) {
            entityManager.remove(a);
            deletedAddresses++;
        }

        entityManager.remove(town);

        String addressText = "";

        if(deletedAddresses <= 1) {
            addressText = "address";
        } else {
            addressText = "addresses";
        }

        System.out.printf("%d %s in %s deleted", deletedAddresses, addressText, townName);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
