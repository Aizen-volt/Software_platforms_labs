package application;

import data.DataManipulator;
import entities.Mage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuHandler {

    private final DataManipulator manipulator;

    public MenuHandler(DataManipulator manipulator) {
        this.manipulator = manipulator;
    }

    public void executeAction(MenuOption option) {
        switch (option) {
            case LIST_MAGES:
                listMages();
                break;
            case LIST_TOWERS:
                listTowers();
                break;
            case ADD_MAGE:
                addMage();
                break;
            case ADD_TOWER:
                addTower();
                break;
            case REMOVE_MAGE:
                removeMage();
                break;
            case REMOVE_TOWER:
                removeTower();
                break;
            case PRINT_TOWERS_WITH_HEIGHT_GREATER_THAN:
                printTowersWithHeightGreaterThan();
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private void listMages() {
        manipulator.listMages();
    }

    private void listTowers() {
        manipulator.listTowers();
    }

    private void addMage() {
        String name = InputHandler.getUserInput("Enter name");
        int level = InputHandler.getIntInput("Enter level");
        String towerName = InputHandler.getUserInput("Enter tower (empty for none)");
        manipulator.addMage(name, level, towerName);
    }

    private void addTower() {
        String towerName = InputHandler.getUserInput("Enter name");
        int height = InputHandler.getIntInput("Enter height");
        String magesString = InputHandler.getUserInput("Enter mages (separated by comma, empty for none)");
        String[] mageNames = magesString.replace(", ", ",").split(",");

        boolean allMagesExist = Arrays.stream(mageNames)
                .allMatch(name -> manipulator.getMage(name) != null);

        if (allMagesExist) {
            List<Mage> mages = new ArrayList<>();
            for (String mageName : mageNames) {
                Mage mage = manipulator.getMage(mageName);
                mages.add(mage);
            }
            manipulator.addTower(towerName, height, mages);
        } else {
            System.out.println("One or more mage names provided do not exist in the database.");
        }
    }

    private void removeMage() {
        String mageName = InputHandler.getUserInput("Enter name");
        manipulator.removeMage(mageName);
    }

    private void removeTower() {
        String towerName = InputHandler.getUserInput("Enter name");
        manipulator.removeTower(towerName);
    }

    private void printTowersWithHeightGreaterThan() {
        int height = InputHandler.getIntInput("Enter height");
        manipulator.printTowersWithHeightGreaterThan(height);
    }
}
