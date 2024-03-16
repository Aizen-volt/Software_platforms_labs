package org.example;

import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        int levelComparison = Integer.compare(mage1.getLevel(), mage2.getLevel());
        if (levelComparison != 0) {
            return levelComparison;
        }
        int nameComparison = mage1.getName().compareTo(mage2.getName());
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Double.compare(mage1.getPower(), mage2.getPower());
    }
}
