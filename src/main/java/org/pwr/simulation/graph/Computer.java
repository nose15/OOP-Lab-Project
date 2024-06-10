package org.pwr.simulation.graph;

public class Computer extends Node {

    private static int counter = 0;
    private Node parent;
    public Computer()
    {
        super();
        id = --counter;
    }
    void communicate()
    {

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
    void addSwitch(Node s)
    {
        return;
    }
    void addComputer(Node c)
    {
        return;
    }
    void setState()
    {

    }

}
