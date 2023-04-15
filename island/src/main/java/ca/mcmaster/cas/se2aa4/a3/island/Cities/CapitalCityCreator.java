package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.GraphAdapter;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

public class CapitalCityCreator implements MyCapitalCityMaker{

    // Finds the most central city and makes that the capital.
    public MyVertex makeCapitalCity(List<MyVertex> vertices, List<MyPolygon> polygons, IslandShape islandShape, GraphAdapter graphAdapter){

        graphAdapter.addVerticesToGraph(vertices);
        graphAdapter.addEdgesToGraph(polygons);

        MyVertex capital = graphAdapter.findCapital(vertices, islandShape.getCenter());
        new CentroidToCity().changeCity(capital, CityOption.CAPITAL);

        return capital;
    }
}
