package org.pwr.app;

import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomPanels {
    public static JPanel createSpinnerInputPanel(String label, int min, int max, ChangeListener spinnerChangeListener) {
        JPanel spinnerInputPanel = new JPanel();
        spinnerInputPanel.setLayout(new GridLayout(2, 1));
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, min, max, 1); // initial value, min, max, step
        JSpinner spinner = new JSpinner(spinnerModel);
        JLabel jlabel = new JLabel(label);

        spinner.addChangeListener(spinnerChangeListener);

        spinnerInputPanel.add(jlabel);
        spinnerInputPanel.add(spinner);

        return spinnerInputPanel;
    }

    public static JPanel createSliderInputPanel(String label, int min, int max, ChangeListener sliderChangeListener) {
        JPanel sliderInputPanel = new JPanel();
        sliderInputPanel.setLayout(new GridLayout(2, 1));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, 1);
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

    public static JPanel createCheckBoxInputPanel(String label, ActionListener checkBoxActionListener) {
        JPanel checkBoxInputPanel = new JPanel();
        checkBoxInputPanel.setLayout(new GridLayout(2, 1));

        JCheckBox checkBox = new JCheckBox(label);

        checkBox.addActionListener(checkBoxActionListener);
        checkBoxInputPanel.add(checkBox);

        return checkBoxInputPanel;
    }

}
