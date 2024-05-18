package org.pwr.app.eventhandling;

import org.pwr.app.InputManager;
import org.pwr.dtos.ConfigDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

public class CheckBoxActionListener extends BaseEventListener implements ActionListener {
    public CheckBoxActionListener(InputManager inputManager) {
        super(inputManager);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBox checkBox = (JCheckBox) actionEvent.getSource();
        boolean checkBoxValue = checkBox.isSelected();
        inputManager.processInput(checkBox.getName(), checkBoxValue);
    }
}
