package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String sortingMode = args[0];
        Set<Mage> set = SetCreator.createSet(sortingMode);

        Mage mage1 = new Mage("Gandalf", 20, 100.0, sortingMode);
        Mage apprentice1 = new Mage("Dumbledore", 15, 80.0, sortingMode);
        Mage apprentice2 = new Mage("Harry", 10, 50.0, sortingMode);
        Mage apprentice3 = new Mage("Gargamel", 5, 30.0, sortingMode);

        mage1.addApprentice(apprentice1);
        apprentice1.addApprentice(apprentice2);
        apprentice2.addApprentice(apprentice3);


        Mage mage2 = new Mage("Merlin", 30, 150.0, sortingMode);
        Mage apprentice4 = new Mage("Saruman", 25, 120.0, sortingMode);
        Mage apprentice5 = new Mage("Voldemort", 12, 70.0, sortingMode);

        mage2.addApprentice(apprentice4);
        mage2.addApprentice(apprentice5);

        Mage mage5 = new Mage("Hermiona", 8, 60.0, sortingMode);
        Mage mage6 = new Mage("Radagast", 18, 90.0, sortingMode);
        Mage mage7 = new Mage("Frodo", 7, 40.0, sortingMode);

        set.add(mage1);
        set.add(mage2);
        set.add(apprentice4);
        set.add(apprentice5);
        set.add(mage5);
        set.add(mage6);
        set.add(mage7);
        set.add(apprentice1);
        set.add(apprentice2);
        set.add(apprentice3);

        for (var mage: set) {
            System.out.println(mage);
        }

        System.out.println("\n");

        int i = 1;
        for (var mage: set) {
            System.out.println(mage.printRecursive(i, 1, 1));
            i++;
        }

        Map<Mage, Integer> statistics = ApprenticeStatistics.generateStatistics(set, sortingMode);
        System.out.println(statistics);
    }
}