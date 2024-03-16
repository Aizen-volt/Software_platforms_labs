package org.example;

import java.util.*;

public class ApprenticeStatistics {
    public static Map<Mage, Integer> generateStatistics(Set<Mage> set, SortingMode sortingMode) {
        Map<Mage, Integer> statistics = sortingMode.createMap();

        for (Mage mage : set) {
            statistics.put(mage, countApprentices(mage));
        }
        return statistics;
    }

    private static int countApprentices(Mage mage) {
        int count = mage.getApprentices().size();
        for (Mage apprentice : mage.getApprentices()) {
            count += countApprentices(apprentice);
        }
        return count;
    }
}
