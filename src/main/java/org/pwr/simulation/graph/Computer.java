package org.pwr.simulation.graph;

public class Computer extends Node {

    private static int counter = 0;
    private Node parent;
    public Computer()
    {
        super();
        id = --counter;
    }

    @Override
    public void act() {

    }

    public void communicate()
    {

    }

    @Override
    public void hack(float skill) {

    }

    @Override
    public void heal(float skill) {

    }

    static int getCounter()
    {
        return counter;
    }
    public Node getParent()
    {
        return parent;
    }
    public void setParent(Node p)
    {
        parent = p;
    }
}
