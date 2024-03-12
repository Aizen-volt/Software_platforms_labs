package org.example;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int threadsCount = Integer.parseInt(args[0]);

        TaskQueue taskQueue = new TaskQueue();
        ResultCollector resultCollector = new ResultCollector();

        Thread threads[] = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(new TaskConsumer(taskQueue, resultCollector));
            threads[i].start();
        }

        Scanner scanner = new Scanner(System.in);
        int id = 0;
        while (true) {
            //System.out.println("Enter a number of iterations to create a task (or -1 to finish):");
            int value = -1;
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                value = -1;
                e.printStackTrace();
            }
            if (value == -1) {
                break;
            }
            taskQueue.add(new Task(id, value));
            id++;
        }

        for (int i = 0; i < threadsCount; i++) {
            threads[i].stop();
        }

        try {
            System.out.println("Final results:");
            System.out.println(resultCollector.getResults());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}