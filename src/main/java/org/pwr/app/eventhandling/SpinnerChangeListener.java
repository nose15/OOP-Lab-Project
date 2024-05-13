package org.pwr.app.eventhandling;

import org.pwr.app.InputHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerChangeListener extends BaseEventListener implements ChangeListener {
    public SpinnerChangeListener(InputHandler inputHandler) {
        super(inputHandler);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSpinner spinner = (JSpinner) changeEvent.getSource();
        int spinnerValue = (int) spinner.getValue();
        inputHandler.handleInput(spinner.getName(), spinnerValue);
    }
}
