package org.pwr.dtos;

import org.pwr.simulation.graph.Graph;

public class SimGraphDTO {
    private Graph graph;

    public SimGraphDTO(Graph graph) {
        this.graph = graph;
    }

    public Graph getSimMap() {
        return this.graph;
    }
}
