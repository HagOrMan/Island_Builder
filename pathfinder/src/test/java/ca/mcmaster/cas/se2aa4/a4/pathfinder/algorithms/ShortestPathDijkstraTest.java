package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation.EdgeFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.NodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathDijkstraTest {

    private MyGraph g;
    private EdgeFactory edgeFactory;
    private Node n0, n1, n2, n3, n4;
    private Edge e01, e10;
    private PathFinding shortestPath;

    @BeforeEach
    public void setUp(){
        g = new Graph();
        NodeFactory nodeFactory = NodeFactory.getInstance();
        edgeFactory = EdgeFactory.getInstance();
        shortestPath = new ShortestPathDijkstra();

        n0 = nodeFactory.makeNode(0);
        n1 = nodeFactory.makeNode(1);
        n2 = nodeFactory.makeNode(2);
        n3 = nodeFactory.makeNode(3);
        n4 = nodeFactory.makeNode(4);


        e01 = edgeFactory.makeEdge(n0.getIndex(), n1.getIndex(), 5); e10 = edgeFactory.makeEdge(n1.getIndex(), n0.getIndex(), 5);

    }

    @Test
    void findPath5Nodes() {
        g = new Graph();
        edgeFactory = EdgeFactory.getInstance();

        g.addNode(n0); g.addNode(n1); g.addNode(n2); g.addNode(n3); g.addNode(n4);
        Edge e02 = edgeFactory.makeEdge(n0.getIndex(), n2.getIndex(), 2); Edge e20 = edgeFactory.makeEdge(n2.getIndex(), n0.getIndex(), 2);
        Edge e04 = edgeFactory.makeEdge(n0.getIndex(), n4.getIndex(), 6); Edge e40 = edgeFactory.makeEdge(n4.getIndex(), n0.getIndex(), 6);
        Edge e24 = edgeFactory.makeEdge(n2.getIndex(), n4.getIndex(), 1); Edge e42 = edgeFactory.makeEdge(n4.getIndex(), n2.getIndex(), 1);
        Edge e23 = edgeFactory.makeEdge(n2.getIndex(), n3.getIndex(), 1); Edge e32 = edgeFactory.makeEdge(n3.getIndex(), n2.getIndex(), 1);
        Edge e31 = edgeFactory.makeEdge(n3.getIndex(), n1.getIndex(), 1); Edge e13 = edgeFactory.makeEdge(n1.getIndex(), n3.getIndex(), 1);

        g.addEdge(e02); g.addEdge(e20);
        g.addEdge(e01); g.addEdge(e10);
        g.addEdge(e04); g.addEdge(e40);
        g.addEdge(e24); g.addEdge(e42);
        g.addEdge(e23); g.addEdge(e32);
        g.addEdge(e31); g.addEdge(e13);

        Map<Node, Node> paths = shortestPath.findPath(g, n0);
        assertEquals(n0, paths.get(n0));
        assertEquals(n3, paths.get(n1));
        assertEquals(n0, paths.get(n2));
        assertEquals(n2, paths.get(n3));
        assertEquals(n2, paths.get(n4));

        assertEquals(4, shortestPath.longestPathDistance());

    }

    @Test
    void findPath1Node() {
        g = new Graph();

        g.addNode(n0);

        Map<Node, Node> paths = shortestPath.findPath(g, n0);
        assertEquals(n0, paths.get(n0));
        assertEquals(0, shortestPath.longestPathDistance());

    }

    @Test
    void findPath2Nodes() {
        g = new Graph();

        g.addNode(n0); g.addNode(n1);
        g.addEdge(e10); g.addEdge(e01);

        Map<Node, Node> paths = shortestPath.findPath(g, n0);
        assertEquals(n0, paths.get(n0));
        assertEquals(n0, paths.get(n1));
        assertEquals(5, shortestPath.longestPathDistance());

    }

    @Test
    void findPath0Nodes()  {
        g = new Graph();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {shortestPath.findPath(g, n0);});
        String expectedMessage = "Graph must contain input source node";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        assertEquals(0, shortestPath.longestPathDistance());
    }

}