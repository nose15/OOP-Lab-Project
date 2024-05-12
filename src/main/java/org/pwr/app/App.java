package org.pwr.app;

import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;
import org.pwr.simulation.SimInputHandler;
import org.pwr.simulation.SimulationManager;

public class App {
    private View view;


    private App() {
        SimulationManager simulationManager = new SimulationManager();
        SimInputHandler simInputHandler = new SimInputHandler(simulationManager);

        SliderChangeListener sliderChangeListener = new SliderChangeListener(simInputHandler);
        SpinnerChangeListener spinnerChangeListener = new SpinnerChangeListener(simInputHandler);
        CheckBoxActionListener checkBoxActionListener = new CheckBoxActionListener(simInputHandler);

        view = new View(spinnerChangeListener, sliderChangeListener, checkBoxActionListener);
        view.run();
    }

    public static void main(String[] args) {
        new App();
    }
}
