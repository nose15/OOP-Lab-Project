package org.pwr.app.gui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.pwr.app.InputManager;
import org.pwr.app.eventhandling.ButtonActionListener;
import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;
import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;
import org.pwr.simulation.graph.Node;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static org.pwr.app.gui.CustomPanels.*;
import static org.pwr.app.gui.CustomPanels.createSpinnerInputPanel;

public class View {
    private JFrame currentFrame;
    private final ChangeListener spinnerChangeListener;
    private final ChangeListener sliderChangeListener;
    private final ActionListener checkBoxActionListener;
    private final ActionListener buttonActionListener;
    private DefaultListModel<String> mapListModel;
    private BlockingQueue<SimStateDTO> simToGuiQueue;
    private InputManager inputManager;
    private Graph displayGraph;
    private Map<Node, List<Node>> map;

    public View(BlockingQueue<SimStateDTO> simToGuiQueue, BlockingQueue<ConfigDTO> guiToSimQueue) {
        this.inputManager = new InputManager(guiToSimQueue);
        this.simToGuiQueue = simToGuiQueue;
        this.spinnerChangeListener = new SpinnerChangeListener(this.inputManager);
        this.sliderChangeListener = new SliderChangeListener(this.inputManager);
        this.checkBoxActionListener = new CheckBoxActionListener(this.inputManager);
        this.buttonActionListener = new ButtonActionListener(this.inputManager);

        this.mapListModel = new DefaultListModel<>();
        this.displayGraph = new MultiGraph("simMapDisplay");
    }

    private JPanel setMainConfPanel() {
        JPanel confPanel = new JPanel();
        confPanel.setLayout(new GridLayout(12, 1));

        // TODO: Make it a separate function so the App can just add new config options
        // Now it's hardcoded but giving the control to the App module would be more appropriate
        confPanel.add(createSpinnerInputPanel("Liczba hakerów", 0, 100, spinnerChangeListener, "setNumberOfHackers"));
        confPanel.add(createSpinnerInputPanel("Liczba Informatyków", 0, 100, spinnerChangeListener, "setNumberOfITExperts"));
        confPanel.add(createSpinnerInputPanel("Liczba pracowników", 0, 1000, spinnerChangeListener, "setNumberOfNodes"));
        confPanel.add(createSliderInputPanel("Kompetencje Informatyków", 0, 100, sliderChangeListener, "setAvgItSkills"));
        confPanel.add(createSliderInputPanel("Kompetencje Hakerów", 0, 100, sliderChangeListener, "setAvgHackerSkills"));
        confPanel.add(createSpinnerInputPanel("Tempo utraty odporności", 0, 100, spinnerChangeListener, "setResistanceLossPace"));
        confPanel.add(createCheckBoxInputPanel("Rozprzestrzenianie się Malwaru", checkBoxActionListener, "setMalwareSpread"));
        confPanel.add(createSpinnerInputPanel("Tempo rozprzestrzeniania się malwaru", 0, 100, spinnerChangeListener, "setMalwareSpreadPace"));
        confPanel.add(createButtonPanel("Start", this.buttonActionListener, "start"));

        return confPanel;
    }

    private JSplitPane setMainRightSplitPlane() {
        JSplitPane rightSplitPane = new JSplitPane();
        rightSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        rightSplitPane.setDividerLocation(900);

        JPanel mapPanel = addMainMapPanel();
        JPanel statsPanel = addMainStatsPanel();

        rightSplitPane.setTopComponent(mapPanel);
        rightSplitPane.setBottomComponent(statsPanel);

        return rightSplitPane;
    }

    private JPanel addMainMapPanel() {
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());
        Viewer viewer = displayGraph.display(false);
        ViewPanel viewPanel = viewer.addDefaultView(false);
        mapPanel.add(viewPanel);
        viewer.enableAutoLayout();
        return mapPanel;
    }

    private JPanel addMainStatsPanel() {
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
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentFrame.setSize(800,600);
        currentFrame.setMinimumSize(new Dimension(800, 600));
        return currentFrame;
    }

    private void UpdateSimDisplay(SimStateDTO simState) {
        SwingUtilities.invokeLater(() -> {
            this.map = simState.simGraphDTO.getSimMap();
            // TODO: New thread that handles graph display
        });
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            setMainPage();
            currentFrame.setVisible(true);
        });

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                SimStateDTO simState = this.simToGuiQueue.take();
                UpdateSimDisplay(simState);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, 0,  1, TimeUnit.SECONDS);
    }
}
