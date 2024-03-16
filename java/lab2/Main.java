package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static boolean shouldContinue = true;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the number of threads as an argument.");
            return;
        }

        int threadsCount = Integer.parseInt(args[0]);

        TaskQueue taskQueue = new TaskQueue();
        ResultCollector resultCollector = new ResultCollector();

        TaskConsumer[] consumers = startConsumerThreads(threadsCount, taskQueue, resultCollector);

        acceptUserInput(taskQueue);

        stopConsumerThreads(consumers);

        printFinalResults(resultCollector);
    }

    private static TaskConsumer[] startConsumerThreads(int threadsCount, TaskQueue taskQueue, ResultCollector resultCollector) {
        TaskConsumer[] consumers = new TaskConsumer[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            consumers[i] = new TaskConsumer(taskQueue, resultCollector);
            new Thread(consumers[i]).start();
        }
        return consumers;
    }

    private static void acceptUserInput(TaskQueue taskQueue) {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        while (shouldContinue) {
            int value;
            try {
                System.out.println("Enter a number of iterations to create a task (or -1 to finish):");
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                continue;
            }
            if (value == -1) {
                shouldContinue = false;
                break;
            }
            taskQueue.add(new Task(id++, value));
        }
        scanner.close();
    }

    private static void stopConsumerThreads(TaskConsumer[] consumers) {
        for (TaskConsumer consumer : consumers) {
            consumer.stop();
        }
    }

    private static void printFinalResults(ResultCollector resultCollector) {
        try {
            System.out.println("Final results:");
            System.out.println(resultCollector.getResults());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
