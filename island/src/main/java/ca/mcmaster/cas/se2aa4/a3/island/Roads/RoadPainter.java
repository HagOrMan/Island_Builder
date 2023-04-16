package ca.mcmaster.cas.se2aa4.a3.island.Roads;

import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityOption;
import ca.mcmaster.cas.se2aa4.a3.island.GraphAdapter;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.algorithms.ShortestPathDijkstra;

import java.util.ArrayList;
import java.util.List;

public class RoadPainter implements MyRoadPainter{

    // Uses graph adapter to get list of new segments needed, and makes them into roads and adds them to segments list.
    public void buildRoads(List<MySegment> segments, List<MyVertex> vertices, GraphAdapter graphAdapter, MyVertex source){
        List<MySegment> createdRoads = new ArrayList<>();

        // Creates main connections from capital to all other cities/villages/hamlets.
        for (MySegment s: graphAdapter.getRoadsNeeded(vertices, source, new ShortestPathDijkstra(), createdRoads)){
            s.setThick(3);
            s.changeColor("50,50,50");
            segments.add(s);
            createdRoads.add(s);
        }
        // Adds connections between cities and cities/villages.
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.CITY){
                continue;
            }
            // Makes city to city connections with a max distance of 15.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    15, CityOption.CITY)){
                s.setThick(2.2f);
                s.changeColor("100,50,50");
                segments.add(s);
                createdRoads.add(s);
            }
            // Makes city to village connections with max distance of 15.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    15, CityOption.VILLAGE)){
                s.setThick(2);
                s.changeColor("100,100,50");
                segments.add(s);
                createdRoads.add(s);
            }
//            for (MySegment s: graphAdapter.getRoadsNeededSecondary(vertices, v, new ShortestPathDijkstra(), createdRoads)){
//                s.setThick(2);
//                s.changeColor("100,100,50");
//                segments.add(s);
//                createdRoads.add(s);
//            }
        }
        // Adds connections between villages and villages/hamlets.
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.VILLAGE){
                continue;
            }
            // Makes village to village connections with a max distance of 8.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    8, CityOption.VILLAGE)){
                s.setThick(1.5f);
                s.changeColor("150,125,100");
                segments.add(s);
                createdRoads.add(s);
            }
            // Makes village to hamlet connections with max distance of 8.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    8, CityOption.HAMLET)){
                s.setThick(1.3f);
                s.changeColor("150,150,150");
                segments.add(s);
                createdRoads.add(s);
            }
        }
        // Adds connections between hamlets and hamlets.
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.HAMLET){
                continue;
            }
            // Makes hamlet to hamlet connections with a max distance of 5.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    6, CityOption.HAMLET)){
                s.setThick(1);
                s.changeColor("175,175,150");
                segments.add(s);
                createdRoads.add(s);
            }
        }
    }
}
