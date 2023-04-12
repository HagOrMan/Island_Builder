package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Node;

import java.util.Map;

public interface PathFinding {

    public Map<Node, Node> findPath(Graph graph, Node start);

}
