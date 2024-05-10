package org.pwr.simulation;

import org.pwr.dtos.SimStateDTO;

public class SimulationManager {
    private static SimulationManager instance;
    private short numberOfNodes;
    private short numberOfHackers;
    private short numberOfITExperts;
    private float avgHackerSkills;
    private float avgItSkills;
    private SimulationThread simulationThread;

    public SimulationManager() {}

    public short getNumberOfNodes() {
        return numberOfNodes;
    }

    public short getNumberOfHackers() {
        return numberOfHackers;
    }

    public short getNumberOfITExperts() {
        return numberOfITExperts;
    }

    public float getAvgHackerSkills() {
        return avgHackerSkills;
    }

    public float getAvgItSkills() {
        return avgItSkills;
    }

    public SimulationThread getSimulationThread() {
        return this.simulationThread;
    }

    public SimStateDTO getSimState() {
        SimStateDTO simStateDTO = new SimStateDTO();
        return simStateDTO;
    }

    public void setNumberOfNodes(short numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public void setNumberOfHackers(short numberOfHackers) {
        this.numberOfHackers = numberOfHackers;
    }

    public void setNumberOfITExperts(short numberOfITExperts) {
        this.numberOfITExperts = numberOfITExperts;
    }

    public void setAvgHackerSkills(float avgHackerSkills) {
        this.avgHackerSkills = avgHackerSkills;
    }

    public void setAvgItSkills(float avgItSkills) {
        this.avgItSkills = avgItSkills;
    }

    public void setSimulationThread(SimulationThread simulationThread) {
        this.simulationThread = simulationThread;
    }

    public void startSimulation() {
        this.runSimulationThread();
    }

    public void pauseSimulation() {
        simulationThread.pause();
    }

    public void resumeSimulation() {
        simulationThread.resume();
    }

    public void stopSimulation() {
        simulationThread.stop();
    }

    private void runSimulationThread() {
        simulationThread.run();
    }
}
