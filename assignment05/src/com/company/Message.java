package com.company;

import java.util.Comparator;
import java.util.Date;

public class Message implements Comparable<Message>{

    private static int currId = 0;
    private int id;
    private Requestor requestor;
    private MessagePriority priority;
    private static final Comparator<Message> COMPARATOR;

    static {
        COMPARATOR = Comparator
                .comparing(Message::getPriority)
                .thenComparing(Message::getId);
    }

    private synchronized static int getCurrId(){
        currId++;
        return currId;
    }

    protected Message(Requestor requestor, MessagePriority priority) {
        id = getCurrId();
        this.requestor = requestor;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public Requestor getRequestor() {
        return requestor;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    @Override
    public final int compareTo(Message otherMessage){
        return COMPARATOR.compare(this, otherMessage);
    }

    @Override
    public String toString(){
        return getClass().getName() + "(" + getId() + ")";
    }
}
