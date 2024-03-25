import Entities.Mage;
import Entities.Tower;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    private static final int NUMBER_OF_MAGES = 10;
    private static final int NUMBER_OF_TOWERS = 3;

    public static void initializeData(EntityManager manager)  {
        List<Mage> mages = createMages(manager);
        createTowers(manager, mages);
    }

    private static List<Mage> createMages(EntityManager manager) {
        List<Mage> mages = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_MAGES; i++) {
            Mage mage = new Mage();
            mage.setName("Mage " + i);
            mage.setLevel(i * NUMBER_OF_MAGES);
            mages.add(mage);
            manager.persist(mage);
        }
        return mages;
    }

    private static void createTowers(EntityManager manager, List<Mage> mages) {
        for (int i = 1; i <= NUMBER_OF_TOWERS; i++) {
            Tower tower = new Tower();
            tower.setName("Tower " + i);
            tower.setHeight(i * NUMBER_OF_TOWERS);

            List<Mage> towerMages = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_MAGES; j++) {
                if (j % NUMBER_OF_TOWERS == i - 1) {
                    towerMages.add(mages.get(j));
                }
            }
            tower.setMages(towerMages);

            manager.persist(tower);
        }
    }
}
