package org.pwr.dtos;

import org.pwr.simulation.graph.Graph;
import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimGraphDTO {
    private Graph graph;

    public SimGraphDTO(Graph graph) {
        this.graph = graph;
    }

    public Graph getSimMap() {
        return this.graph;
    }
}
