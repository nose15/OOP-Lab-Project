package org.pwr.dtos;

public class SimStateDTO {
    public SimGraphDTO simGraphDTO;
    public boolean rerender;

    public SimStateDTO(SimGraphDTO simGraphDTO, boolean rerender) {
        this.simGraphDTO = simGraphDTO;
        this.rerender = rerender;
    }
}
