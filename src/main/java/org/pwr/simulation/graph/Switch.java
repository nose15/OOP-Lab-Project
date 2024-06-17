package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Switch extends Node {
    private final float childrenRevealThreshold;
    private final float peersRevealThreshold;
    private final float parentRevealThreshold;

    private static int counter = 0;
    private final ArrayList<Node> switches = new ArrayList<>();
    private final ArrayList<Node> computers = new ArrayList<>();
    private Node parent;
    public Switch()
    {
        super();
        this.childrenRevealThreshold = 0.5f;
        this.peersRevealThreshold = 0.75f;
        this.parentRevealThreshold = 0.9f;

        id = ++counter;
    }

    static int getCounter()
    {
        return counter;
    }

    @Override
    public void communicate() {}

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public void addSwitch(Node s)
    {
        switches.add(s);
    }

    public void addComputer(Node c)
    {
        computers.add(c);
    }

    public ArrayList<Node> revealChildren() {
        if (this.currentState < this.childrenRevealThreshold) {
            return null;
        }

        return computers;
    }

    public ArrayList<Node> revealSwitches() {
        if (this.currentState < this.peersRevealThreshold) {
            return null;
        }

        return switches;
    }

    public Node revealParents() {
        if (this.currentState < this.parentRevealThreshold) {
            return null;
        }

        return parent;
    }

    public ArrayList<Node> getComputers() {
        return computers;
    }

    public ArrayList<Node> getSwitches() {
        return switches;
    }

    public Node getParents() {
        return parent;
    }
}
