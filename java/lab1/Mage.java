package org.example;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable<Mage> {
    private final String name;
    private final int level;
    private final double power;
    private final Set<Mage> apprentices;

    public Mage(String name, int level, double power, SortingMode sortingMode) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = sortingMode.createSet();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Mage> getApprentices() {
        return Collections.unmodifiableSet(apprentices);
    }

    public void addApprentice(Mage apprentice) {
        apprentices.add(apprentice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power);
    }

    @Override
    public int compareTo(Mage otherMage) {
        int levelComparison = Integer.compare(this.level, otherMage.level);
        if (levelComparison != 0) {
            return levelComparison;
        }
        int nameComparison = this.name.compareTo(otherMage.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Double.compare(this.power, otherMage.power);
    }

    public String printRecursive(int startNumber) {
        StringBuilder output = new StringBuilder();
        printRecursiveHelper(output, this, String.valueOf(startNumber), 0);
        return output.toString();
    }

    private void printRecursiveHelper(StringBuilder output, Mage mage, String numbering, int depth) {
        output.append(getIndentation(depth)).append(numbering).append(" ").append(mage.getName()).append("\n");
        int nextNumber = 1;
        for (Mage apprentice : mage.getApprentices()) {
            printRecursiveHelper(output, apprentice, numbering + "." + nextNumber, depth + 1);
            nextNumber++;
        }
    }

    private String getIndentation(int depth) {
        return "  ".repeat(Math.max(0, depth));
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }
}
