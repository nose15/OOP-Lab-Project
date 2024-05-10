package org.pwr.app;

import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class App {
    private JFrame frame;

    private JPanel createSpinnerInputPanel(String label, int min, int max) {
        JPanel spinnerInputPanel = new JPanel();
        spinnerInputPanel.setLayout(new GridLayout(2, 1));
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, min, max, 1); // initial value, min, max, step
        JSpinner spinner = new JSpinner(spinnerModel);
        JLabel jlabel = new JLabel(label);
        spinnerInputPanel.add(jlabel);
        spinnerInputPanel.add(spinner);

        return spinnerInputPanel;
    }

    private JPanel createSliderInputPanel(String label, int min, int max) {
        JPanel sliderInputPanel = new JPanel();
        sliderInputPanel.setLayout(new GridLayout(2, 1));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, 1);
        JLabel label1 = new JLabel(label);

        slider.setMajorTickSpacing(10); // Major ticks every 10 units
        slider.setMinorTickSpacing(1); // Minor ticks every 1 unit
        slider.setPaintTicks(true); // Display ticks
        slider.setPaintLabels(true); // Display labels

        sliderInputPanel.add(label1);
        sliderInputPanel.add(slider);

        return sliderInputPanel;
    }

    private JPanel createCheckBoxInputPanel(String label) {
        JPanel checkBoxInputPanel = new JPanel();
        checkBoxInputPanel.setLayout(new GridLayout(2, 1));

        JCheckBox checkBox = new JCheckBox(label);
//        JLabel label1 = new JLabel(label);

//        checkBoxInputPanel.add(label1);
        checkBoxInputPanel.add(checkBox);

        return checkBoxInputPanel;
    }


    private App() {
        this.frame = new JFrame("Symulacja Ataku Hakerskiego");
        JPanel confPanel = new JPanel();

        Border border = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray);

//        confPanel.setBorder(border);
        confPanel.setLayout(new GridLayout(12, 1));

        JPanel mapPanel = new JPanel();
//        mapPanel.setBorder(border);
        mapPanel.setLayout(new GridLayout(1, 1));

        JPanel statsPanel = new JPanel();
//        statsPanel.setBorder(border);
        statsPanel.setLayout(new GridLayout(5, 1));

        JSplitPane rightSplitPane = new JSplitPane();
//        rightSplitPane.setBorder(border);
        rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setDividerLocation(900);
        rightSplitPane.setTopComponent(mapPanel);
        rightSplitPane.setBottomComponent(statsPanel);

        JSplitPane mainSplitPane = new JSplitPane();
        mainSplitPane.setBorder(border);
        mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setDividerLocation(300);
        mainSplitPane.setTopComponent(confPanel);
        mainSplitPane.setBottomComponent(rightSplitPane);



        confPanel.add(createSpinnerInputPanel("Liczba hakerów", 0, 100));
        confPanel.add(createSpinnerInputPanel("Liczba Informatyków", 0, 100));
        confPanel.add(createSpinnerInputPanel("Liczba pracowników", 0, 1000));
        confPanel.add(createSliderInputPanel("Kompetencje Informatyków", 0, 100));
        confPanel.add(createSliderInputPanel("Kompetencje Hakerów", 0, 100));
        confPanel.add(createSpinnerInputPanel("Tempo utraty odporności", 0, 100));
        confPanel.add(createCheckBoxInputPanel("Rozprzestrzenianie się Malwaru"));
        confPanel.add(createSpinnerInputPanel("Tempo rozprzestrzeniania się malwaru", 0, 100));


        frame.add(mainSplitPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
