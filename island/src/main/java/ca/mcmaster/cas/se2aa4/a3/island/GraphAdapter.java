package ca.mcmaster.cas.se2aa4.a3.island;


import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.MyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.NodeCreation.NodeFactory;

public class GraphAdapter {

    private final MyGraph graph = new Graph();
    private final NodeFactory nodeFactory;

    public GraphAdapter(){
        nodeFactory = NodeFactory.getInstance();
    }

    /**
     * Adds a vertex to the graph as a node.
     * @param v Vertex to be added to graph
     */
    public void addVertexToGraph(MyVertex v){
        nodeFactory.makeNode(v.getIndex());
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
