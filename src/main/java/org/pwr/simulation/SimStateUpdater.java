package org.pwr.simulation;

import org.pwr.dtos.SimStateDTO;
import org.pwr.dtos.SimulationMapDTO;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimStateUpdater {
    private final SimulationMapDTO simulationMapDTO;
    private final BlockingQueue<SimStateDTO> simStateDTOs;
    private final ScheduledExecutorService scheduler;

    public SimStateUpdater(SimulationMapDTO simulationMapDTO, BlockingQueue<SimStateDTO> simStateDTOs) {
        this.simulationMapDTO = simulationMapDTO;
        this.simStateDTOs = simStateDTOs;
        this.scheduler = new ScheduledThreadPoolExecutor(1);
    }

    public void run() {
        scheduler.scheduleAtFixedRate(this::sendState, 0, 1, TimeUnit.MILLISECONDS);
    }

    private void sendState() {
        SimStateDTO simState = packState();
        try {
            this.simStateDTOs.put(simState);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private SimStateDTO packState() {
        return new SimStateDTO(this.simulationMapDTO);
    }
}
