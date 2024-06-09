package org.pwr.simulation.graph;

import java.util.ArrayList;
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
    abstract void setState();
    abstract void communicate();
}
