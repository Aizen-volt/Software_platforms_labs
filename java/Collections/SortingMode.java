package org.example;

import java.util.*;

public enum SortingMode {
    NORMAL {
        @Override
        public SortedSet<Mage> createSet() {
            return new TreeSet<>();
        }

        @Override
        public SortedMap<Mage, Integer> createMap() {
            return new TreeMap<>();
        }
    },
    ALTERNATIVE {
        @Override
        public SortedSet<Mage> createSet() {
            return new TreeSet<>(new MageComparator());
        }

        @Override
        public SortedMap<Mage, Integer> createMap() {
            return new TreeMap<>(new MageComparator());
        }
    },
    DEFAULT {
        @Override
        public Set<Mage> createSet() {
            return new HashSet<>();
        }

        @Override
        public Map<Mage, Integer> createMap() {
            return new HashMap<>();
        }
    };

    public abstract Set<Mage> createSet();

    public abstract Map<Mage, Integer> createMap();
}
