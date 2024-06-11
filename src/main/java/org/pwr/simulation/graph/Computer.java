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

    @Override
    public void communicate() {}

    public void setParent(Node p)
    {
        parent = p;
    }
    public void addSwitch(Node s) {}
    public void addComputer(Node c) {}

    @Override
    public ArrayList<Node> revealChildren() {
        return null;
    }

    @Override
    public ArrayList<Node> revealSwitches() {
        return null;
    }

    @Override
    public Node revealParents() {
        return parent;
    }

    @Override
    public ArrayList<Node> getComputers()
    {
        return null;
    }

    @Override
    public ArrayList<Node> getSwitches()
    {
        return null;
    }

    @Override
    public Node getParents()
    {
        return parent;
    }
}
