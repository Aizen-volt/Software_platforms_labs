package Entities;

import com.sun.istack.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;
    private int height;
    @OneToMany(mappedBy = "tower")
    private List<Mage> mages;

    public Tower() {
        this.mages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    public void addMage(Mage mage) {
        mages.add(mage);
    }

    public void removeMage(Mage mage) {
        mages.remove(mage);
    }

    public void clearMages() {
        mages.clear();
    }
}
