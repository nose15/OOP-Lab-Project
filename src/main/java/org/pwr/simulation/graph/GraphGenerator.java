package org.pwr.simulation.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
    private Node head;
    private int numberOfSwitch;
    private int numberOfComputers;

    //Complex generator attributes
    private float switchDeepness; //How many switches in a row (switch that have a switch parent)
    private float computerConsistency; //How many computers is connected per switch (focus on equal distribution or totally random)

    //Default generator
    public GraphGenerator()
    {
        this.head = new Router();
        this.numberOfSwitch = 4;
        this.numberOfComputers = 20;


    }

//    //Simple custom generator
//    //Explain:
//    //  switch can be Parent of another switch,
//    //  but there is no connection between switches
//    //  with same parent (only by parent)
//    public GraphGenerator(int numberOfSwitch, int numberOfComputers)
//    {
//        this.head = new Router();
//        this.numberOfSwitch = numberOfSwitch;
//        this.numberOfComputers = numberOfComputers;
//
//        Random random = new Random();
//        List<Switch> switches = new ArrayList<>();
//
//        //Switch generation
//        for(int i = 0; i < numberOfSwitch; i++)
//        {
//            switches.add(new Switch());
//            float chance = random.nextFloat(1,0);
//
//            if(chance > 0.5 || i == 0)
//            {
//                head.addConnection(switches.get(i));
//                switches.get(i).setParent(head);
//            }
//            else
//            {
//                int chooseSwitch = random.nextInt(i-1);
//                switches.get(chooseSwitch).addConnection(switches.get(i));
//                switches.get(i).setParent(switches.get(chooseSwitch));
//            }
//        }
//
//        //Computer generation
//            //Part 1: Place one computer to endpoint switches
//        for(int i = 0; i < numberOfSwitch; i++)
//        {
//            if(switches.get(i).getConnections().isEmpty())
//            {
//                switches.get(i).addConnection(new Computer().setParent(switches.get(i)));
//            }
//        }
//            //Part 2: Place the rest of computers
//        for(int i = 0; i < numberOfComputers - numberOfSwitch; i++)
//        {
//            int chooseSwitch = random.nextInt(numberOfSwitch);
//            switches.get(chooseSwitch).addConnection(new Computer().setParent(switches.get(chooseSwitch)));
//        }
//    }

    //Complex custom generator
    //Plan:
    // User can define how many switches in a row (switch that have a switch parent)
    // and how many computers is connected per switch (focus on equal distribution or totally random)
    // moreover add possibility to connection between switches
    public GraphGenerator(int numberOfSwitch, int numberOfComputers, float switchDeepness, float computerConsistency)
    {
        //this(numberOfSwitch, numberOfComputers);
        this.numberOfSwitch = numberOfSwitch;
        this.numberOfComputers = numberOfComputers;
        this.switchDeepness = switchDeepness;
        this.computerConsistency = computerConsistency;
        //TODO: Complex custom generator

    }

    public Node getHead()
    {
        return head;
    }

    //Builders - probably not needed because of 3 constructors methods
    public GraphGenerator setNumberOfSwitch(int numberOfSwitch)
    {
        this.numberOfSwitch = numberOfSwitch;
        return this;
    }
    public GraphGenerator setNumberOfComputers(int numberOfComputers)
    {
        this.numberOfComputers = numberOfComputers;
        return this;
    }
    public GraphGenerator setSwitchDeepness(float switchDeepness)
    {
        this.switchDeepness = switchDeepness;
        return this;
    }
    public GraphGenerator setComputerConsistency(float computerConsistency)
    {
        this.computerConsistency = computerConsistency;
        return this;
    }
}
