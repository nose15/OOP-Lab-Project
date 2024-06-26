package org.pwr.app.gui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomPanels {
    public static JPanel createSpinnerInputPanel(String label, int min, int max, ChangeListener spinnerChangeListener, String name) {
        JPanel spinnerInputPanel = new JPanel();
        spinnerInputPanel.setLayout(new GridLayout(2, 1));
        SpinnerModel spinnerModel = new SpinnerNumberModel(3, min, max, 1); // initial value, min, max, step
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setName(name);
        JLabel jlabel = new JLabel(label);

        spinner.addChangeListener(spinnerChangeListener);

        spinnerInputPanel.add(jlabel);
        spinnerInputPanel.add(spinner);

        return spinnerInputPanel;
    }

    public static JPanel createSliderInputPanel(String label, int min, int max, ChangeListener sliderChangeListener, String name) {
        JPanel sliderInputPanel = new JPanel();
        sliderInputPanel.setLayout(new GridLayout(2, 1));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, 1);
        slider.setName(name);
        JLabel label1 = new JLabel(label);

        slider.setMajorTickSpacing(10); // Major ticks every 10 units
        slider.setMinorTickSpacing(1); // Minor ticks every 1 unit
        slider.setPaintTicks(true); // Display ticks
        slider.setPaintLabels(true); // Display labels
        slider.addChangeListener(sliderChangeListener);

        sliderInputPanel.add(label1);
        sliderInputPanel.add(slider);

        return sliderInputPanel;
    }

    public static JPanel createCheckBoxInputPanel(String label, ActionListener checkBoxActionListener, String name) {
        JPanel checkBoxInputPanel = new JPanel();
        checkBoxInputPanel.setLayout(new GridLayout(2, 1));

        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setName(name);

        checkBox.addActionListener(checkBoxActionListener);
        checkBoxInputPanel.add(checkBox);

        return checkBoxInputPanel;
    }

    public static JPanel createButtonPanel(String label, ActionListener buttonActionListener, String name) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));

        JButton button = new JButton(label);
        button.setName(name);

        button.addActionListener(buttonActionListener);
        buttonPanel.add(button);

        return buttonPanel;
    }

}
