package org.pwr.simulation;

import org.pwr.simulation.graph.GraphGenerator;
import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final SimManagerData simData;
    private Node rootNode;
    private final GraphGenerator graphGenerator;


    //TODO: Node and children
    //TODO: Generate graph
    //TODO: Agent fundaments

    public Simulation(SimManagerData simManagerData) {
        this.simData = simManagerData;
        this.rootNode = null;
        this.graphGenerator = new GraphGenerator();
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public void run() {
        while (true) {
            // Just an example, may be deleted
            this.rootNode = graphGenerator.getHead();
            // ---
        }
    }
}
