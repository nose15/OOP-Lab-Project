package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

import java.util.*;

public abstract class Agent {
    protected AgentState agentState;
    protected static Set<Node> knownNodes;
    protected float skill;
    protected Node location;
    protected boolean done;
    protected int turnsCount;
    protected float prevTargetState;
    protected final Queue<Boolean> initiative = new LinkedList<>();

    public Node getLocation() {
        return this.location;
    }

    public float getSkill() {
        return this.skill;
    }

    protected void move(Node target) {
        this.location = target;
        resetProgress();
    }

    protected void resetProgress() {
        this.turnsCount = 0;
        this.prevTargetState = 0;
        this.initiative.clear();
    }

    public abstract void act();
}
