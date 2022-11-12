package com.company;

public class Requestor extends Task {

    private MessageQueue responseQueue;

    public Requestor(MessageQueue messageQueue){
        super(messageQueue);
        responseQueue = new MessageQueue();
    }

    @Override
    public void run() {
        System.out.println("Running " + this);
        while (true){
            addRequest();
            boolean responseFinished = false;
            while (!responseFinished){
                Response response = delResponse();
                if(response != null){
                    System.out.println("Response: "+response);
                    responseFinished = true;
                }
                sleep();
            }
        }
    }

    private void addRequest(){
        queue.queueMessage(new Request(this));
    }

    private Response delResponse() {
        return queue.delResponse(this);
    }

}
