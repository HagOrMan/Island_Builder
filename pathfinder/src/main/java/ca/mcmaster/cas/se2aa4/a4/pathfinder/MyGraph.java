package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;

import java.util.List;
import java.util.Set;

public interface MyGraph {
    void addNode(Node n);
    void addEdge(Edge edge);
    List<Node> getAdjacentNodes(Node n);
    int getWeight(Node n1, Node n2);
    Set<Node> getNodes();
    List<Edge> getEdges();
}
