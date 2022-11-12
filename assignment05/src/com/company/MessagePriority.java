package com.company;

import java.util.Random;

public enum MessagePriority {

    High("High priority"),
    Default("Default priority"),
    Low("Low priority");
    private final String localName;

    public static MessagePriority randomPriority() {
        Random random = new Random();
        MessagePriority[] values = MessagePriority.values();
        int index = random.nextInt(values.length);
        return values[index];
    }

    MessagePriority(String localName){
        this.localName = localName;
    }

}
