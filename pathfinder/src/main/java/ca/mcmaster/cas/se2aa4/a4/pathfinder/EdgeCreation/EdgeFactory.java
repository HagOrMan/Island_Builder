package ca.mcmaster.cas.se2aa4.a4.pathfinder.EdgeCreation;

import java.util.ArrayList;
import java.util.List;

public class EdgeFactory {


    private final List<Edge> createdEdges;
    private static EdgeFactory uniqueInstance = null;

    private EdgeFactory(){
        createdEdges = new ArrayList<>();
    }

    public static EdgeFactory getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new EdgeFactory();
        }
        return uniqueInstance;
    }

    /**
     * Makes an edge based on specified parameters, either returning an existing one or making a new one.
     * @param n1Idx source node index of edge
     * @param n2Idx destination node index of edge
     * @param weight weight of edge
     * @return Edge if it already exists, or new edge with these properties if it does not exist.
     */
    public Edge makeEdge(int n1Idx, int n2Idx, int weight){

        // Makes edge with given properties as a baseline.
        Edge edge = null;

        // Goes through list and checks if these properties already exist in an existing edge.
        for (Edge e : createdEdges){
            if (e.getN1Idx() == n1Idx && e.getN2Idx() == n2Idx && e.getWeight() == weight){
                edge = e;
                break;
            }
        }

        if (edge == null){
            edge = new Edge(n1Idx, n2Idx, weight);
        }

        createdEdges.add(edge);
        return edge;

    }

}
