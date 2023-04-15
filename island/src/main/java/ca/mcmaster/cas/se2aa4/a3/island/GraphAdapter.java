package ca.mcmaster.cas.se2aa4.a3.island;


import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;

public class GraphAdapter {

    private final MyGraph graph = new Graph();

    // Gets a node from a specified index.
    private Node nodeFromIndex(int index){
        for (Node n: graph.getNodes()){
            if (n.equals(index)){
                return n;
            }
        }
        return null;
    }
}
