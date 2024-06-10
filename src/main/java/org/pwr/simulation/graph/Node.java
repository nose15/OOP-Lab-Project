package org.pwr.simulation.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    List<Node> connectedNodes;
    Node parent;
    protected float stateRange;
    protected float currentState;
    protected final float impactCoeficient;
    protected float currentImpact;
    protected float hasAgent;

    public Node()
    {
        connectedNodes = new ArrayList<>();
        this.impactCoeficient = 2;
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

    public void act() {
        if (currentState > 0.75 * stateRange) {
            currentImpact = impactCoeficient;
        }
        else if (currentState <= 0.75 * stateRange && 0.5 * stateRange <= currentState) {
            currentImpact = 0.5f * impactCoeficient;
        }
        else if (currentState >= -0.75 * stateRange && -0.5 * stateRange >= currentState) {
            currentImpact = -0.5f * impactCoeficient;
        }
        else {
            currentImpact = -impactCoeficient;
        }

        communicate();
    }

    public void hack(float skill) {
        currentState -= 1 * skill;
    }

    public void heal(float skill) {
        currentState += 1 * skill;
    }

    public abstract void setState();
    public abstract void communicate();

}
