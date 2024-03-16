package org.example;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Sorting mode argument missing.");
            return;
        }

        SortingMode sortingMode = SortingMode.valueOf(args[0].toUpperCase());
        Set<Mage> set = sortingMode.createSet();

        Mage mage1 = new Mage("Gandalf", 20, 100.0, sortingMode);
        Mage mage2 = new Mage("Merlin", 30, 150.0, sortingMode);
        Mage apprentice1 = new Mage("Harry", 10, 50.0, sortingMode);
        Mage apprentice2 = new Mage("Dumbledore", 15, 80.0, sortingMode);
        Mage apprentice3 = new Mage("Gargamel", 5, 30.0, sortingMode);

        mage1.addApprentice(apprentice1);
        mage1.addApprentice(apprentice2);
        mage2.addApprentice(apprentice3);

        set.add(mage1);
        set.add(mage2);
        set.add(apprentice1);
        set.add(apprentice2);
        set.add(apprentice3);

        Mage mage3 = new Mage("Saruman", 25, 120.0, sortingMode);
        Mage apprentice4 = new Mage("Voldemort", 12, 70.0, sortingMode);
        Mage apprentice5 = new Mage("Hermiona", 8, 60.0, sortingMode);

        mage1.addApprentice(mage3);
        mage3.addApprentice(apprentice4);
        apprentice4.addApprentice(apprentice5);

        set.add(mage3);
        set.add(apprentice4);
        set.add(apprentice5);

        Mage mage4 = new Mage("Radagast", 18, 90.0, sortingMode);
        Mage apprentice6 = new Mage("Frodo", 7, 40.0, sortingMode);
        Mage apprentice7 = new Mage("Gollum", 6, 35.0, sortingMode);

        apprentice1.addApprentice(mage4);
        mage4.addApprentice(apprentice6);
        apprentice6.addApprentice(apprentice7);

        set.add(mage4);
        set.add(apprentice6);
        set.add(apprentice7);

        int i = 1;
        for (Mage mage : set) {
            System.out.print(mage.printRecursive(i));
            i++;
        }

        Map<Mage, Integer> statistics = ApprenticeStatistics.generateStatistics(set, sortingMode);
        System.out.println(statistics);
    }
}
