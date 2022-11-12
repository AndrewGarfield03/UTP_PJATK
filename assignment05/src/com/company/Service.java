package com.company;

public final class Service extends Task {

    public Service(MessageQueue queue) {
        super(queue);
    }

    @Override
    public void run() {
        while (true){
            Request request = queue.delRequest();
            if(request != null){
                serviceRequest(request);
            }
            sleep();
        }
    }

    private void serviceRequest(Request request){
        System.out.println("request: " + request + " is processing");
        Response response = createResponse(request);
        queue.queueMessage(response);
    }

    private Response createResponse(Request request){
        return new Response(request);
    }
}
