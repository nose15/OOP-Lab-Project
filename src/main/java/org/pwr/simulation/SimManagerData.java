package org.pwr.simulation;

public class SimManagerData {
    public int numberOfNodes;
    public int numberOfHackers;
    public int numberOfITExperts;
    public float avgHackerSkills;
    public float avgItSkills;

    public float resistanceLossPace;
    public boolean malwareSpread;
    public float malwareSpreadPace;

    public int clockStep;

    public SimManagerData() {
        this.numberOfHackers = 0;
        this.malwareSpreadPace = 0;
        this.resistanceLossPace = 0;
        this.avgItSkills = 0;
        this.avgHackerSkills = 0;
        this.numberOfITExperts = 0;
        this.malwareSpread = false;
        this.numberOfNodes = 0;
        this.clockStep = 1000;
    }
}
