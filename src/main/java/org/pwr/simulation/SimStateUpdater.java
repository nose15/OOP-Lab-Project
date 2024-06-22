package org.pwr.simulation;

import org.pwr.dtos.SimStateDTO;
import org.pwr.dtos.SimGraphDTO;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimStateUpdater {
    private final SimGraphDTO simGraphDTO;
    private final BlockingQueue<SimStateDTO> simStateDTOs;
    private final ScheduledExecutorService scheduler;
    private boolean isRunning;

    public SimStateUpdater(SimGraphDTO simGraphDTO, boolean isRunning, BlockingQueue<SimStateDTO> simStateDTOs) {
        this.simGraphDTO = simGraphDTO;
        this.simStateDTOs = simStateDTOs;
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.isRunning = isRunning;
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
        return new SimStateDTO(this.simGraphDTO, this.isRunning);
    }
}
