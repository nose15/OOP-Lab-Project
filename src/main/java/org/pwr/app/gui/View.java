package org.pwr.app.gui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;
import org.pwr.app.InputManager;
import org.pwr.app.eventhandling.ButtonActionListener;
import org.pwr.app.eventhandling.CheckBoxActionListener;
import org.pwr.app.eventhandling.SliderChangeListener;
import org.pwr.app.eventhandling.SpinnerChangeListener;
import org.pwr.dtos.ConfigDTO;
import org.pwr.dtos.SimStateDTO;
import org.pwr.simulation.Simulation;
import org.pwr.simulation.graph.Node;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private BlockingQueue<SimStateDTO> simToGuiQueue;
    private InputManager inputManager;
    private Graph displayGraph;
    private Map<Node, List<Node>> map;
    private boolean isGraphRendered;

    public View(BlockingQueue<SimStateDTO> simToGuiQueue, BlockingQueue<ConfigDTO> guiToSimQueue) {
        this.inputManager = new InputManager(guiToSimQueue);
        this.simToGuiQueue = simToGuiQueue;
        this.spinnerChangeListener = new SpinnerChangeListener(this.inputManager);
        this.sliderChangeListener = new SliderChangeListener(this.inputManager);
        this.checkBoxActionListener = new CheckBoxActionListener(this.inputManager);
        this.buttonActionListener = new ButtonActionListener(this.inputManager);
        this.displayGraph = new MultiGraph("simMapDisplay");
    }

    private JPanel setMainConfPanel() {
        JPanel confPanel = new JPanel();
        confPanel.setLayout(new GridLayout(12, 1));

        // TODO: Make it a separate function so the App can just add new config options
        // Now it's hardcoded but giving the control to the App module would be more appropriate
        confPanel.add(createSpinnerInputPanel("Liczba hakerów", 1, 100, spinnerChangeListener, "setNumberOfHackers"));
        confPanel.add(createSpinnerInputPanel("Liczba Informatyków", 1, 100, spinnerChangeListener, "setNumberOfITExperts"));
        confPanel.add(createSpinnerInputPanel("Liczba switchy", 2, 100, spinnerChangeListener, "setNumberOfSwitches"));
        confPanel.add(createSpinnerInputPanel("Liczba pracowników", 2, 200, spinnerChangeListener, "setNumberOfComputers"));
        confPanel.add(createSliderInputPanel("Kompetencje Informatyków", 0, 100, sliderChangeListener, "setAvgItSkills"));
        confPanel.add(createSliderInputPanel("Kompetencje Hakerów", 0, 100, sliderChangeListener, "setAvgHackerSkills"));
        confPanel.add(createSpinnerInputPanel("Tempo utraty odporności", 0, 100, spinnerChangeListener, "setResistanceLossPace"));
        confPanel.add(createSpinnerInputPanel("Tempo rozprzestrzeniania się malwaru", 0, 100, spinnerChangeListener, "setMalwareSpreadPace"));
        confPanel.add(createCheckBoxInputPanel("Rozprzestrzenianie się Malwaru", checkBoxActionListener, "setMalwareSpread"));
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
        SwingViewer viewer = new SwingViewer(displayGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        mapPanel = (JPanel) viewer.addDefaultView(false);

        return mapPanel;
    }

    private JPanel addMainStatsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(5, 1));

        return statsPanel;
    }

    private void setMainPage() {
        this.currentFrame = new JFrame("Symulacja Ataku Hakerskiego");

        Border border = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray);
        JSplitPane mainSplitPane = new JSplitPane();

        mainSplitPane.setBorder(border);
        mainSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        mainSplitPane.setDividerLocation(300);

        JPanel confPanel = setMainConfPanel();
        //JSplitPane rightSplitPane = setMainRightSplitPlane();
        mainSplitPane.setTopComponent(confPanel);
        //mainSplitPane.setBottomComponent(rightSplitPane);

        JPanel map = addMainMapPanel();
        mainSplitPane.setBottomComponent(map);

        currentFrame.add(mainSplitPane, BorderLayout.CENTER);
        currentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        currentFrame.setSize(800,600);
        currentFrame.setMinimumSize(new Dimension(800, 600));
    }

    //TODO: Clean up styling because the amount of spaghetti here is scary
    private void UpdateSimDisplay(SimStateDTO simState) {
        SwingUtilities.invokeLater(() -> {
            this.map = simState.simGraphDTO.getSimMap().getMap();

            if (simState.rerender) {
                this.displayGraph.clear();
            }

            displayGraph.setAttribute("ui.stylesheet", "node { text-size: 15px; text-alignment: under;}");
            
            for (Node key : map.keySet()) {
                if (this.displayGraph.getNode(String.valueOf(key)) == null) {
                    this.displayGraph.addNode(String.valueOf(key));
                }
                for (Node a : map.get(key)) {
                    if (this.displayGraph.getNode(String.valueOf(a)) == null) this.displayGraph.addNode(String.valueOf(a));
                    if (this.displayGraph.getEdge(a.getId() + "0" + key.getId()) == null &&
                            this.displayGraph.getEdge(key.getId() + "0" + a.getId()) == null)
                        this.displayGraph.addEdge(key.getId() + "0" + a.getId(), String.valueOf(key), String.valueOf(a));


                    //Add each style class to array then add all calsses to node at once
                    ArrayList<String> defineNodeStyle = new ArrayList<>();
                    //Define type of node and set specific style
                    if(key.getId()>0)
                            defineNodeStyle.add("switch");
                    else if(key.getId()<0)
                            defineNodeStyle.add("computer");
                    else if(key.getId() == 0)
                            defineNodeStyle.add("router");

                    //Define current state of node
                    if (key.getState() > 0)
                        defineNodeStyle.add("healed");
                    else if (key.getState() < 0)
                        defineNodeStyle.add("infected");
                    else
                        defineNodeStyle.add("neutral");

                    if(key.getNumOfIT() > 0 && key.getNumOfHackers() > 0)
                        defineNodeStyle.add("dubleAgent");
                    else if(key.getNumOfHackers() > 0)
                        defineNodeStyle.add("hacker");
                    else if(key.getNumOfIT() > 0)
                        defineNodeStyle.add("it");
                    else
                        defineNodeStyle.add("empty");

                    this.displayGraph.getNode(String.valueOf(key)).setAttribute("ui.class", defineNodeStyle.toArray());
                }
                this.displayGraph.getNode(String.valueOf(key)).setAttribute("ui.label",key.getState() + " \n h: " + key.getNumOfHackers() + " | it: " + key.getNumOfIT());
            }

            String cssPath = "file:///" + System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/style.css";
            displayGraph.setAttribute("ui.stylesheet", "url('" + cssPath + "')");

        });
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            setMainPage();
            currentFrame.setVisible(true);
        });

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2);
        scheduler.scheduleAtFixedRate(this::singleUpdate, 0,  1, TimeUnit.SECONDS);
    }

    private void singleUpdate() {
        try {
            SimStateDTO simState = this.simToGuiQueue.take();
            UpdateSimDisplay(simState);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
