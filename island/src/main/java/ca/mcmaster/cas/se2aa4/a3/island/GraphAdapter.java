package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.*;

public class GraphAdapter {

    private final Graph graph = new Graph();

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
