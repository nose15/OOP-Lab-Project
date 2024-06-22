package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Computer extends Node {
    private static int counter = 0;
    private Node parent;

    public Computer()
    {
        super();
        id = --counter;
    }

    static int getCounter()
    {
        return counter;
    }
    static void setCounter(int val) {
        counter = val;
    }


    @Override
    public void communicate() {}

    public void setParent(Node p)
    {
        parent = p;
    }

    public Node revealParents() {
        return parent;
    }

    public Node getParents()
    {
        return parent;
    }
}
