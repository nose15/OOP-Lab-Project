package org.pwr.simulation;

import org.pwr.dtos.ConfigDTO;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimInputHandler {
    private final SimulationManager simulationManager;
    private final BlockingQueue<ConfigDTO> configDTOS;
    private final ScheduledExecutorService scheduler;
    private final SimManagerData simManagerData;

    public SimInputHandler(SimulationManager simulationManager, BlockingQueue<ConfigDTO> queue) {
        this.configDTOS = queue;
        this.simulationManager = simulationManager;
        this.scheduler = new ScheduledThreadPoolExecutor(1);
        this.simManagerData = simulationManager.getSimManagerData();
    }


    public void handleInput() {
        ConfigDTO configDTO = takeRecentConfigDTO();

        switch (configDTO.command) {
            case "setNumberOfNodes":
                this.simManagerData.numberOfNodes = (Integer.parseInt(configDTO.value));
                break;
            case "setNumberOfITExperts":
                this.simManagerData.numberOfITExperts = (Integer.parseInt(configDTO.value));
                break;
            case "setAvgHackerSkills":
                this.simManagerData.avgHackerSkills = (Integer.parseInt(configDTO.value));
                break;
            case "setAvgItSkills":
                this.simManagerData.avgItSkills = (Integer.parseInt(configDTO.value));
                break;
            case "setResistanceLossPace":
                this.simManagerData.resistanceLossPace = (Integer.parseInt(configDTO.value));
                break;
            case "setMalwareSpreadPace":
                this.simManagerData.malwareSpreadPace = (Integer.parseInt(configDTO.value));
                break;
            case "setMalwareSpread":
                this.simManagerData.malwareSpread = (Boolean.parseBoolean(configDTO.value));
                break;
            case "setNumberOfHackers":
                this.simManagerData.numberOfHackers = (Integer.parseInt(configDTO.value));
                break;
            case "setAllZero":
                this.simManagerData.numberOfNodes = 0;
                this.simManagerData.numberOfITExperts = 0;
                this.simManagerData.numberOfHackers = 0;
                this.simManagerData.avgHackerSkills = 0;
                this.simManagerData.avgItSkills = 0;
                this.simManagerData.resistanceLossPace = 0;
                this.simManagerData.malwareSpread = false;
                this.simManagerData.malwareSpreadPace = 0;
                break;
            case "start":
                this.simulationManager.startSimulation();
                break;
        }
    }

    private ConfigDTO takeRecentConfigDTO() {
        try {
            return this.configDTOS.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void start() {
        this.scheduler.scheduleAtFixedRate(() -> this.handleInput(), 0, 1, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
