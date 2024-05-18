package org.pwr.simulation;
import org.pwr.dtos.SimGraphDTO;

import java.util.concurrent.BlockingQueue;

public class SimulationThread extends Thread {
    private final BlockingQueue<String> managerToThreadQueue;
    private final Simulation simulation;

    public SimulationThread(SimManagerData simManagerData, BlockingQueue<String> managerToThreadQueue) {
        this.managerToThreadQueue = managerToThreadQueue;
        this.simulation = new Simulation(simManagerData);
    }

    public SimGraphDTO getGraph() {
        return new SimGraphDTO(this.simulation.getRootNode());
    }

    @Override
    public void run() {
        System.out.println("Sim Thread running");
        while (true) {
            try {
                if(managerToThreadQueue.take().equals("start")) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        simulation.run();
    }

    public void pause() {
        System.out.println("Sim Thread Paused");
    }
}
