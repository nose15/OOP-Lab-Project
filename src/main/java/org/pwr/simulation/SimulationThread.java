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
        while (true) {
            if (!managerToThreadQueue.isEmpty()) {
                try {
                    String command = managerToThreadQueue.remove();
                    if (command.equals("start")) {
                        break;
                    } else if (command.equals("regenerate")) {
                        this.simReGenerate();
                    }
                } catch (NoSuchElementException e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                Thread.sleep(config.clockStep / 10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.simulation.run();
    }

    private void simReGenerate() {
        this.simulation.generate();
    }

    public void pause() {
        System.out.println("Sim Thread Paused");
    }
}
