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

    public SimStateUpdater(SimGraphDTO simGraphDTO, BlockingQueue<SimStateDTO> simStateDTOs) {
        this.simGraphDTO = simGraphDTO;
        this.simStateDTOs = simStateDTOs;
        this.scheduler = new ScheduledThreadPoolExecutor(1);
    }

    public void sendRenderUpdate() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        sendState(true);
    }

    public void runSimulationUpdates() {
        scheduler.scheduleAtFixedRate(() -> {
            sendState(false);
        }, 100, 1, TimeUnit.MILLISECONDS);
    }

    private void sendState(boolean rerender) {
        SimStateDTO simState = packState(rerender);
        try {
            this.simStateDTOs.put(simState);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private SimStateDTO packState(boolean rerender) {
        return new SimStateDTO(this.simGraphDTO, rerender);
    }
}
