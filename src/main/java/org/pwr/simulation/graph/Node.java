package org.pwr.simulation.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {

    protected float currentState;
    protected float impactCoeficient;
    protected int id;

    public Node()
    {
    }

    public String toString() {
        return String.valueOf(this.hashCode());
    }
    public int getId()
    {
        return id;
    }
    abstract void addSwitch(Node s);
    abstract void addComputer(Node c);
    abstract void setParent(Node p);
    abstract void setState();
    abstract void communicate();

    public abstract ArrayList<Node> getSwitches();
    public abstract ArrayList<Node> getComputers();
}
