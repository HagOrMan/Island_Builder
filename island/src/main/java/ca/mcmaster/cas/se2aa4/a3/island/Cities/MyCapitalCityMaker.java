package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.GraphAdapter;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.PathFinding;

import java.util.List;

public interface MyCapitalCityMaker {
    MyVertex makeCapitalCity(List<MyVertex> vertices, List<MyPolygon> polygons, PathFinding pathfinder, GraphAdapter graphAdapter);
}
