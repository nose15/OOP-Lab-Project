package org.pwr.app.eventhandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JCheckBox checkBox = (JCheckBox) actionEvent.getSource();
        System.out.println(checkBox.isSelected());
    }
}
