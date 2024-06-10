package org.pwr.simulation.agents;

import org.pwr.simulation.graph.Computer;
import org.pwr.simulation.graph.Node;
import org.pwr.simulation.graph.Router;
import org.pwr.simulation.graph.Switch;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class ITSpec extends Agent {
    public static Queue<Node> callsForHelp;
    private Queue<Boolean> initiative;
    private float prevTargetState;
    private float callForHelpCooldown = 10;
    private final float healingThreshold = 0.75f;

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


        if (targetState > healingThreshold) {
            this.moveFurther();
            return;
        }

        if (callForHelpCooldown > 1) callForHelpCooldown--;
    }

    protected void moveFurther() {
        if (!callsForHelp.isEmpty()) {
            move(callsForHelp.remove());
        }

        if (this.location.getClass() == Switch.class) {
            Switch switchObj = (Switch) this.location;
            ArrayList<Node> targets = switchObj.getComputers();

            for (Node computer : targets) {
                if (computer.getState() < this.healingThreshold) {
                    this.move(computer);
                    return;
                }
            }

            targets = switchObj.getSwitches();

            for (Node s : targets) {
                if (s.getState() < this.healingThreshold) {
                    this.move(s);
                    return;
                }
            }

            if (switchObj.getParents() != null) {
                this.move(switchObj.getParents());
                return;
            }

            this.move(targets.get(new Random().nextInt(targets.size())));
        }

        if (this.location.getClass() == Computer.class) {
            this.move(this.location.getParents());
        }

        if (this.location.getClass() == Router.class) {
            Router routerObj = (Router) this.location;

            ArrayList<Node> targets = routerObj.getSwitches();

            for (Node s : targets) {
                if (s.getState() < this.healingThreshold) {
                    this.move(s);
                    return;
                }
            }

            this.move(targets.get(new Random().nextInt(targets.size())));
        }
    }

    public void callForHelp() {
        if (callForHelpCooldown == 0) {
            callsForHelp.add(location);
            callForHelpCooldown = 10;
        }
    }
}
