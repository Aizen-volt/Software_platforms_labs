import Entities.Mage;
import Entities.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DataManipulator {
    private final EntityManager manager;

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
                System.out.println("Mage: " + mage.getName() + ", Level: " + mage.getLevel());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void listTowers() {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            List<Tower> towers = manager.createQuery("SELECT t FROM Tower t", Tower.class).getResultList();
            for (Tower tower : towers) {
                System.out.println("-".repeat(20));
                System.out.println("Tower: " + tower.getName() + ", Height: " + tower.getHeight());
                List<Mage> mages = tower.getMages();
                if (!mages.isEmpty()) {
                    System.out.println("Mages in this tower:");
                    for (Mage mage : mages) {
                        System.out.println("- " + mage.getName());
                    }
                } else {
                    System.out.println("No mages in this tower.");
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void addMage(String name, int level) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Mage mage = new Mage();
            mage.setName(name);
            mage.setLevel(level);
            manager.persist(mage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void removeMage(String name) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Mage mage = manager.find(Mage.class, name);
            if (mage != null) {
                manager.remove(mage);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void addTower(String name, int height, List<Mage> mages) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Tower tower = new Tower();
            tower.setName(name);
            tower.setHeight(height);
            if (mages != null) {
                tower.setMages(mages);
            }
            manager.persist(tower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void removeTower(String name) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Tower tower = manager.find(Tower.class, name);
            if (tower != null) {
                manager.remove(tower);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void assignMageToTower(String mageName, String towerName) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Mage mage = manager.find(Mage.class, mageName);
            Tower tower = manager.find(Tower.class, towerName);
            if (mage != null && tower != null) {
                tower.addMage(mage);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void removeMageFromTower(String mageName, String towerName) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Mage mage = manager.find(Mage.class, mageName);
            Tower tower = manager.find(Tower.class, towerName);
            if (mage != null && tower != null) {
                tower.removeMage(mage);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void query() {
        // Implement this method
    }
}
