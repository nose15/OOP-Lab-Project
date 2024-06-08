package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

public abstract class Agent {
    private float skill;
    private Node location;

    public Node getLocation() {
        return this.location;
    }

    public float getSkill() {
        return this.skill;
    }

    public abstract void act();
    protected abstract void move();
}
