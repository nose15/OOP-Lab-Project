package org.pwr.dtos;

import java.util.ArrayList;

public class SimulationMapDTO {
    private final ArrayList<String> simMap;

    public SimulationMapDTO(ArrayList<String> simMap) {
        this.simMap = simMap;
    }

    public ArrayList<String> getSimMap() {
        return simMap;
    }
}
