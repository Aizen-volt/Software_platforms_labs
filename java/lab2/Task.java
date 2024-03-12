package org.example;

public class Task {
    private final int ID;
    private final int argument;
    private double percentDone;
    private double sum;
    private int i;

    public Task(int ID, int argument) {
        this.ID = ID;
        this.argument = argument;
        percentDone = 0;
        sum = 0;
        i = 1;
    }

    public void calculate() {
        try {
            Thread.sleep(1000); // Simulate time to calculate a task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (i < argument) {
            sum += 4 * (Math.pow(-1, i - 1)) / (2 * i - 1);
            i++;
            percentDone = (double)i / argument;
        }
    }

    public Result getResult() {
        return new Result(ID, percentDone, sum);
    }

    public boolean isFinished() {
        return i == argument;
    }

    @Override
    public String toString() {
        return "Task " + ID + ": done in " + percentDone * 100 + "%, current result: " + sum;
    }
}