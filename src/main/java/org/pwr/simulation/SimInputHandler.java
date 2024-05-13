package org.pwr.simulation;

import org.pwr.app.InputHandler;

public class SimInputHandler implements InputHandler {
    private final SimulationManager simulationManager;

    public SimInputHandler(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    @Override
    public void handleInput(String name, int value) {
        switch (name) {
            case "setNumberOfNodes":
                this.simulationManager.setNumberOfNodes(value);
                break;
            case "setNumberOfITExperts":
                this.simulationManager.setNumberOfITExperts(value);
                break;
            case "setAvgHackerSkills":
                this.simulationManager.setAvgHackerSkills(value);
                break;
            case "setAvgItSkills":
                this.simulationManager.setAvgItSkills(value);
                break;
            case "setResistanceLossPace":
                this.simulationManager.setResistanceLossPace(value);
                break;
            case "setMalwareSpreadPace":
                this.simulationManager.setMalwareSpreadPace(value);
                break;
        }
    }

    @Override
    public void handleInput(String name, float value) {

    }

    @Override
    public void handleInput(String name, boolean value) {
        switch (name) {
            case "setMalwareSpread":
                this.simulationManager.setMalwareSpread(value);
        }
    }

}
