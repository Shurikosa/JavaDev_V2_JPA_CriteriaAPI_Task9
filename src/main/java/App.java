
import crud.CriteriaAPI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);
    private static  EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;
    private static final File file = new File("OSBB.txt");
    public static void main(String[] args) {
        LOGGER.info("Programm started");

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            List<Object[]> ownersWithoutCarAccess = CriteriaAPI.ownersProcessWithCriteriaAPI(entityManager);
            printOwners(ownersWithoutCarAccess);
            writeToFile(ownersWithoutCarAccess);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error(e.getMessage());
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        LOGGER.info("Finish program");
    }

    private static void printOwners(List<Object[]> list) {
        LOGGER.info("Prepare to print");

        for (Object[] result : list) {
            String firstName = (String) result[0];
            String lastName = (String) result[1];
            String email = (String) result[2];
            String buildingAddress = (String) result[3];
            int flatNumber = (int) result[4];
            int areaM2 = (int) result[5];

            System.out.println("Full Name: " + firstName + " " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Building Address: " + buildingAddress);
            System.out.println("Flat Number: " + flatNumber);
            System.out.println("Area (m2): " + areaM2);
            System.out.println();
            LOGGER.info("print finished");
        }
    }
    private static void writeToFile(List<Object[]> list) {
        LOGGER.info("Write to file start");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Object[] result : list) {
                String firstName = (String) result[0];
                String lastName = (String) result[1];
                String email = (String) result[2];
                String buildingAddress = (String) result[3];
                int flatNumber = (int) result[4];
                int areaM2 = (int) result[5];
                writer.write("Full Name: " + firstName + " " + lastName);
                writer.newLine();
                writer.write("Email: " + email);
                writer.newLine();
                writer.write("Building Address: " + buildingAddress);
                writer.newLine();
                writer.write("Flat Number: " + flatNumber);
                writer.newLine();
                writer.write("Area (m2): " + areaM2);
                writer.newLine();
                writer.newLine();
            }
            System.out.println("Data has been written to " + file);
        } catch (IOException e) {
           LOGGER.fatal(e);
        }
    }

}
