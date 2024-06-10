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
    public ArrayList<Node> getSwitches()
    {
        return null;
    }
    public ArrayList<Node> getComputers()
    {
        return null;
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
