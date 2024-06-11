package org.pwr.simulation.graph;

import org.pwr.simulation.agents.Agent;
import org.pwr.simulation.agents.Hacker;
import org.pwr.simulation.agents.ITSpec;

import java.util.ArrayList;
import java.util.Collection;
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
    protected int numOfIt;
    protected int numOfHackers;

    public Node()
    {
        this.numOfIt = 0;
        this.numOfHackers = 0;
        connectedNodes = new ArrayList<>();
        this.impactCoeficient = 2;
        this.balancingPace = 0.01f;
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
        if (currentState > 1) currentState = 1;
        if (currentState < -1) currentState = -1;

//        setImpactCoeficient();
//        communicate();
//        approachBalance();
    }

    public void hack(float skill) {
        System.out.println("Node " + this + " being hacked by " + skill);
        currentState -= 1 * skill;
    }

    public void heal(float skill) {
        System.out.println("Node " + this + " being healed by " + skill);
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
    public abstract ArrayList<Node> getSwitches();
    public abstract ArrayList<Node> getComputers();

    public Node getParents() {
        return parent;
    }

    public abstract void communicate();
    public abstract void setParent(Node parent);

    public abstract void addSwitch(Node vertex);

    public abstract void addComputer(Node vertex);

    public abstract ArrayList<Node> revealChildren();

    public abstract ArrayList<Node> revealSwitches();

    public abstract Node revealParents();

    public int getNumOfHackers() {
        return this.numOfHackers;
    }

    public int getNumOfIT() {
        return this.numOfIt;
    }

    public void subtract(Agent agent) {
        if (agent.getClass() == Hacker.class) this.numOfHackers -= 1;
        if (agent.getClass() == ITSpec.class) this.numOfIt -= 1;
    }

    public void add(Agent agent) {
        if (agent.getClass() == Hacker.class) this.numOfHackers += 1;
        else if (agent.getClass() == ITSpec.class) this.numOfIt += 1;
    }
}
