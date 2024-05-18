package org.pwr.app.eventhandling;

import org.pwr.app.InputManager;
import org.pwr.dtos.ConfigDTO;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.concurrent.BlockingQueue;

public class SpinnerChangeListener extends BaseEventListener implements ChangeListener {
    public SpinnerChangeListener(InputManager inputManager) {
        super(inputManager);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSpinner spinner = (JSpinner) changeEvent.getSource();
        int spinnerValue = (int) spinner.getValue();
        inputManager.processInput(spinner.getName(), spinnerValue);
    }
}
