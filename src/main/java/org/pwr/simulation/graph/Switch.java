package org.pwr.simulation.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Switch extends Node {

    private static int counter = 0;
    private ArrayList<Node> switches = new ArrayList<>();
    private ArrayList<Node> computers = new ArrayList<>();
    private Node parent;
    public Switch()
    {
        super();
        id = ++counter;
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
        return switches;
    }
    public ArrayList<Node> getComputers()
    {
        return computers;
    }
    public void setParent(Node p)
    {
        parent = p;
    }
    public void addSwitch(Node s)
    {
        switches.add(s);
    }
    public void addComputer(Node c)
    {
        computers.add(c);
    }

    void setState()
    {

    }

}
