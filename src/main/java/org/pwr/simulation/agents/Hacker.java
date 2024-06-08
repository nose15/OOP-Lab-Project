package org.pwr.simulation.agents;

import java.util.LinkedList;
import java.util.Queue;

public class Hacker extends Agent {
    private int turnsCount;
    private float prevTargetState;
    private Queue<Boolean> initiative = new LinkedList<>();
    private float targetHackedThreshold = 0.75f;

    private void checkInitiative(float targetState) {
        if (initiative.size() > 2) {
            initiative.remove();
        }
        initiative.add(targetState > prevTargetState);

        if (!initiative.contains(true)) {
            this.done = true;
        }
    }

    @Override
    public void act() {
        this.location.hack(this.skill);
        float targetState = this.location.getState();

        if (targetState > targetHackedThreshold) {
            done = true;
        }

        checkInitiative(targetState);

        if (done) {
            this.move();
            return;
        }

        this.prevTargetState = targetState;
        turnsCount++;
    }

    @Override
    protected void move() {
        turnsCount = 0;
        prevTargetState = 0;
        initiative.clear();
    }
}
