package ca.mcmaster.cas.se2aa4.a3.island.Roads;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

public interface MyRoadPainter {
    void buildRoads(List<MySegment> segments, List<MyVertex> vertices);
}
