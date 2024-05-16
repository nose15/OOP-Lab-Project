package org.pwr.simulation.graph.struct;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    List<Node> connectedNodes;
    Node parent;
    private float CurrentState;
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
    abstract void SetState();
    abstract void Communicate();
}
