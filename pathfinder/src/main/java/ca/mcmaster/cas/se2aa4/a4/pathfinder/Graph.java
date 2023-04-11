package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<Node, List<Node>> nodes;
    private List<Edge> edges;
    private Map<Edge, Integer> weights;

    public Graph(){
        nodes = new Hashtable<>();
        edges = new ArrayList<>();
        weights = new Hashtable<>();
    }

    public void addNode(Node n){
        if (!nodes.containsKey(n)){
            nodes.put(n, new ArrayList<>());
        }
    }
    

    // add edge
    // add node
    // get edge weight from inputting 2 node indices
    // get list of connected nodes to an input node

}
