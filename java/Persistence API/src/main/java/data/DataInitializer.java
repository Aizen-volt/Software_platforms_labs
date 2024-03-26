package data;

import entities.Mage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    public static void initializeData(EntityManager manager, int numberOfMages, int numberOfTowers) {
        DataManipulator dataManipulator = new DataManipulator(manager);
        try {
            createTowers(dataManipulator, numberOfTowers);
            createMages(dataManipulator, numberOfMages);
        } catch (Exception e) {
            logger.error("An error occurred while initializing data:", e);
        }
    }

    private static void createMages(DataManipulator manipulator, int numberOfMages) {
        for (int i = 1; i <= numberOfMages; i++) {
            manipulator.addMage("Mage " + i, i, "Tower " + ((i / 2) + 1));
        }
    }

    private static void createTowers(DataManipulator manipulator, int numberOfTowers) {
        for (int i = 1; i <= numberOfTowers; i++) {
            manipulator.addTower("Tower " + i, (int)Math.pow(2, i), null);
        }
    }
}
