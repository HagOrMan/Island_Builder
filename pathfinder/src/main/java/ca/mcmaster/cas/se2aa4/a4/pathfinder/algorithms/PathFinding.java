package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory.Node;

import java.util.Map;

public interface PathFinding {

    Map<Node, Node> findPath(Graph graph, Node start);

}
