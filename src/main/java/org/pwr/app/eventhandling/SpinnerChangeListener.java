package org.pwr.app.eventhandling;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSpinner spinner = (JSpinner) changeEvent.getSource();
        System.out.println(spinner.getValue());
    }
}
