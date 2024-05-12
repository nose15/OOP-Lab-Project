package org.pwr.app.eventhandling;

import org.pwr.InputHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener extends BaseEventListener implements ChangeListener {
    public SliderChangeListener(InputHandler inputHandler) {
        super(inputHandler);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider slider = (JSlider)changeEvent.getSource();
        int sliderValue = slider.getValue();
        this.inputHandler.HandleInput(slider.getName(), sliderValue);
    }
}