package com.company.assignment01;

public class Truck implements IAggregable< Truck , Integer>, IDeeplyCloneable<Truck>{

    private int netto;
    private String name;

    public Truck() {
    }

    public Truck(int netto , String name) {

        this.netto = netto;
        this.name = name;
    }

    public int getNetto() {

        return this.netto;


    }
    public String getName(){
        return  this.name;
    }

    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return getNetto();
        }
        return getNetto() + intermediateResult;
    }

    public Truck deepClone() {
        return new Truck(getNetto(),getName());
    }
}

