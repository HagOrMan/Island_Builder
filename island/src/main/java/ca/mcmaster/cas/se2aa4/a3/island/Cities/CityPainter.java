package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.locationtech.jts.geom.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CityPainter {

    // Take in polygons within island and number of cities to make, and randomly makes vertices in island as cities.
    public void addCitiesToIsland(List<MyPolygon> polygons, int numCities, List<MyVertex> vertices){
        Set<Integer> polygonsWithIndices = new HashSet<>();
        Random rand = new Random();

        int index = -1;
        // Adds cities, and makes sure the polygon doesn't already contain a city before adding it.
        for (int i = 0; i < numCities; i++){
            do {
                index = rand.nextInt(polygons.size());
            } while (polygonsWithIndices.contains(index));
            polygonsWithIndices.add(index);
        }
    }

    private void addVertexAsCity(List<MyVertex> vertices, Point p){
        
    }

}
