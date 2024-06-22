package org.pwr.simulation;
import org.pwr.dtos.SimGraphDTO;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class SimulationThread extends Thread {
    private final Queue<String> managerToThreadQueue;
    private final Simulation simulation;
    private final SimManagerData config;

    public SimulationThread(SimManagerData simManagerData, Queue<String> managerToThreadQueue) {
        this.managerToThreadQueue = managerToThreadQueue;
        this.simulation = new Simulation(simManagerData);
        this.config = simManagerData;
    }

    public SimGraphDTO getGraph() {
        return new SimGraphDTO(this.simulation.getSimGraph());
    }

    @Override
    public void run() {
        System.out.println("Sim Thread running");
        while (true) {
            try {
                this.simPrerender();
                System.out.println("Running");
                if(managerToThreadQueue.remove().equals("start")) {
                    System.out.println("Start");
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }

            try {
                Thread.sleep(config.clockStep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.simulation.run();
    }

    private void simPrerender() {
        this.simulation.render();
    }

    public void pause() {
        System.out.println("Sim Thread Paused");
    }
}
