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
    public void setParent(Node p) {}

    @Override
    public void addSwitch(Node s) {
        switches.add(s);
    }

    @Override
    public void addComputer(Node c) {}

    @Override
    public ArrayList<Node> revealChildren() {
        return null;
    }

    @Override
    public ArrayList<Node> revealSwitches() {
        if (this.currentState < -0.9) {
            return this.switches;
        }

        return null;
    }

    @Override
    public Node revealParents() {
        return null;
    }

    @Override
    public ArrayList<Node> getSwitches() {
        return switches;
    }

    @Override
    public ArrayList<Node> getComputers() {
        return null;
    }

    @Override
    public Node getParents() {
        return null;
    }
}
