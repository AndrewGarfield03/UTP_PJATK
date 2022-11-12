package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
	    MessageQueue queue = new MessageQueue();
        List<Service> services = services(queue, 5);
        List<Requestor> requestors = requestors(queue, 5);
        start(services);
        start(requestors);
    }

    private static List<Service> services(MessageQueue queue, int serviceCount){
        return tasks(queue, serviceCount, Service::new);
    }

    private static List<Requestor> requestors(MessageQueue queue, int requestorCount){
        return tasks(queue, requestorCount, Requestor::new);
    }

    private static <TTask extends Task> List<TTask> tasks(MessageQueue queue, int taskCount, Function<MessageQueue, TTask> create){
        List<TTask> tasks = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            TTask task = create.apply(queue);
            tasks.add(task);
        }
        return tasks;
    }

    private static <TTask extends Task> void start(List<TTask> tasks){
        tasks.stream().forEach(Main::start);
    }

    private static <TTask extends Task> void start(TTask task){
        new Thread(task).start();
    }
}
