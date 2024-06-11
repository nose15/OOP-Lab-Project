package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

import java.util.*;

public class Hacker extends Agent {

    private final float targetHackedThreshold;
    public static Set<Node> knownNodes;


    public Hacker(float skill) {
        super();
        this.skill = skill;
        this.targetHackedThreshold = -0.75f; //TODO: Make it a simulation parameter, the same for ITSpec
        this.initiative.add(true);
    }


    private boolean checkRepelled(float targetState) {
        if (initiative.size() > 2) {
            initiative.remove();
        }
        initiative.add(targetState > prevTargetState);

        return  (!initiative.contains(false) && initiative.size() > 2);
    }

    @Override
    public void act() {
        knownNodes.add(this.location);

        this.location.hack(this.skill);
        float targetState = this.location.getState();

        if (targetState < targetHackedThreshold) {
            this.moveFurther();
            return;
        }

        if (checkRepelled(targetState)) {
            this.moveRandom();
            return;
        }

        this.prevTargetState = targetState;
        turnsCount++;
    }

    protected void moveFurther() {
        Node parent;
        parent = this.location.revealParents();

        if (parent != null) {
            move(parent);
            return;
        }

        ArrayList<Node> switches = this.location.revealSwitches();
        Node target = this.searchForTarget(switches);
        if (target != null) {
            move(target);
        }

        ArrayList<Node> children = this.location.revealChildren();
        target = this.searchForTarget(children);
        if (target != null) {
            move(target);
        }

        moveRandom();
    }

    private Node searchForTarget(ArrayList<Node> nodes) {
        if (nodes != null) {
            for (Node node : nodes) {
                if (node.getState() < this.targetHackedThreshold) {
                    return node;
                }
            }
        }

        return null;
    }

    private void moveRandom() {
        if (knownNodes.size() == 1) {
            return;
        }

        int i = 0;
        int idx = new Random().nextInt(knownNodes.size());
        Node newLocation = null;

        for (Node node : knownNodes) {
            if (idx == i) {
                newLocation = node;
                break;
            }
            i++;
        }

        if (newLocation != null) {
            this.move(newLocation);
        }
    }
}
