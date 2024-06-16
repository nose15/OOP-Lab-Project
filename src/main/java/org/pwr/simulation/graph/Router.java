package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Router extends Node {
    private final ArrayList<Node> switches;

    public Router() {
        super();
        this.switches = new ArrayList<>();
        this.id = 0;
    }

    @Override
    public void communicate() {}

    @Override
    public Node getParents() {return null;}

    public void addSwitch(Node s) {
        switches.add(s);
    }

    public void addComputer(Node c) {}

    public ArrayList<Node> revealSwitches() {
        if (this.currentState < -0.9) {
            return this.switches;
        }
        return null;
    }

    public ArrayList<Node> getSwitches() {
        return switches;
    }

}
