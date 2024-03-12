package org.example;

public class TaskConsumer implements Runnable {
    private TaskQueue taskQueue;
    private ResultCollector resultCollector;
    private Task currentTask;
    private Result previousResult;

    public TaskConsumer(TaskQueue taskQueue, ResultCollector resultCollector) {
        this.taskQueue = taskQueue;
        this.resultCollector = resultCollector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                currentTask = taskQueue.get();
                previousResult = currentTask.getResult();
                resultCollector.addResult(currentTask.getResult());
                while (!currentTask.isFinished()) {
                    currentTask.calculate();
                    System.out.println(currentTask);
                    resultCollector.modifyResult(previousResult, currentTask.getResult());
                    previousResult = currentTask.getResult();
                }
                currentTask = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}