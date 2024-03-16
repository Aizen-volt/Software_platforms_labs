package org.example;

import java.util.Objects;

public class Result {
    private final int ID;
    private final double percentDone;
    private final double sum;

    public Result(int ID, double percentDone, double sum) {
        this.ID = ID;
        this.percentDone = percentDone;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return ID == result.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Task " + ID + ": done in " + percentDone * 100 + "%, current result: " + sum;
    }
}
