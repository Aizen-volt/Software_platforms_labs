package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

public class Mage implements Comparable {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;
    private String sortingMode;

    public Mage(String name, int level, double power, String sortingMode) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = SetCreator.createSet(sortingMode);
        this.sortingMode = sortingMode;
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
        return SetCreator.copySet(apprentices, sortingMode);
    }

    public void addApprentice(Mage mage) {
        apprentices.add(mage);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;

        Mage mage = (Mage) other;
        return this.name.equals(mage.name)
                && this.level == mage.level
                && this.power == mage.power
                && this.apprentices == mage.apprentices;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(power) * level;
        return 983 + name.hashCode() + 643 * (int)(temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Mage{name='" + name + "', level=" + level + ", power=" + power + "}";
    }

    public String printRecursive(int number, int subNumber, int depth) {
        StringBuilder output = new StringBuilder();
        output.append("\t".repeat(depth - 1));
        if (depth == 1)
            output.append(number + "." + this + "\n");

        int i = 1;
        for (var apprentice: apprentices) {
            output.append(apprentice.printRecursive(apprentice, depth, i, number));
            i++;
        }
        i = number;
        for (var apprentice: apprentices) {
            output.append(apprentice.printRecursive(i, i, depth + 1));
            i++;
        }
        return output.toString();
    }

    private String printRecursive(Mage mage, int depth, int number, int subNumber) {
        StringBuilder output = new StringBuilder();

        output.append("\t".repeat(depth))
                .append((subNumber + ".").repeat(depth))
                .append(number + "." + this + "\n");
        return output.toString();
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || getClass() != o.getClass())
            throw new IllegalArgumentException("Argument is not of type Mage");

        Mage mage = (Mage) o;

        int nameComparison = this.name.compareTo(mage.name);
        if (nameComparison != 0)
            return nameComparison;

        if (this.level != mage.level)
            return Integer.compare(this.level, mage.level);

        return Double.compare(this.power, mage.power);
    }
}