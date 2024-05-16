package org.pwr.simulation.graph.generate;

import org.pwr.simulation.graph.struct.Computer;
import org.pwr.simulation.graph.struct.Node;
import org.pwr.simulation.graph.struct.Router;
import org.pwr.simulation.graph.struct.Switch;

public class GraphGenerator {
    private Node head;
    private int numberOfSwitch = 4;
    private int numberOfComputers =20;

    //Simple generator for now give only rotuer -> switch -> pcs/switch no connection
    public GraphGenerator()
    {
        head = new Router();
        for(int i = 0; i < numberOfSwitch; i++)
        {
            head.addConnection(new Switch(head));
            for
            (int j = 0; j < (numberOfComputers/numberOfSwitch)+
                    (numberOfComputers%numberOfSwitch>i?1:0)
                    ; j++)
            {
                head.getConnections().get(i).addConnection(new Computer(head.getConnections().get(i)));
            }
        }
    }

    public Node getHead()
    {
        return head;
    }

    //Builders
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
}
