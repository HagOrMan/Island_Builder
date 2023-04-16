package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;

import java.util.Map;
import java.util.Set;

public interface PathFinding {

    Map<Node, Node> findPath(MyGraph graph, Node start);
    int longestPathDistance();
    int longestPathGivenNodes(Set<Node> nodes);
}
