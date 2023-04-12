package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private List<Node> addNodesToGraph(Graph g){
        Node n1 = new Node(0);
        Node n2 = new Node(1);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        Node n5 = new Node(4);

        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);

        List<Node> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        return nodes;
    }

    // Tests that nodes which are adjacent are added properly to graph.
    @Test
    void getAdjacentNodes() {

        Graph g = new Graph();
        Node n1 = new Node(0);
        Node n2 = new Node(1);
        Node n3 = new Node(2);

        g.addNode(n1); g.addNode(n2); g.addNode(n3);
        Edge e1 = new Edge(n1.getIndex(), n2.getIndex(), 5);
        Edge e2 = new Edge(n1.getIndex(), n3.getIndex(), 5);
        g.addEdge(e1); g.addEdge(e2);

        // Checks adjacency of n1.
        List<Node> adjacent = g.getAdjacentNodes(n1);
        assertFalse(adjacent.contains(n1));
        assertTrue(adjacent.contains(n2));
        assertTrue(adjacent.contains(n3));

        // Checks adjacency of n2.
        adjacent = g.getAdjacentNodes(n2);
        assertFalse(adjacent.contains(n1));
        assertFalse(adjacent.contains(n2));
        assertFalse(adjacent.contains(n3));

        assertTrue(adjacent.isEmpty());



    }

    // Tests that weights are added properly to graph.
    @Test
    void getWeight() {

        Graph g = new Graph();
        Node n1 = new Node(0);
        Node n2 = new Node(1);
        g.addNode(n1); g.addNode(n2);
        Edge e1 = new Edge(n1.getIndex(), n2.getIndex(), 5);
        g.addEdge(e1);

        List<Edge> edges = g.getEdges();
        assertEquals(5, edges.get(0).getWeight());

    }

    // Tests that nodes can be added to the graph properly.
    @Test
    void getNodes() {
        Graph g = new Graph();
        List<Node> nodes = addNodesToGraph(g);
        Set<Node> graphNodes = g.getNodes();

        for (Node n : nodes){
            assertTrue(graphNodes.contains(n));
        }
    }

    // Tests that edges can be added to the graph properly.
    @Test
    void getEdges() {
        Graph g = new Graph();
        List<Node> nodes = addNodesToGraph(g);
        List<Edge> edges = new ArrayList<>();

        // Adds edges in a straight line with weights equal to the order they were added in.
        for (int i = 1; i < nodes.size(); i++){
            Node n1 = nodes.get(i - 1);
            Node n2 = nodes.get(i);
            Edge e1 = new Edge(n1.getIndex(), n2.getIndex(), i);
            edges.add(e1);
            g.addEdge(e1);
        }

        // Tests to make sure each edge in edges has an edge in graph with same nodes in it.
        boolean found;
        for (Edge e : edges){
            found = false;
            for (Edge other : g.getEdges()){
                if (e.equals(other.getN1Idx(), other.getN2Idx())){
                    found = true;
                }
            }
            assertTrue(found);
        }

    }

}