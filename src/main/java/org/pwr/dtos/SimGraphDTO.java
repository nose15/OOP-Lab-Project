package org.pwr.dtos;

import org.pwr.simulation.graph.Node;

import java.util.ArrayList;

public class SimGraphDTO {
    private final ArrayList<Node> simNodes;
    private final Node rootNode;

    public SimGraphDTO(Node rootNode) {
        this.rootNode = rootNode;
        this.simNodes = new ArrayList<>();
        simNodes.add(rootNode);
    }

    public ArrayList<Node> getSimNodes() {
        return this.simNodes;
    }

    public Node getRootNode() {
        return rootNode;
    }
}
