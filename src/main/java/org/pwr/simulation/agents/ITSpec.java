package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Node;

import java.util.Queue;

public class ITSpec extends Agent {
    public static Queue<Node> callsForHelp;
    private Queue<Boolean> initiative;
    private float prevTargetState;
    private float callForHelpCooldown = 10;

    private void checkInitiative(float targetState) {
        if (initiative.size() > 2) {
            initiative.remove();
        }
        initiative.add(targetState > prevTargetState);

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

        float healingThreshold = 0.75f;
        if (targetState > healingThreshold) {
            this.move();
            return;
        }

        if (callForHelpCooldown > 1) callForHelpCooldown--;
    }
    protected void move() {

    }

    public void callForHelp() {
        if (callForHelpCooldown == 0) {
            callsForHelp.add(location);
            callForHelpCooldown = 10;
        }
    }
}
