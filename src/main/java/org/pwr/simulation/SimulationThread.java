package org.pwr.simulation;

public class SimulationThread implements Runnable {

    public SimulationThread() {
        Simulation simulation = new Simulation();
    }

    @Override
    public void run() {
        System.out.println("Sim Thread Running");
    }

    public void stop() {
        System.out.println("Sim Thread Stopped");
    }

    public void pause() {
        System.out.println("Sim Thread Paused");
    }

    public void resume() {
        System.out.println("Sim Thread Resumed");
    }
}
