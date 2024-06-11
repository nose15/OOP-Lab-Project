package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ITSpec extends Agent {
    public static Queue<Node> callsForHelp;
    private final Queue<Boolean> initiative;
    private final float baseCallForHelpCooldown;
    private final float targetHealingThreshold;
    private float callForHelpCooldown;

    private float prevTargetState;

    public ITSpec(float skill) {
        this.skill = skill;
        this.initiative = new LinkedList<>();
        this.targetHealingThreshold = 0.75f;
        this.baseCallForHelpCooldown = 10;
        this.callForHelpCooldown = this.baseCallForHelpCooldown;
        initiative.add(true);
    }

    private void checkInitiative(float targetState) {
        if (initiative.size() > 2) {
            initiative.remove();
        }
        initiative.add(targetState >= prevTargetState);

        if (!initiative.contains(true)) {
            this.callForHelp();
        }
    }

    @Override
    public void act() {
        this.location.heal(this.skill);
        float targetState = this.location.getState();

        checkInitiative(targetState);

        prevTargetState = targetState;


        if (targetState >= targetHealingThreshold) {
            this.moveFurther();
            return;
        }

        if (callForHelpCooldown > 1) callForHelpCooldown--;
    }

    protected void moveFurther() {
        if (!callsForHelp.isEmpty()) {
            move(callsForHelp.remove());
        }

        ArrayList<Node> computers = this.location.getComputers();
        Node target = this.searchForTarget(computers);
        if (target != null) {
            this.move(target);
            return;
        }

        ArrayList<Node> switches = this.location.getSwitches();
        target = this.searchForTarget(switches);
        if (target != null) {
            this.move(target);
            return;
        }

        if (this.location.getParents() != null) {
            this.move(this.location.getParents());
        }
    }

    private Node searchForTarget(ArrayList<Node> nodes) {
        if (nodes != null) {
            for (Node node : nodes) {
                if (node.getState() < this.targetHealingThreshold) {
                    return node;
                }
            }
        }

        return null;
    }

    public void callForHelp() {
        if (callForHelpCooldown == 0) {
            callsForHelp.add(location);
            this.callForHelpCooldown = this.baseCallForHelpCooldown;
        }
    }
}
