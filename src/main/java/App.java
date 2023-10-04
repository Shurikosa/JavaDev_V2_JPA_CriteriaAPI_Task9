import crud.CriteriaAPI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.apache.log4j.Logger;
import java.util.List;
import static utils.Utils.printOwners;
import static utils.Utils.writeToFile;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class);
    private static  EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction transaction;

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

}
