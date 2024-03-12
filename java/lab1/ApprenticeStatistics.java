package org.example;

import java.util.*;

public class ApprenticeStatistics {
    public static Map<Mage, Integer> generateStatistics(Set<Mage> set, String sortingMode) {
        Map<Mage, Integer> statistics;

        if ("normal".equals(sortingMode)) {
            statistics = new TreeMap<>();
        } else if ("alternative".equals(sortingMode)) {
            statistics = new TreeMap<>(new MageComparator());
        } else {
            statistics = new HashMap<>();
        }

        for (var mage: set) {
            statistics.put(mage, countApprentices(mage));
        }
        return statistics;
    }

    private static int countApprentices(Mage mage) {
        int count = mage.getApprentices().size();
        for (var apprentice : mage.getApprentices()) {
            count += countApprentices(apprentice);
        }
        return count;
    }
}