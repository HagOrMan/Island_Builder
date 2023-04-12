package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory.Node;

import java.util.*;

// node will hold index, adapter takes in vertex index and sends that to here to check against a node

public class Graph {

    private Map<Node, List<Node>> nodes;
    private List<Edge> edges;
    private Map<Edge, Integer> weights;

    public Graph(){
        nodes = new Hashtable<>();
        edges = new ArrayList<>();
        weights = new HashMap<>();
    }

    public void addNode(Node n){
        if (!nodes.containsKey(n)){
            nodes.put(n, new ArrayList<>());
        }
    }

    // Adds an edge to adjacency list and makes a new edge for it.
    public void addEdge(Edge edge){
        Node n1 = getNode(edge.getN1Idx());
        Node n2 = getNode(edge.getN2Idx());
        List<Node> adjacent = nodes.get(n1);
        adjacent.add(n2);
        edges.add(edge);
        weights.put(edge, edge.getWeight());
    }

    // Get list of connected node indices to an input node index.
    public List<Node> getAdjacentNodes(Node n){
        return nodes.get(n);
    }

    public int getWeight(Node n1, Node n2){
        for (Edge e : edges){
            if (e.equals(n1.getIndex(), n2.getIndex())){
                return e.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }

    // Get list of all nodes.
    public Set<Node> getNodes(){
        return nodes.keySet();
    }

    public List<Edge> getEdges() { return edges; }

    // Gets node from index.
    private Node getNode(int index){
        for (Node n : getNodes()){
            if (n.equals(index)){
                return n;
            }
        }
        return null;
    }

}
