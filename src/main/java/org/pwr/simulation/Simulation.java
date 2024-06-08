package org.pwr.simulation;

import org.pwr.simulation.agents.Agent;
import org.pwr.simulation.graph.GraphGenerator;
import org.pwr.simulation.graph.Node;

import java.security.Timestamp;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final SimManagerData simData;
    private Node rootNode;
    private final GraphGenerator graphGenerator;
    private long clockStep;
    private long timestampStart;
    private long timestampEnd;
    private ArrayList<Agent> agents;


    //TODO: Node and children
    //TODO: Generate graph
    //TODO: Agent fundaments

    public Simulation(SimManagerData simManagerData) {
        this.simData = simManagerData;
        this.clockStep = this.simData.clockStep;
        this.rootNode = null;
        this.graphGenerator = new GraphGenerator();
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    public void run() {
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
        for (Agent agent : agents) {
            agent.act();
        }
    }
}
