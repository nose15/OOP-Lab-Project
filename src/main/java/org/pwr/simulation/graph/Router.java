package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Router extends Node{
    private final ArrayList<Node> switches = new ArrayList<>();

    public Router()
    {
        super();
        id = 0;
    }

    public void addSwitch(Node s)
    {
        switches.add(s);
    }
    public ArrayList<Node> getSwitches()
    {
        return switches;
    }
    public void addComputer(Node c)
    {
        return;
    }

    @Override
    public ArrayList<Node> revealChildren() {
        return null;
    }

    @Override
    public ArrayList<Node> revealSwitches() {
        return null;
    }

    @Override
    public Node revealParents() {
        return null;
    }

    public void setParent(Node p)
    {
        return;
    }
    void setState()
    {

    }
    public void communicate()
    {

    }
}
