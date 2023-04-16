package ca.mcmaster.cas.se2aa4.a3.island;


import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation.EdgeFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.NodeFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.PathFinding;

import java.util.*;

public class GraphAdapter {

    private final MyGraph graph = new Graph();
    private final NodeFactory nodeFactory;
    private final EdgeFactory edgeFactory;

    public GraphAdapter(){
        nodeFactory = NodeFactory.getInstance();
        edgeFactory = EdgeFactory.getInstance();
    }

    /**
     * Adds a vertex to the graph as a node.
     * @param v Vertex to be added to graph
     */
    private void addVertexToGraph(MyVertex v){
        Node n = nodeFactory.makeNode(v.getIndex());
        graph.addNode(n);
    }

    public void addVerticesToGraph(List<MyVertex> vertices){
        // Adds all vertices to graph.
        for (MyVertex v: vertices){
            addVertexToGraph(v);
        }
    }

    // Goes through neighbouring polygons and adds edges for each.
    public void addEdgesToGraph(List<MyPolygon> polygons){
        for (MyPolygon p : polygons){

            for (MyPolygon other : p.getNeighbours()){

                // Ensures we do not add edges to outside of graph.
                if (p.isWaterTile() || !p.isIsland() || other.isWaterTile() || !other.isIsland()){
                    continue;
                }

                int v1Idx = p.getCentroidIdx(), v2Idx = other.getCentroidIdx();
                int distance = (int) p.getCenterOfPolygon().distance(other.getCenterOfPolygon());
                graph.addEdge(edgeFactory.makeEdge(v1Idx, v2Idx, distance));
            }

        }
    }

    // Finds the capital city based on which vertex is most central.
    public MyVertex findCapital(List<MyVertex> vertices, PathFinding pathfinder){

        // Gets list of all city vertices, and then converts to node set.
        List<MyVertex> cityVertices = findCityVertices(vertices);
        Set<Node> cityNodes = new HashSet<>(cityVerticesToNodes(cityVertices));

        int lowestDistance = Integer.MAX_VALUE;
        MyVertex capital = null;
        int distance;

        // Goes through each city vertex and finds the one which is most central (has shortest longest path).
        for (MyVertex v: cityVertices){
            pathfinder.findPath(graph, nodeFromIndex(v.getIndex()));
            distance = pathfinder.longestPathGivenNodes(cityNodes);

            if (distance < lowestDistance){
                lowestDistance = distance;
                capital = v;
            }
        }

        return capital;
    }

    private List<MyVertex> findCityVertices(List<MyVertex> vertices){
        List<MyVertex> cityVertices = new ArrayList<>();
        for (MyVertex v : vertices){
            if (v.isCity()){
                cityVertices.add(v);
            }
        }
        return cityVertices;
    }

    private Set<Node> cityVerticesToNodes(List<MyVertex> vertices){
        Set<Node> nodes = new HashSet<>();
        for (MyVertex v: vertices){
            nodes.add(nodeFromIndex(v.getIndex()));
        }
        return nodes;
    }

    // Gets a node from a specified index.
    private Node nodeFromIndex(int index){
        for (Node n: graph.getNodes()){
            if (n.equals(index)){
                return n;
            }
        }
        return null;
    }

    // Returns list of new segments that represent roads between capital and cities.
    public List<MySegment> getRoadsNeeded(List<MyVertex> vertices, MyVertex source, PathFinding pathfinder){
        List<MySegment> roads = new ArrayList<>();

        // Gets map of node connections from shortest path algorithm.
        Map<Node, Node> nodeMap = pathfinder.findPath(graph, nodeFromIndex(source.getIndex()));

        // For each city vertex, backtracks from shortest path to create segments.
        for (MyVertex v : findCityVertices(vertices)){
            backtrackFromCity(roads, nodeMap, nodeFromIndex(v.getIndex()), vertices);
        }

        return roads;
    }

    // Goes backwards from a given start node until it reaches to source node.
    private void backtrackFromCity(List<MySegment> roads, Map<Node, Node> nodeMap, Node start, List<MyVertex> vertices){
        Node current = start;
        Node previous = nodeMap.get(start);
        MyVertex v1, v2;

        // Goes through map for given start node until we reach the capital.
        while (previous != current && previous != null){
            v1 = vertices.get(current.getIndex());
            v2 = vertices.get(previous.getIndex());

            // Ensures segment does not already exist before creating it.
            if (segmentDoesNotExist(roads, v1, v2)){
                roads.add(new MySegment(v1, v2));
            }

            // Moves down the chain of nodes.
            current = previous;
            previous = nodeMap.get(current);

        }
    }

    private boolean segmentDoesNotExist(List<MySegment> segments, MyVertex v1, MyVertex v2){
        for (MySegment s : segments){
            if (s.equals(v1.getIndex(), v2.getIndex())){
                return false;
            }
        }
        return true;
    }

}
