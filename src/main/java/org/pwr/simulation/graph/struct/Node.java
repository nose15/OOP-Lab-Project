package org.pwr.simulation.graph.struct;

public abstract class Node {

    Node[] connectedNodes;
    private float CurrentState;
    private float impactCoeficient;

    abstract void SetState();
    abstract void Communicate();
}
