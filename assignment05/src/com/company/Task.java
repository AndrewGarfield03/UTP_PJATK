package com.company;

public abstract class Task implements Runnable{

    private static int currId = 0;
    private final int id;
    protected final MessageQueue queue;

    protected Task(MessageQueue queue){
        id = getCurrId();
        this.queue = queue;
    }

    private static int getCurrId(){
        currId++;
        return currId;
    }

    public final int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "name:" + getClass().getName() + ", id:" + getId();
    }

    void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
