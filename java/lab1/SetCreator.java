package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetCreator {
    public static Set<Mage> createSet(String sortingMode) {
        Set<Mage> set;

        if ("normal".equals(sortingMode)) {
            set = new TreeSet<>();
        } else if ("alternative".equals(sortingMode)) {
            set = new TreeSet<>(new MageComparator());
        } else {
            set = new HashSet<>();
        }
        return set;
    }

    public static Set<Mage> copySet(Set<Mage> set, String sortingMode) {
        Set<Mage> result;

        if ("normal".equals(sortingMode) || "alternative".equals(sortingMode)) {
            result = new TreeSet<>(set);
        } else {
            result = new HashSet<>(set);
        }
        return set;
    }
}