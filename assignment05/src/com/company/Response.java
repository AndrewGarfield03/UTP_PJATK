package com.company;

public class Response extends Message{

    private int responseRes;

    public Response(Request request){
        super(request.getRequestor(), request.getPriority());
        responseRes = request.getNum1() + request.getNum2();
    }

    public int getResponseRes() {
        return responseRes;
    }

    @Override
    public String toString() {
        return "Response: " + "(" + responseRes + ")";
    }
}
