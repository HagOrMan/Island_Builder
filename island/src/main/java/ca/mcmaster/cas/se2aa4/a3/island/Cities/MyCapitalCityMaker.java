package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

public interface MyCapitalCityMaker {
    MyVertex makeCapitalCity(List<MyVertex> vertices, List<MyPolygon> polygons, IslandShape islandShape);
}
