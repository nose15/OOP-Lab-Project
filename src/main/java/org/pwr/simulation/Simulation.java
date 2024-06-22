package org.pwr.simulation;

import org.pwr.simulation.agents.Agent;
import org.pwr.simulation.agents.Hacker;
import org.pwr.simulation.agents.ITSpec;
import org.pwr.simulation.graph.Graph;
import org.pwr.simulation.graph.Node;

import java.util.*;


public class Simulation {
    private SimManagerData simData;
    private final long clockStep;
    private final ArrayList<Agent> agents;
    private final Graph graph;

    public Simulation(SimManagerData simManagerData) {
        this.simData = simManagerData;
        this.clockStep = this.simData.clockStep;
        this.graph = new Graph();
        this.agents = new ArrayList<>();
        ITSpec.callsForHelp = new LinkedList<>();
        Hacker.knownNodes = new HashSet<>();
    }
    public Graph getSimGraph() {
        return graph;
    }

    public void run() {
        System.out.println("Sim running");

        this.simData.isRunning = true;
        this.initSim();

        while (true) {
            long timestampStart = System.currentTimeMillis();
            this.step();
            long timestampEnd = System.currentTimeMillis();

            try {
                Thread.sleep(clockStep - timestampEnd + timestampStart);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initSim() {
        initAgents();
    }

    private void initAgents() {
        Random random = new Random();

        Node router = graph.findVertex(0);
        for (int i = 0; i < simData.numberOfITExperts; i++) {
            ITSpec itSpec = new ITSpec((float) random.nextGaussian(simData.avgItSkills * 0.001, 0.001f) );
            itSpec.setLocation(router);
            router.addAgent(itSpec);
            agents.add(itSpec);
        }


        for (int i = 0; i < simData.numberOfHackers; i++) {
            Hacker hacker = new Hacker((float) random.nextGaussian(simData.avgHackerSkills * 0.001, 0.001f));
            Node location = graph.findVertex(-random.nextInt(simData.numberOfComputers));
            hacker.setLocation(location);
            location.addAgent(hacker);
            agents.add(hacker);
        }
    }
    public void render() {
        graph.graphGeneratorSimple(simData.numberOfSwitches, simData.numberOfComputers);
    }

    public void step() {
        for (Agent agent : agents) {
            agent.act();
        }

        for (Node node : graph.getMap().keySet()) {
            node.act();

            for (Node n : graph.getMap().get(node)) {
                n.act();
            }
        }
    }
}
