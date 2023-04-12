package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathDijkstraTest {

    @Test
    void findPath5Nodes() {
        Graph g = new Graph();

        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        g.addNode(n0); g.addNode(n1); g.addNode(n2); g.addNode(n3); g.addNode(n4);
        g.addEdge(n0, n2, 2); g.addEdge(n2, n0, 2);
        g.addEdge(n0, n1, 5); g.addEdge(n1, n0, 5);
        g.addEdge(n0, n4, 6); g.addEdge(n4, n0, 6);
        g.addEdge(n2, n4, 1); g.addEdge(n4, n2, 1);
        g.addEdge(n2, n3, 1); g.addEdge(n3, n2, 1);
        g.addEdge(n3, n1, 1); g.addEdge(n1, n3, 1);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(paths.get(n0), n0);
        assertEquals(paths.get(n1), n3);
        assertEquals(paths.get(n2), n0);
        assertEquals(paths.get(n3), n2);
        assertEquals(paths.get(n4), n2);

    }

    @Test
    void findPath1Node() {
        Graph g = new Graph();

        Node n0 = new Node(0);

        g.addNode(n0);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(paths.get(n0), n0);

    }

    @Test
    void findPath2Nodes() {
        Graph g = new Graph();

        Node n0 = new Node(0);
        Node n1 = new Node(1);

        g.addNode(n0); g.addNode(n1);
        g.addEdge(n0, n1, 5); g.addEdge(n1, n0, 5);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(paths.get(n0), n0);
        assertEquals(paths.get(n1), n0);

    }

}