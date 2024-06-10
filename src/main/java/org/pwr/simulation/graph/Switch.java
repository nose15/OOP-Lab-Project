package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Switch extends Node {
    private final float childrenRevealThreshold;

    public Switch()
    {
        super();
        this.stateRange = 100;
        this.childrenRevealThreshold = 0.75f * this.stateRange;
    }

    public void communicate()
    {

    }
    public void setState()
    {

    }

    public Switch setParent(Node parent)
    {
        this.parent = parent;
        return this;
    }

    public ArrayList<Node> getChildren() {
        if (this.currentState < this.childrenRevealThreshold) {
            return new ArrayList<>();
        }

        return
    }

}
