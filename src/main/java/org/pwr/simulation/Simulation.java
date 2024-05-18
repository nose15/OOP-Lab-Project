package org.pwr.simulation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private final SimManagerData simData;
    private final ArrayList<String> simulationMap;


    //TODO: Node and children
    //TODO: Generate graph
    //TODO: Agent fundaments
    public Simulation(SimManagerData simManagerData) {
        this.simData = simManagerData;
        this.simulationMap = new ArrayList<>(20);
        System.out.println(simManagerData + " simulation smd : sd " + this.simData);
    }

    public ArrayList<String> getMap() {
        return this.simulationMap;
    }

    public void run() {
        while (true) {
            // Just an example, may be deleted

            try {
                simulationMap.clear();


                for (int i = 0; i < 20; i++) {
                    simulationMap.add(String.valueOf(Math.random() * 10));
                }
                TimeUnit.SECONDS.sleep(5);


            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }

            // ---
        }
    }
}
