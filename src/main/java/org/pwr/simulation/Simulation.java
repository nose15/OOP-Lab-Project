package org.pwr.simulation;

import org.pwr.simulation.agents.Agent;
import org.pwr.simulation.graph.Graph;
import org.pwr.simulation.graph.GraphGenerator;
import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;


public class Simulation {
    private final SimManagerData simData;
    private Node rootNode;
    private final GraphGenerator graphGenerator;
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
        this.graphGenerator = new GraphGenerator();
        this.graph = new Graph();
    }

    public Node getRootNode() {
        return this.rootNode;
    }
    public Graph getSimMap() {
        return graph;
    }

    public void run() {
        graph.graphGeneratorSimple(10, 40);
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
