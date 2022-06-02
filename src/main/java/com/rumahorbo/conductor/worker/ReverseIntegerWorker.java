package com.rumahorbo.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class ReverseIntegerWorker implements Worker {

    private final String taskDefName;

    public ReverseIntegerWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String integer = (String) task.getInputData().get("integer");
        result.addOutputData("reversedInteger", "Integer " + integer + " reversed into " + reverseInteger(integer));
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

    private String reverseInteger(String integer) {
        int value = Integer.parseInt(integer);
        int result = 0;
        while (value != 0) {
            int temp = value % 10;
            result = result*10 + temp;
            value = value / 10;
        }
        return Integer.toString(result);
    }
}
