package org.pwr.app.eventhandling;

import org.pwr.InputHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxActionListener extends BaseEventListener implements ActionListener {
    public CheckBoxActionListener(InputHandler inputHandler) {
        super(inputHandler);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBox checkBox = (JCheckBox) actionEvent.getSource();
        boolean checkBoxValue = checkBox.isSelected();
        this.inputHandler.HandleInput(checkBox.getName(), checkBoxValue);
    }
}
