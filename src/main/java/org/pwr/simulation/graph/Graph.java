package org.pwr.simulation.graph;

import java.util.*;
import org.pwr.simulation.graph.Node;

public class Graph {

    private Map<Node, List<Node>> map = new HashMap<>();

    public void addVertex(Node s)
    {
        map.put(s, new LinkedList<Node>());
    }
    public Node addAndGetVertex(Node s)
    {
        map.put(s, new LinkedList<Node>());
        return s;
    }

    public void addEdge(Node source, Node destination, boolean bidirectional)
    {
        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
        if (bidirectional)
            map.get(destination).add(source);
    }

    public int getVertexCount()
    {
        return map.keySet().size();
    }

    public int getEdgesCount(boolean bidirection)
    {
        int count = 0;
        for (Node v : map.keySet())
        {
            count += map.get(v).size();
        }
        if (bidirection)
            count = count / 2;
        return count;
    }
    public Node findVertex(int id)
    {
        for (Node v : map.keySet())
        {
            if (v.getId() == id)
                return v;
        }
        return null;
    }

    public boolean hasVertex(Node s)
    {
        return map.containsKey(s);
    }

    public boolean hasEdge(Node s, Node d)
    {
        return map.get(s).contains(d);
    }

    public List<Node> getNeighbors(Node s)
    {
        return map.get(s);
    }

    public void printGraph()
    {
        for (Node v : map.keySet())
        {
            System.out.println(v + " -> " + map.get(v));
            System.out.print(v.getId() + " -> [");
            for (Node n : map.get(v))
            {
                System.out.print(n.getId());
                if(map.get(v).get(map.get(v).size()-1) != n)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public void simpleGraphGenerator()
    {
        int numberOfSwitch = 4;
        int numberOfComputers = 20;
        addVertex((Node) new Router());

        for(int i = 0; i<numberOfSwitch; i++)
        {
            addEdge(findVertex(0), addAndGetVertex((Node) new Switch()), true);
            for(int j = 0; j<numberOfComputers/numberOfSwitch; j++)
            {
                addEdge(findVertex(i+1), addAndGetVertex((Node) new Computer()), true);
            }
        }
    }
}
