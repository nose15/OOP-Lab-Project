package org.pwr.app;

import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static org.pwr.app.CustomPanels.*;

public class App {
    private JFrame currentFrame;

    private JPanel setMainConfPanel() {
        JPanel confPanel = new JPanel();
        confPanel.setLayout(new GridLayout(12, 1));

        SpinnerChangeListener spinnerChangeListener = new SpinnerChangeListener();
        SliderChangeListener sliderChangeListener = new SliderChangeListener();
        CheckBoxActionListener checkBoxActionListener = new CheckBoxActionListener();

        confPanel.add(createSpinnerInputPanel("Liczba hakerów", 0, 100, spinnerChangeListener));
        confPanel.add(createSpinnerInputPanel("Liczba Informatyków", 0, 100, spinnerChangeListener));
        confPanel.add(createSpinnerInputPanel("Liczba pracowników", 0, 1000, spinnerChangeListener));
        confPanel.add(createSliderInputPanel("Kompetencje Informatyków", 0, 100, sliderChangeListener));
        confPanel.add(createSliderInputPanel("Kompetencje Hakerów", 0, 100, sliderChangeListener));
        confPanel.add(createSpinnerInputPanel("Tempo utraty odporności", 0, 100, spinnerChangeListener));
        confPanel.add(createCheckBoxInputPanel("Rozprzestrzenianie się Malwaru", checkBoxActionListener));
        confPanel.add(createSpinnerInputPanel("Tempo rozprzestrzeniania się malwaru", 0, 100, spinnerChangeListener));

        return confPanel;
    }

    private JSplitPane setMainRightSplitPlane() {
        JSplitPane rightSplitPane = new JSplitPane();
        rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setDividerLocation(900);

        JPanel mapPanel = setMainMapPanel();
        JPanel statsPanel = setMainStatsPanel();

        rightSplitPane.setTopComponent(mapPanel);
        rightSplitPane.setBottomComponent(statsPanel);

        return rightSplitPane;
    }

    private JPanel setMainMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(1, 1));

        return mapPanel;
    }

    private JPanel setMainStatsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(5, 1));

        return statsPanel;
    }

    private JFrame setMainPage() {
        this.currentFrame = new JFrame("Symulacja Ataku Hakerskiego");

        Border border = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray);
        JSplitPane mainSplitPane = new JSplitPane();

        mainSplitPane.setBorder(border);
        mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setDividerLocation(300);

        JPanel confPanel = setMainConfPanel();
        JSplitPane rightSplitPane = setMainRightSplitPlane();
        mainSplitPane.setTopComponent(confPanel);
        mainSplitPane.setBottomComponent(rightSplitPane);

        currentFrame.add(mainSplitPane, BorderLayout.CENTER);
        return currentFrame;
    }

    private App() {
        setMainPage();
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}