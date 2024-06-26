package org.pwr.simulation;

public class SimManagerData {
    public int numberOfComputers;
    public int numberOfSwitches;

    public int numberOfHackers;
    public int numberOfITExperts;
    public float avgHackerSkills;
    public float avgItSkills;

    public float resistanceLossPace;
    public boolean malwareSpread;
    public float malwareSpreadPace;

    public int clockStep;

    public SimManagerData() {
        this.numberOfHackers = 5;
        this.malwareSpreadPace = 0;
        this.resistanceLossPace = 0.5f;
        this.avgItSkills = 0.5f;
        this.avgHackerSkills = 0.5f;
        this.numberOfITExperts = 5;
        this.malwareSpread = false;
        this.numberOfComputers = 5;
        this.numberOfSwitches = 2;
        this.clockStep = 1000;
    }
}
