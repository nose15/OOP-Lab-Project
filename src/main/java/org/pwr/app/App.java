package org.pwr.app;

import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static org.pwr.app.CustomPanels.*;

public class App {
    private View view;


    private App() {
        SliderChangeListener sliderChangeListener = new SliderChangeListener();
        SpinnerChangeListener spinnerChangeListener = new SpinnerChangeListener();
        CheckBoxActionListener checkBoxActionListener = new CheckBoxActionListener();

        view = new View(spinnerChangeListener, sliderChangeListener, checkBoxActionListener);
        view.run();
    }

    public static void main(String[] args) {
        new App();
    }
}
