package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory.Node;

import java.util.*;

public class ShortestPathDijkstra implements PathFinding{
    @Override
    public Map<Node, Node> findPath(Graph graph, Node source) {

        // Creates map for path and sets each value to null except the source node path is itself.
        Map<Node, Node> path = new HashMap<>();
        fillPathKeys(path, graph.getNodes());
        path.replace(source, source);

        // Creates cost map and fills with infinity, except source cost is 0.
        Map<Node, Integer> cost = new HashMap<>();
        fillCostKeys(cost, graph.getNodes());
        cost.replace(source, 0);

        // Initializes priority queue and adds source to queue.
        Queue<Map.Entry<Node, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        queue.add(new AbstractMap.SimpleEntry<>(source, 0));

        // Goes through priority as per dijkstra's algorithm.
        while (!queue.isEmpty()){
            Node n = queue.remove().getKey();

            for (Node other : graph.getAdjacentNodes(n)){
                int nCost = cost.get(n);
                int otherCost = cost.get(other);
                int weight = graph.getWeight(n, other);

                // Checks if current node to node being checked gives it a lower cost.
                if (nCost + weight < otherCost){
                    path.replace(other, n);
                    cost.replace(other, nCost + weight);
                    // Updates other in priority queue such that other has priority cost of its new cost.
                    updateQueue(queue, other, cost);
                }

            }

        }

        return path;
    }

    // Updates the queue for a new node, first checking if it already exists and removing it if so.
    private void updateQueue(Queue<Map.Entry<Node, Integer>> queue, Node key, Map<Node, Integer> cost){
        queue.removeIf(entry -> entry.getKey() == key);
        queue.add(new AbstractMap.SimpleEntry<>(key, cost.get(key)));
    }

    // Fills path map with empty values for path to each node.
    private void fillPathKeys(Map<Node, Node> path, Set<Node> nodes){
        for (Node n : nodes){
            path.put(n, null);
        }
    }
    // Fills cost map with max cost for each node.
    private void fillCostKeys(Map<Node, Integer> path, Set<Node> nodes){
        for (Node n : nodes){
            path.put(n, Integer.MAX_VALUE);
        }
    }
}
