package com.company.assignment01;


import java.util.ArrayList;
import java.util.List;

public class MyContainer<TElement extends
        IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult>
        implements IContainer<TElement, TAggregateResult> {

    private ArrayList<TElement> elements = new ArrayList<>();

    public MyContainer() {

    }

    public MyContainer(ArrayList<TElement> elements) {
        this.elements = elements;
    }

    public void add(TElement elementT) {
        elements.add(elementT);
    }


    public List<TElement> elements() {
        return elements;
    }


    public TAggregateResult aggregateAllElements() {
        TAggregateResult sum = null;
        for (TElement elem : elements) {
            sum = elem.aggregate(sum);
        }
        return sum;
    }


    public TElement cloneElementAtIndex(int index) throws ArrayIndexOutOfBoundsException {
        if (index > elements.size())
            throw new ArrayIndexOutOfBoundsException();
        return elements.get(index).deepClone();

    }
}

