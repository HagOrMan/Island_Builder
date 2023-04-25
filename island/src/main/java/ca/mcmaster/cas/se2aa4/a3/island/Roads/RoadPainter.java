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
            changeSegment(3, "50,50,50", s);
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
                changeSegment(2.2f, "100,50,50", s);
                segments.add(s);
                createdRoads.add(s);
            }
            // Makes city to village connections with max distance of 15.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    15, CityOption.VILLAGE)){
                changeSegment(2, "100,100,50", s);
                segments.add(s);
                createdRoads.add(s);
            }
        }
        // Adds connections between villages and villages/hamlets.
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.VILLAGE){
                continue;
            }
            // Makes village to village connections with a max distance of 8.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    8, CityOption.VILLAGE)){
                changeSegment(1.5f, "150,125,100", s);
                segments.add(s);
                createdRoads.add(s);
            }
            // Makes village to hamlet connections with max distance of 8.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    8, CityOption.HAMLET)){
                changeSegment(1.3f, "150,150,150", s);
                segments.add(s);
                createdRoads.add(s);
            }
        }
        // Adds connections between hamlets and hamlets.
        for (MyVertex v : vertices){
            if (v.getCityType() != CityOption.HAMLET){
                continue;
            }
            // Makes hamlet to hamlet connections with a max distance of 6.
            for (MySegment s: graphAdapter.getRoadsNeeded(vertices, v, new ShortestPathDijkstra(), createdRoads,
                    6, CityOption.HAMLET)){
                changeSegment(0.8f, "200,200,150", s);
                segments.add(s);
                createdRoads.add(s);
            }
        }
    }

    private void changeSegment(float thickness, String colorCode, MySegment s){
        s.setThick(thickness);
        s.changeColor(colorCode);
    }

}
