package org.pwr.app.eventhandling;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider slider = (JSlider)changeEvent.getSource();
        System.out.println(slider.getValue());
    }
}