package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResultCollector {
    private final List<Result> results = new ArrayList<>();

    public synchronized void addResult(Result result) {
        results.add(result);
    }

    public synchronized void modifyResult(Result previousResult, Result result) {
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).equals(previousResult)) {
                results.remove(i);
                results.add(result);
            }
        }
    }

    public synchronized List<Result> getResults() throws InterruptedException {
        return new ArrayList<>(results);
    }
}