package com.company;

import java.util.PriorityQueue;
import java.util.Queue;

public class MessageQueue {

    private Queue<Message> priorityQueue;

    public MessageQueue(){
        priorityQueue = new PriorityQueue<>();
    }

    public synchronized void queueMessage(Message message) {
        priorityQueue.offer(message);
    }

    public synchronized Request delRequest(){
        Message message = priorityQueue.peek();
        if(message instanceof Request){
            return (Request) priorityQueue.poll();
        }
        else return null;
    }

    public synchronized Response delResponse(Requestor requestor){
        if(priorityQueue.peek() instanceof Response && priorityQueue.peek().getRequestor() == requestor){
            return (Response) priorityQueue.poll();
        }
        else return null;
    }

}
