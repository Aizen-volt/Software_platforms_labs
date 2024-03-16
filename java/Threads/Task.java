package org.example;

public class Task {
    private final int ID;
    private final int argument;
    private double percentDone = 0;
    private double sum = 0;
    private int i = 1;

    public Task(int ID, int argument) {
        this.ID = ID;
        this.argument = argument;
    }

    public synchronized void calculate() {
        try {
            Thread.sleep(1000); // Simulate time to calculate a task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (i < argument) {
            sum += 4 * (Math.pow(-1, i - 1)) / (2 * i - 1);
            i++;
            percentDone = (double) i / argument;
        }
    }

    public synchronized Result getResult() {
        return new Result(ID, percentDone, sum);
    }

    public synchronized boolean isFinished() {
        return i == argument;
    }

    @Override
    public synchronized String toString() {
        return "Task " + ID + ": done in " + percentDone * 100 + "%, current result: " + sum;
    }
}
