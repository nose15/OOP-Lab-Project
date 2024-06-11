package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

import java.util.*;

public abstract class Agent {
    protected float skill;
    protected Node location;
    protected int turnsCount;
    protected float prevTargetState;
    protected final Queue<Boolean> initiative = new LinkedList<>();

    protected void move(Node target) {
        this.location.removeAgent(this);
        this.location = target;
        target.addAgent(this);
        resetProgress();
    }

    protected void resetProgress() {
        this.turnsCount = 0;
        this.prevTargetState = 0;
        this.initiative.clear();
        initiative.add(true);
    }

    public abstract void act();

    public void setLocation(Node location) {
        this.location = location;
    }
}
