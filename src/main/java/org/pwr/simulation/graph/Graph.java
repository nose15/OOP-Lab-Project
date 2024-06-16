package org.pwr.simulation.graph;

import java.security.PublicKey;
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

    public Map<Node, List<Node>> getMap() {
        return this.map;
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
        throw new IllegalArgumentException("Vertex not found");
    }

    static public Node findVertex(Map<Node, List<Node>> map, int id)
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
            //System.out.println(v + " -> " + map.get(v));
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

    public void printHashMap() {
        System.out.println(map);
    }

    public void graphGeneratorTest()
    {
        int numberOfSwitch = 4;
        int numberOfComputers = 20;
        addVertex(new Router());

        for(int i = 0; i<numberOfSwitch; i++)
        {
            addEdge(findVertex(0), addAndGetVertex(new Switch()), true);
            for(int j = 0; j<numberOfComputers/numberOfSwitch; j++)
            {
                addEdge(findVertex(i+1), addAndGetVertex(new Computer()), true);
            }
        }
    }
    public void graphGeneratorSimple(int numberOfSwitch, int numberOfComputer)
    {
        if(numberOfSwitch < 1 || numberOfComputer < 1)
            throw new IllegalArgumentException("Number of switches and computers must be greater than 0");
        if(numberOfSwitch > numberOfComputer)
            throw new IllegalArgumentException("Number of switches must be less than number of computers");

        addVertex((Node) new Router());
        Random random = new Random();

        for(int i = 0; i<numberOfSwitch; i++)
        {
            int take = random.nextInt((Switch.getCounter()-0)+1)+0;
            addEdge(findVertex(take), addAndGetVertex(new Switch()), true);
            addEdge(findVertex(Switch.getCounter()), addAndGetVertex(new Computer()), true);

            if (findVertex(take) instanceof Router)
                ((Router) findVertex(take)).addSwitch(findVertex(Switch.getCounter()));
            else //if to switch
                ((Switch) findVertex(take)).addSwitch(findVertex(Switch.getCounter()));

            ((Switch) findVertex(Switch.getCounter())).setParent(findVertex(take));
            ((Switch) findVertex(Switch.getCounter())).addComputer(findVertex(Computer.getCounter()));
            ((Computer) findVertex(Computer.getCounter())).setParent(findVertex(Switch.getCounter()));
        }
        for(int i = 0; i<numberOfComputer-numberOfSwitch; i++)
        {
            int take = random.nextInt((Switch.getCounter()-1)+1)+1;
            addEdge(findVertex(take), addAndGetVertex(new Computer()), true);

            ((Switch) findVertex(take)).addComputer(findVertex(Computer.getCounter()));
            ((Computer) findVertex(Computer.getCounter())).setParent(findVertex(take));
        }
    }
    public void graphGeneratorAdvance(int numberOfSwitch, int numberOfComputer, float switchConnectivity, float computerConsistancy)
    {
        if(numberOfSwitch < 1 || numberOfComputer < 1)
            throw new IllegalArgumentException("Number of switches and computers must be greater than 0");
        if(numberOfSwitch > numberOfComputer)
            throw new IllegalArgumentException("Number of switches must be less than number of computers");

        addVertex((Node) new Router());
        Random random = new Random();

        List<Switch> endSwitches = new ArrayList<>();

        //Switch generation
        // First: It take random number from 0 to number of switches
        // and then add new switch to the graph and connect it to the taken switch/router

        // Second: It take random number from 0 to number of computers witch means number of connection betwen this switch and others
        // and then by loop of conectivity if random float is less than switchConnectivity it connect to another switch
        // it take random switch and if its the same switch or already connected it take another one it get skipped

        // Third: If switch has no connections it add it to the endSwitches list
        // to add computer to it later
        for(int i = 0; i< numberOfSwitch; i++)
        {
            int take = random.nextInt((Switch.getCounter()-0)+1)+0;
            addEdge(findVertex(take), addAndGetVertex((Node) new Switch()), true);

            if(findVertex(take) instanceof Router)
                ((Router) findVertex(take)).addSwitch(findVertex(Switch.getCounter()));
            else //For switch connection
                ((Switch) findVertex(take)).addSwitch(findVertex(Switch.getCounter()));


            ((Switch) findVertex(Switch.getCounter())).setParent(findVertex(take));

            //Number of other connection to switches
            int conectivity = random.nextInt((Switch.getCounter()-1)+1)+1;

            for(int j = 0; j<conectivity; j++)
            {
                if(random.nextFloat((1.f-0.01f)+1.f)+0.01f <= switchConnectivity)
                {
                    int chooseSwitch = random.nextInt((Switch.getCounter()-1)+1)+1;
                    if(findVertex(take) != findVertex(chooseSwitch) && !((Switch) findVertex(take)).getSwitches().contains(findVertex(chooseSwitch)))
                    {
                        addEdge(findVertex(take), findVertex(chooseSwitch), true);
                        ((Switch) findVertex(chooseSwitch)).addSwitch(findVertex(take));
                        ((Switch) findVertex(take)).addSwitch(findVertex(chooseSwitch));
                    }
                }
            }

            if(((Switch) findVertex(take)).getSwitches().isEmpty())
                endSwitches.add((Switch) findVertex(take));
        }

        for(int i = 0; i<endSwitches.size(); i++)
        {
            if(!endSwitches.get(i).getSwitches().isEmpty())
            {
                endSwitches.remove(i);
                i--;
                continue;
            }

            addEdge(endSwitches.get(i), addAndGetVertex((Node) new Computer()), true);
        }

        for(int i = 0; i<numberOfComputer-endSwitches.size(); i++)
        {
            int take = random.nextInt((Switch.getCounter()-1)+1)+1;

            float chance = (random.nextFloat((1.f-0.01f)+1.f)+0.01f)/((Switch) findVertex(take)).getComputers().size();

            if(chance <= computerConsistancy || ((Switch) findVertex(take)).getComputers().isEmpty())
            {
                addEdge(findVertex(take), addAndGetVertex((Node) new Computer()), true);
                ((Switch) findVertex(take)).addComputer(findVertex(Computer.getCounter()));
                ((Computer) findVertex(Computer.getCounter())).setParent(findVertex(take));
            }
            else
            {
                i--;
            }
        }
    }

}
