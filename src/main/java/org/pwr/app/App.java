package org.pwr.app;

import org.pwr.app.gui.View;
import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;
import org.pwr.simulation.SimulationManager;
import org.pwr.simulation.graph.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    private App() {
        BlockingQueue<SimStateDTO> simToGUIQueue = new LinkedBlockingQueue<SimStateDTO>(2);
        BlockingQueue<ConfigDTO> guiToSimQueue = new LinkedBlockingQueue<ConfigDTO>(2);

        new SimulationManager(simToGUIQueue, guiToSimQueue);
        View view = new View(simToGUIQueue, guiToSimQueue);
        view.run();
    }

    public static void main(String[] args) {
        new App();
    }
}
