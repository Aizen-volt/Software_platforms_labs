import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "default";

    public static void main (String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        try {
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            transaction.begin();
            DataInitializer.initializeData(manager);
            transaction.commit();

            DataManipulator manipulator = new DataManipulator(manager);

            // List all mages
            System.out.println("Listing all mages:");
            manipulator.listMages();
            System.out.println();

            // List all towers
            System.out.println("Listing all towers:");
            manipulator.listTowers();
            System.out.println();

            // Add a mage
            System.out.println("Adding a new mage:");
            manipulator.addMage("New Mage", 50);
            System.out.println("New mage added.");
            System.out.println();

            // Remove a mage
            System.out.println("Removing a mage:");
            manipulator.removeMage("Mage 5");
            System.out.println("Mage 5 removed.");
            System.out.println();

            // Add a tower
            System.out.println("Adding a new tower:");
            manipulator.addTower("New Tower", 100, null);
            System.out.println("New tower added.");
            System.out.println();

            // Assign a mage to a tower
            System.out.println("Assigning a mage to a tower:");
            manipulator.assignMageToTower("Mage 1", "Tower 1");
            System.out.println("Mage 1 assigned to Tower 1.");
            System.out.println();

            // Remove a mage from a tower
            System.out.println("Removing a mage from a tower:");
            manipulator.removeMageFromTower("Mage 2", "Tower 1");
            System.out.println("Mage 2 removed from Tower 1.");
            System.out.println();

            // List all mages and towers after operations
            System.out.println("Listing all mages after operations:");
            manipulator.listMages();
            System.out.println();

            System.out.println("Listing all towers after operations:");
            manipulator.listTowers();
            System.out.println();

            manager.close();
        } finally {
            factory.close();
        }
    }
}
