package org.pwr.simulation.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    List<Node> connectedNodes;
    Node parent;
    private float currentState;
    private float impactCoeficient;

    public Node()
    {
        connectedNodes = new ArrayList<>();
    }
    public void addConnection(Node node)
    {
        connectedNodes.add(node);
    }
    public List<Node> getConnections()
    {
        return connectedNodes;
    }

    public String toString() {
        return String.valueOf(this.hashCode());
    }

    public float getState() {
        return currentState;
    }

    public abstract void act();
    public abstract void setState();
    public abstract void communicate();
    public abstract void hack(float skill);
    public abstract void heal(float skill);
}
