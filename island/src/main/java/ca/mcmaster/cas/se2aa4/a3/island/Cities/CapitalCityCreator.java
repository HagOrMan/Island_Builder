package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.GraphAdapter;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

public class CapitalCityCreator implements MyCapitalCityMaker{

    // Finds the most central city and makes that the capital.
    public void makeCapitalCity(List<MyVertex> vertices){
        GraphAdapter graphAdapter = new GraphAdapter();

        // Adds all vertices to graph.
        for (MyVertex v: vertices){
            graphAdapter.addVertexToGraph(v);
        }
    }
}
