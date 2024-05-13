package org.pwr.app;

import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;
import org.pwr.app.gui.View;
import org.pwr.simulation.SimInputHandler;
import org.pwr.simulation.SimulationManager;

public class App {
    private final View view;
    private final SimulationManager simulationManager;
    private InputHandler inputHandler;


    private App() {
        this.simulationManager = new SimulationManager();
        this.inputHandler = new SimInputHandler(simulationManager);

        SliderChangeListener sliderChangeListener = new SliderChangeListener(this.inputHandler);
        SpinnerChangeListener spinnerChangeListener = new SpinnerChangeListener(this.inputHandler);
        CheckBoxActionListener checkBoxActionListener = new CheckBoxActionListener(this.inputHandler);

        view = new View(spinnerChangeListener, sliderChangeListener, checkBoxActionListener);
        view.run();
    }

    public static void main(String[] args) {
        new App();
    }
}
