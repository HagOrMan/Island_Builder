package ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;

import java.util.*;

public class ShortestPathDijkstra implements PathFinding{

    private Map<Node, Integer> cost = new HashMap<>();
    private Map<Node, Integer[]> costDist = new HashMap<>();

    // Gets longest path out of the shortest paths found for this source node.
    public int longestPathDistance(){
        int c;
        int longestPath = 0;
        for (Node key : cost.keySet()){
            c = cost.get(key);
            longestPath = Math.max(longestPath, c);
        }
        return longestPath;
    }

    // Gets longest path to the set of given nodes.
    public int longestPathGivenNodes(Set<Node> nodes){
        int c;
        int longestPath = 0;
        for (Node key : nodes){
            c = cost.get(key);
            longestPath = Math.max(longestPath, c);
        }
        return longestPath;
    }

    @Override
    public Map<Node, Node> findPath(MyGraph graph, Node source) {

        testGraphContainsSource(graph, source);

        // Creates map for path and sets each value to null except the source node path is itself.
        Map<Node, Node> path = new HashMap<>();
        fillPathKeys(path, graph.getNodes());
        path.replace(source, source);

        // Creates cost map and fills with infinity, except source cost is 0.
        cost = new HashMap<>();
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

    @Override
    public Map<Node, Node> findPath(MyGraph graph, Node source, int maxEdges) {
        testGraphContainsSource(graph, source);

        // Creates map for path and sets each value to null except the source node path is itself.
        Map<Node, Node> path = new HashMap<>();
        fillPathKeys(path, graph.getNodes());
        path.replace(source, source);

        // Creates cost map and fills with infinity, except source cost is 0.
        costDist = new HashMap<>();
        fillCostDistKeys(graph.getNodes());
        costDist.replace(source, new Integer[]{0, 0});

        cost = new HashMap<>();
        fillCostKeys(cost, graph.getNodes());
        cost.replace(source, 0);

        // Initializes priority queue and adds source to queue.
        Queue<Map.Entry<Node, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        queue.add(new AbstractMap.SimpleEntry<>(source, 0));

        // Goes through priority as per dijkstra's algorithm.
        while (!queue.isEmpty()){
            Node n = queue.remove().getKey();

            for (Node other : graph.getAdjacentNodes(n)){
                int nCost = costDist.get(n)[0];
                int otherCost = costDist.get(other)[0];
                int weight = graph.getWeight(n, other);
                int newDist = costDist.get(n)[1] + 1;

                // Checks if current node to node being checked gives it a lower cost.
                if (nCost + weight < otherCost && newDist < maxEdges){
                    path.replace(other, n);
                    costDist.replace(other, new Integer[]{nCost + weight, newDist});
                    cost.replace(other, nCost + weight);
                    // Updates other in priority queue such that other has priority cost of its new cost.
                    updateQueue(queue, other, cost);
                }

            }

        }

        return path;
    }

    private void testGraphContainsSource(MyGraph graph, Node source){
        Set<Node> nodes = graph.getNodes();
        if (!nodes.contains(source)){
            throw new IllegalArgumentException("Graph must contain input source node");
        }
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

    private void fillCostDistKeys(Set<Node> nodes){
        for (Node n : nodes){
            costDist.put(n, new Integer[]{Integer.MAX_VALUE, 0});
        }
    }

}
