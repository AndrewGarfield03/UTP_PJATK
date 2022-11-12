package com.company;

import java.util.Random;

public final class Request extends Message{

    private final int num1;
    private final int num2;


    public Request(Requestor requestor){
        super(requestor, MessagePriority.randomPriority());
        num1 = (int)Math.floor(Math.random()*100);
        num2 = (int)Math.floor(Math.random()*100);
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    @Override
    public String toString(){
        return super.toString() + "(" + num1 + ", " + num2 + ")";
    }
}
