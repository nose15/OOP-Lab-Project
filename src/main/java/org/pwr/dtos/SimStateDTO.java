package org.pwr.dtos;

public class SimStateDTO {
    public SimGraphDTO simGraphDTO;
    public boolean isRunning;

    public SimStateDTO(SimGraphDTO simGraphDTO, boolean isRunning) {
        this.simGraphDTO = simGraphDTO;
        this.isRunning = isRunning;
    }
}
