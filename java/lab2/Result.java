package org.example;

public class Result {
    private final int ID;
    private double percentDone;
    private double sum;

    public Result(int ID, double percentDone, double sum) {
        this.ID = ID;
        this.percentDone = percentDone;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Task " + ID + ": done in " + percentDone * 100 + "%, current result: " + sum;
    }

    @Override
    public boolean equals(Object o) {
        Result temp = (Result)o;
        return temp.ID == this.ID;
    }
}
