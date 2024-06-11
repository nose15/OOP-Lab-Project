package org.pwr.simulation;

import org.pwr.simulation.agents.Agent;
import org.pwr.simulation.graph.Graph;
import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;


public class Simulation {
    private final SimManagerData simData;
    private Node rootNode;
    private long clockStep;
    private long timestampStart;
    private long timestampEnd;
    private ArrayList<Agent> agents;
    private Graph graph;

    //TODO: Node and children
    //TODO: Generate graph
    //TODO: Agent fundaments

    public Simulation(SimManagerData simManagerData) {
        this.simData = simManagerData;
        this.clockStep = this.simData.clockStep;
        this.rootNode = null;
        this.graph = new Graph();
    }

    public Node getRootNode() {
        return this.rootNode;
    }
    public Graph getSimMap() {
        return graph;
    }

    public void run() {
        //graph.graphGeneratorSimple(10, 30);
        graph.graphGeneratorAdvance(20, 35, 0.8f, 0.6f);
        while (true) {
            this.timestampStart = System.currentTimeMillis();
            this.step();
            this.timestampEnd = System.currentTimeMillis();

            try {
                Thread.sleep(clockStep - timestampEnd + timestampStart);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void step() {
        graph.printHashMap();
    }
}
