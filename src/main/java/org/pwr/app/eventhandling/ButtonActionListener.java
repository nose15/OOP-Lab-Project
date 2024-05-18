package org.pwr.app.eventhandling;

import org.pwr.app.InputManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener extends BaseEventListener implements ActionListener {
    public ButtonActionListener(InputManager inputManager) {
        super(inputManager);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        inputManager.processInput(button.getName(), 1);
    }
}
