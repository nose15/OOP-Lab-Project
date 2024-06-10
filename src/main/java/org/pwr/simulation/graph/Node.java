package org.pwr.simulation.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    protected float currentState;
    protected final float impactCoeficient;
    protected final float balancingPace;
    protected int balancing;
    protected int id;
    List<Node> connectedNodes;
    Node parent;
    protected float stateRange;
    protected float currentImpact;
    protected float hasAgent;

    public Node()
    {
        connectedNodes = new ArrayList<>();
        this.impactCoeficient = 2;
        this.balancingPace = 0.1f;
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
        setImpactCoeficient();
        communicate();
        approachBalance();
    }

    public void hack(float skill) {
        currentState -= 1 * skill;
    }

    public void heal(float skill) {
        currentState += 1 * skill;
    }

    private void approachBalance() {
        currentState = -Math.signum(currentState) * balancingPace * balancing;
    }

    private void setImpactCoeficient() {
        this.balancing = 0;
        if (currentState > 0.75 * stateRange) {
            currentImpact = impactCoeficient;
        }
        else if (currentState <= 0.75 * stateRange && 0.5 * stateRange <= currentState) {
            currentImpact = 0.5f * impactCoeficient;
        }
        else if (currentState < 0.5 * stateRange && -0.5 * stateRange < currentState) {
            this.currentImpact = 0;
            this.balancing = 1;
        }
        else if (currentState >= -0.75 * stateRange && -0.5 * stateRange >= currentState) {
            currentImpact = -0.5f * impactCoeficient;
        }
        else if (currentState < -0.75) {
            currentImpact = -impactCoeficient;
        }
    }

    public int getId()
    {
        return id;
    }


    public abstract void setState();
    public abstract void communicate();
}
