package ca.mcmaster.cas.se2aa4.a3.island;


import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation.EdgeFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.NodeFactory;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.PathFinding;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

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
                    break;
                }

                int v1Idx = p.getCentroidIdx(), v2Idx = other.getCentroidIdx();
                int distance = (int) p.getCenterOfPolygon().distance(other.getCenterOfPolygon());
                graph.addEdge(edgeFactory.makeEdge(v1Idx, v2Idx, distance));
            }

        }
    }

    public MyVertex findCapital(List<MyVertex> vertices, Point p){
        List<MyVertex> cityVertices = findCityVertices(vertices);
        int lowestDistance = Integer.MAX_VALUE;
        MyVertex capital = null;
        int distance;
        Point vertex;

        for (MyVertex v: cityVertices){
            vertex = new GeometryFactory().createPoint(new Coordinate(v.getX(), v.getY()));
            distance = (int) vertex.distance(p);

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

    // Gets a node from a specified index.
    private Node nodeFromIndex(int index){
        for (Node n: graph.getNodes()){
            if (n.equals(index)){
                return n;
            }
        }
        return null;
    }

}
