package org.pwr.app.eventhandling;

import org.pwr.app.InputManager;
import org.pwr.dtos.ConfigDTO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.concurrent.BlockingQueue;

public class SliderChangeListener extends BaseEventListener implements ChangeListener {
    public SliderChangeListener(InputManager inputManager) {
        super(inputManager);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider slider = (JSlider) changeEvent.getSource();
        int sliderValue = slider.getValue();
        inputManager.processInput(slider.getName(), sliderValue);
    }
}