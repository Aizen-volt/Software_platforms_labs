package org.example;

public class TaskConsumer implements Runnable {
    private final TaskQueue taskQueue;
    private final ResultCollector resultCollector;
    private volatile boolean running = true;

    public TaskConsumer(TaskQueue taskQueue, ResultCollector resultCollector) {
        this.taskQueue = taskQueue;
        this.resultCollector = resultCollector;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Task currentTask = taskQueue.get();
                while (!currentTask.isFinished() && running) {
                    currentTask.calculate();
                    resultCollector.modifyResult(currentTask.getResult(), currentTask.getResult());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
