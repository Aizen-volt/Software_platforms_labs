package application;

import data.DataInitializer;
import data.DataManipulator;
import data.PersistenceConfig;
import entities.Mage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final EntityManagerFactory factory = PersistenceConfig.getEntityManagerFactory();
    private static final EntityManager manager = factory.createEntityManager();
    private static final DataManipulator manipulator = new DataManipulator(manager);
    private static final MenuHandler menuHandler = new MenuHandler(manipulator);

    public static void main(String[] args) {
        DataInitializer.initializeData(manager, 20, 10);

        try {
            while (true) {
                displayMenu();
                MenuOption option = InputHandler.getMenuOption("Option");
                menuHandler.executeAction(option);
            }
        } finally {
            manager.close();
            PersistenceConfig.closeEntityManagerFactory();
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("Menu Options:");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%s - %s%n", option.getCode(), option.getDescription());
        }
    }
}
