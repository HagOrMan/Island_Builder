package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

public interface MyCityPainter {
    void addCitiesToIsland(List<MyPolygon> polygons, int numCities, List<MyVertex> vertices);
}
