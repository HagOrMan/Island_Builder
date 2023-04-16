package ca.mcmaster.cas.se2aa4.a3.island.Roads;

import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityOption;
import ca.mcmaster.cas.se2aa4.a3.island.GraphAdapter;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.ShortestPathDijkstra;

import java.util.List;

public class RoadPainter implements MyRoadPainter{

    // Uses graph adapter to get list of new segments needed, and makes them into roads and adds them to segments list.
    public void buildRoads(List<MySegment> segments, List<MyVertex> vertices, GraphAdapter graphAdapter, MyVertex source){
        for (MySegment s: graphAdapter.getRoadsNeeded(vertices, source, new ShortestPathDijkstra())){
            s.setThick(3);
            s.changeColor("50,50,50");
            segments.add(s);
        }
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.CITY){
                continue;
            }
            for (MySegment s: graphAdapter.getRoadsNeededSecondary(vertices, v, new ShortestPathDijkstra())){
                s.setThick(2);
                s.changeColor("100,100,50");
                segments.add(s);
            }
        }
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.VILLAGE){
                continue;
            }
            for (MySegment s: graphAdapter.getRoadsNeededTertiary(vertices, v, new ShortestPathDijkstra())){
                s.setThick(1);
                s.changeColor("150,150,150");
                segments.add(s);
            }
        }
    }
}
