package data;

import entities.Mage;
import entities.Tower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DataManipulator {
    private final EntityManager manager;
    private final Logger logger = LoggerFactory.getLogger(DataManipulator.class);

    public DataManipulator(EntityManager manager) {
        this.manager = manager;
    }

    public void listMages() {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            List<Mage> mages = manager.createQuery("SELECT m FROM Mage m", Mage.class).getResultList();
            for (Mage mage : mages) {
                System.out.println("-".repeat(20));
                System.out.println(mage);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while listing mages:", e);
        }
    }

    public void listTowers() {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            List<Tower> towers = manager.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();
            for (Tower tower : towers) {
                System.out.println("-".repeat(20));
                System.out.println(tower);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while listing towers:", e);
        }
    }


    public void addMage(String name, int level, String towerName) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Mage mage = new Mage();
            mage.setName(name);
            mage.setLevel(level);

            if (towerName != null) {
                Tower tower = manager.find(Tower.class, towerName);
                if (tower != null) {
                    mage.setTower(tower);
                    tower.addMage(mage);
                } else {
                    logger.warn("Tower with name {} not found. Added mage without tower assigned", towerName);
                }
            }

            manager.persist(mage);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while adding a mage:", e);
        }
    }

    public void removeMage(String name) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Mage mage = manager.find(Mage.class, name);
            if (mage != null) {
                Tower tower = mage.getTower();
                if (tower != null) {
                    tower.removeMage(mage);
                }
                manager.remove(mage);
            } else {
                logger.warn("Mage with name {} not found", name);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while removing a mage:", e);
        }
    }

    public Mage getMage(String name) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Mage mage = manager.find(Mage.class, name);

            transaction.commit();
            return mage;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while retrieving mage:", e);
            return null;
        }
    }


    public void addTower(String name, int height, List<Mage> mages) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Tower tower = new Tower();
            tower.setName(name);
            tower.setHeight(height);

            if (mages != null && !mages.isEmpty()) {
                for (Mage mage : mages) {
                    mage.setTower(tower);
                }
                tower.setMages(mages);
            }
            manager.persist(tower);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while adding a tower:", e);
        }
    }

    public void removeTower(String name) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();

            Tower tower = manager.find(Tower.class, name);
            if (tower != null) {
                for (Mage mage : tower.getMages()) {
                    mage.setTower(null);
                    manager.remove(mage);
                }
                manager.remove(tower);
            } else {
                logger.warn("Tower with name {} not found", name);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while removing a tower:", e);
        }
    }

    public void printTowersWithHeightGreaterThan(int minHeight) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            List<Tower> towers = manager.createQuery("SELECT t FROM Tower t WHERE t.height > :minHeight", Tower.class)
                    .setParameter("minHeight", minHeight)
                    .getResultList();
            for (Tower tower : towers) {
                System.out.println("-".repeat(20));
                System.out.println(tower);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("An error occurred while executing query: ", e);
        }
    }

}