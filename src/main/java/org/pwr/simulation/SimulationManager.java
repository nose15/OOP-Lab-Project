package org.pwr.simulation;

import org.pwr.dtos.SimStateDTO;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

public class SimulationManager {
    private int numberOfNodes;
    private int numberOfHackers;
    private int numberOfITExperts;
    private float avgHackerSkills;
    private float avgItSkills;

    private float resistanceLossPace;
    private boolean malwareSpread;
    private float malwareSpreadPace;
    private SimulationThread simulationThread;

    public SimulationManager() {}

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public int getNumberOfHackers() {
        return numberOfHackers;
    }

    public int getNumberOfITExperts() {
        return numberOfITExperts;
    }

    public float getAvgHackerSkills() {
        return avgHackerSkills;
    }

    public float getAvgItSkills() {
        return avgItSkills;
    }

    public float getResistanceLossPace() {
        return resistanceLossPace;
    }

    public boolean getMalwareSpread() {
        return malwareSpread;
    }

    public float getMalwareSpreadPace() {
        return malwareSpreadPace;
    }

    public SimulationThread getSimulationThread() {
        return this.simulationThread;
    }

    public SimStateDTO getSimState() {
        SimStateDTO simStateDTO = new SimStateDTO();
        return simStateDTO;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public void setNumberOfHackers(int numberOfHackers) {
        this.numberOfHackers = numberOfHackers;
    }

    public void setNumberOfITExperts(int numberOfITExperts) {
        this.numberOfITExperts = numberOfITExperts;
    }

    public void setAvgHackerSkills(float avgHackerSkills) {
        this.avgHackerSkills = avgHackerSkills;
    }

    public void setAvgItSkills(float avgItSkills) {
        this.avgItSkills = avgItSkills;
    }

    public void setResistanceLossPace(float resistanceLossPace) {
        this.resistanceLossPace = resistanceLossPace;
    }

    public void setMalwareSpread(boolean malwareSpread) {
        this.malwareSpread = malwareSpread;
    }

    public void setMalwareSpreadPace(float malwareSpreadPace) {
        this.malwareSpreadPace = malwareSpreadPace;
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
