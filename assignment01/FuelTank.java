package com.company.assignment01;

public class FuelTank implements IAggregable< FuelTank , Integer>, IDeeplyCloneable<FuelTank>{

    private int volume;

    public FuelTank() {
    }

    public FuelTank(int volume) {

        this.volume = volume;
    }

    public int getVolume() {

        return this.volume;

    }

    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return getVolume();
        }
        return getVolume() + intermediateResult;
    }

    public FuelTank deepClone() {
        return new FuelTank(getVolume());
    }
}

