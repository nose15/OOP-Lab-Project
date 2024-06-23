package org.pwr.simulation;

import com.sun.source.doctree.ThrowsTree;
import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;

import java.util.Queue;
import java.util.concurrent.*;

public class SimulationManager {
    private final SimulationThread simulationThread;
    private final Queue<String> threadControlQueue;
    private final SimInputHandler simInputHandler;
    private final SimManagerData simManagerData;
    private final SimStateUpdater simStateUpdater;

    public SimulationManager(BlockingQueue<SimStateDTO> simToGuiQueue, BlockingQueue<ConfigDTO> guiToSimQueue) {
        this.threadControlQueue = new LinkedBlockingQueue<>();
        this.simManagerData = new SimManagerData();
        this.simInputHandler = new SimInputHandler(this, guiToSimQueue);
        this.simulationThread = new SimulationThread(simManagerData, this.threadControlQueue);
        this.simStateUpdater = new SimStateUpdater(this.simulationThread.getGraph(), simToGuiQueue);

        this.simInputHandler.start();
        this.runSimulationThread();
    }

    public void startSimulation() {
        this.threadControlQueue.add("start");
        this.simStateUpdater.runSimulationUpdates();
    }

    public void pauseSimulation() {
        simulationThread.pause();
    }

    public void stopSimulation() {
        simInputHandler.stop();
        simulationThread.interrupt();
    }

    public void regenerate() {
        this.threadControlQueue.add("regenerate");
        this.simStateUpdater.sendRenderUpdate();
    }

    private void runSimulationThread()  {
        simulationThread.start();
    }

    public SimManagerData getSimManagerData() {
        return simManagerData;
    }
}
