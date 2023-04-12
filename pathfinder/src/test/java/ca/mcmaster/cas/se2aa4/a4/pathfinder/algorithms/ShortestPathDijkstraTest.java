package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory.NodeFactory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathDijkstraTest {

    @Test
    void findPath5Nodes() {
        Graph g = new Graph();
        NodeFactory nodeFactory = new NodeFactory();

        Node n0 = nodeFactory.makeNode(0);
        Node n1 = nodeFactory.makeNode(1);
        Node n2 = nodeFactory.makeNode(2);
        Node n3 = nodeFactory.makeNode(3);
        Node n4 = nodeFactory.makeNode(4);

        g.addNode(n0); g.addNode(n1); g.addNode(n2); g.addNode(n3); g.addNode(n4);
        Edge e02 = new Edge(n0.getIndex(), n2.getIndex(), 2); Edge e20 = new Edge(n2.getIndex(), n0.getIndex(), 2);
        Edge e01 = new Edge(n0.getIndex(), n1.getIndex(), 5); Edge e10 = new Edge(n1.getIndex(), n0.getIndex(), 5);
        Edge e04 = new Edge(n0.getIndex(), n4.getIndex(), 6); Edge e40 = new Edge(n4.getIndex(), n0.getIndex(), 6);
        Edge e24 = new Edge(n2.getIndex(), n4.getIndex(), 1); Edge e42 = new Edge(n4.getIndex(), n2.getIndex(), 1);
        Edge e23 = new Edge(n2.getIndex(), n3.getIndex(), 1); Edge e32 = new Edge(n3.getIndex(), n2.getIndex(), 1);
        Edge e31 = new Edge(n3.getIndex(), n1.getIndex(), 1); Edge e13 = new Edge(n1.getIndex(), n3.getIndex(), 1);

        g.addEdge(e02); g.addEdge(e20);
        g.addEdge(e01); g.addEdge(e10);
        g.addEdge(e04); g.addEdge(e40);
        g.addEdge(e24); g.addEdge(e42);
        g.addEdge(e23); g.addEdge(e32);
        g.addEdge(e31); g.addEdge(e13);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(n0, paths.get(n0));
        assertEquals(n3, paths.get(n1));
        assertEquals(n0, paths.get(n2));
        assertEquals(n2, paths.get(n3));
        assertEquals(n2, paths.get(n4));

    }

    @Test
    void findPath1Node() {
        Graph g = new Graph();
        NodeFactory nodeFactory = new NodeFactory();

        Node n0 = nodeFactory.makeNode(0);

        g.addNode(n0);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(n0, paths.get(n0));

    }

    @Test
    void findPath2Nodes() {
        Graph g = new Graph();
        NodeFactory nodeFactory = new NodeFactory();

        Node n0 = nodeFactory.makeNode(0);
        Node n1 = nodeFactory.makeNode(1);

        g.addNode(n0); g.addNode(n1);
        Edge e1 = new Edge(n0.getIndex(), n1.getIndex(), 5);
        Edge e2 = new Edge(n1.getIndex(), n0.getIndex(), 5);
        g.addEdge(e1); g.addEdge(e2);

        Map<Node, Node> paths = new ShortestPathDijkstra().findPath(g, n0);
        assertEquals(n0, paths.get(n0));
        assertEquals(n0, paths.get(n1));

    }

}