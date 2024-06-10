package org.pwr.simulation.graph;

import java.util.ArrayList;

public class Router extends Node{

    private ArrayList<Node> switches = new ArrayList<>();

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
    public void setParent(Node p)
    {
        return;
    }
    void setState()
    {

    }
    void communicate()
    {

    }
}
