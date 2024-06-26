package org.pwr.simulation.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {


    //SimpleGraphGenerator test
    @Test
    public void testSimpleGraphGenerator_5Switch_10Computer() {
        // Arrange
        int expectedSwitches = 5;
        int expectedComputers = 10;
        Graph graph = new Graph();

        // Act
        graph.graphGeneratorSimple(expectedSwitches, expectedComputers);

        // Assert
        int actualSwitches = 0;
        int actualComputers = 0;
        for (Node node : graph.getMap().keySet()) {
            if (node instanceof Switch) {
                actualSwitches++;
            } else if (node instanceof Computer) {
                actualComputers++;
            }
        }

        assertEquals(expectedSwitches, actualSwitches, "The number of switches in the graph does not match the expected number");
        assertEquals(expectedComputers, actualComputers, "The number of computers in the graph does not match the expected number");

    }

    @Test
    public void testSimpleGraphGenerator_1000Switch_2000Computer() {
        // Arrange
        int expectedSwitches = 1000;
        int expectedComputers = 2000;
        Graph graph = new Graph();

        // Act
        graph.graphGeneratorSimple(expectedSwitches, expectedComputers);

        // Assert
        int actualSwitches = 0;
        int actualComputers = 0;
        for (Node node : graph.getMap().keySet()) {
            if (node instanceof Switch) {
                actualSwitches++;
            } else if (node instanceof Computer) {
                actualComputers++;
            }
        }

        assertEquals(expectedSwitches, actualSwitches, "The number of switches in the graph does not match the expected number");
        assertEquals(expectedComputers, actualComputers, "The number of computers in the graph does not match the expected number");
    }

    @Test
    public void testGraphGeneratorSimple_0Switch_10Computer_ThrowsException_For0Switch() {
        // Arrange
        int switches = 0;
        int computers = 10;
        Graph graph = new Graph();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> graph.graphGeneratorSimple(switches, computers));
    }

    @Test
    public void testGraphGeneratorSimple_5Switch_0Computer_ThrowsException_For0Computers() {
        // Arrange
        int switches = 5;
        int computers = 0;
        Graph graph = new Graph();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> graph.graphGeneratorSimple(switches, computers));
    }

    //AdvanceGraphGenerator test
    @Test
    public void testAdvanceGraphGenerator_5Switch_10Computer() {
        System.out.println("testAdvanceGraphGenerator don't work if you turn on all tests at once");

        // Arrange
        int expectedSwitches = 5;
        int expectedComputers = 10;
        float switchConnectivity = 0.5f;
        float computerConsistancy = 0.5f;
        Graph graph = new Graph();

        // Act
        graph.graphGeneratorAdvance(expectedSwitches, expectedComputers, switchConnectivity, computerConsistancy);

        // Assert
        int actualSwitches = 0;
        int actualComputers = 0;
        for (Node node : graph.getMap().keySet()) {
            if (node instanceof Switch) {
                actualSwitches++;
            } else if (node instanceof Computer) {
                actualComputers++;
            }
        }

        assertEquals(expectedSwitches, actualSwitches, "The number of switches in the graph does not match the expected number");
        assertEquals(expectedComputers, actualComputers, "The number of computers in the graph does not match the expected number");
    }

    @Test
    public void testAdvanceGraphGenerator_1000Switch_2000Computer() {
        System.out.println("testAdvanceGraphGenerator don't work if you turn on all tests at once");

        // Arrange
        int expectedSwitches = 1000;
        int expectedComputers = 2000;
        float switchConnectivity = 0.5f;
        float computerConsistancy = 0.5f;
        Graph graph = new Graph();

        // Act
        graph.graphGeneratorAdvance(expectedSwitches, expectedComputers, switchConnectivity, computerConsistancy);

        // Assert
        int actualSwitches = 0;
        int actualComputers = 0;
        for (Node node : graph.getMap().keySet()) {
            if (node instanceof Switch) {
                actualSwitches++;
            } else if (node instanceof Computer) {
                actualComputers++;
            }
        }

        assertEquals(expectedSwitches, actualSwitches, "The number of switches in the graph does not match the expected number");
        assertEquals(expectedComputers, actualComputers, "The number of computers in the graph does not match the expected number");
    }

    @Test
    public void testAdvanceGraphGeneratore_0Switch_10Computer_ThrowsException_For0Switch() {
        // Arrange
        int switches = 0;
        int computers = 10;
        float switchConnectivity = 0.5f;
        float computerConsistancy = 0.5f;
        Graph graph = new Graph();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> graph.graphGeneratorAdvance(switches, computers, switchConnectivity, computerConsistancy));
    }

    @Test
    public void testAdvanceGraphGenerator_5Switch_0Computer_ThrowsException_For0Computers() {
        // Arrange
        int switches = 5;
        int computers = 0;
        float switchConnectivity = 0.5f;
        float computerConsistancy = 0.5f;
        Graph graph = new Graph();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> graph.graphGeneratorAdvance(switches, computers, switchConnectivity, computerConsistancy));
    }

    @Test
    public void testAdvanceGraphGenerator_5Switch_0Computer_ThrowsException_ForLessComputerThenSwitch() {
        // Arrange
        int switches = 5;
        int computers = 3;
        float switchConnectivity = 0.5f;
        float computerConsistancy = 0.5f;
        Graph graph = new Graph();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> graph.graphGeneratorAdvance(switches, computers, switchConnectivity, computerConsistancy));
    }
}