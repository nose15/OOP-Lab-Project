package org.pwr.dtos;

import org.pwr.simulation.graph.Graph;
import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimGraphDTO {
    private Map<Node, List<Node>> map;

    public SimGraphDTO(Graph graph) {
        this.map = graph.getMap();
    }

    public Map<Node, List<Node>> getSimMap() {
        return this.map;
    }
}
