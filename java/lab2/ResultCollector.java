package org.example;

import java.util.ArrayList;
import java.util.List;

public class ResultCollector {
    private final List<Result> results = new ArrayList<>();

    public synchronized void modifyResult(Result previousResult, Result result) {
        results.remove(previousResult);
        results.add(result);
    }

    public synchronized List<Result> getResults() throws InterruptedException {
        return new ArrayList<>(results);
    }
}
