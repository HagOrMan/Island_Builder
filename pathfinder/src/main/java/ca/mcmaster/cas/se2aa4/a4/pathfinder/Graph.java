package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

// node will hold index, adapter takes in vertex index and sends that to here to check against a node

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

    // Adds an edge to adjacency list and makes a new edge for it.
    public void addEdge(Node n1, Node n2, int weight){
        List<Node> adjacent = nodes.get(n1);
        adjacent.add(n2);
        Edge edge = new Edge(n1.getIndex(), n2.getIndex(), weight);
        edges.add(edge);
        weights.put(edge, weight);
    }

    // Get list of connected node indices to an input node index.
    public List<Integer> getAdjacentNodes(Node n){
        return null;
    }

    public int getWeight(Node n1, Node n2){
        for (Edge e : edges){
            if (e.equals(n1.getIndex(), n2.getIndex())){
                return weights.get(e);
            }
        }
        return 0;
    }

    // Get list of all nodes.
    public Set<Node> getNodes(){
        return nodes.keySet();
    }

}
