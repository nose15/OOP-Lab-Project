package org.pwr.simulation;

import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;

import java.util.concurrent.*;

public class SimulationManager {
    private final SimulationThread simulationThread;
    private final BlockingQueue<String> threadControlQueue;
    private final SimInputHandler simInputHandler;
    private final SimManagerData simManagerData;

    public SimulationManager(BlockingQueue<SimStateDTO> simToGuiQueue, BlockingQueue<ConfigDTO> guiToSimQueue) {
        this.threadControlQueue = new LinkedBlockingQueue<>();
        this.simManagerData = new SimManagerData();
        this.simInputHandler = new SimInputHandler(this, guiToSimQueue);
        this.simulationThread = new SimulationThread(simManagerData, this.threadControlQueue);
        SimStateUpdater simStateUpdater = new SimStateUpdater(this.simulationThread.getGraph(), simToGuiQueue);

        simStateUpdater.run();
        this.simInputHandler.start();
        this.runSimulationThread();
    }

    public void startSimulation() {
        try {
            this.threadControlQueue.put("start");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void pauseSimulation() {
        simulationThread.pause();
    }

    public void stopSimulation() {
        simInputHandler.stop();
        simulationThread.interrupt();
    }

    private void runSimulationThread()  {
        simulationThread.start();
    }

    public SimManagerData getSimManagerData() {
        return simManagerData;
    }
}
