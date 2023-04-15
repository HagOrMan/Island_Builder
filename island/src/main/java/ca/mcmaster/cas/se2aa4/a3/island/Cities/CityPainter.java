package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.locationtech.jts.geom.Point;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CityPainter implements MyCityPainter{

    private final CentroidToCity centroidToCity = new CentroidToCity();
    private final CitySizeDecider cityDecider = new CitySizeDecider();

    // Take in polygons within island and number of cities to make, and randomly makes vertices in island as cities.
    public void addCitiesToIsland(List<MyPolygon> polygons, int numCities, List<MyVertex> vertices){
        Set<Integer> polygonsWithIndices = new HashSet<>();
        Random rand = new Random();
        int islandPolygons = countIslandPolygons(polygons);

        int index = -1;
        int centroidIdx;
        MyPolygon p;

        // Adds cities, and makes sure the polygon doesn't already contain a city before adding it.
        for (int i = 0; i < numCities && polygonsWithIndices.size() < islandPolygons; i++){
            do {
                index = rand.nextInt(polygons.size());

                // Ensures that number of cities is capped at number of island polygons and the polygon used is an island polygon.
            } while (polygonsWithIndices.contains(index) || polygons.get(index).isWaterTile());
            polygonsWithIndices.add(index);

            // Gets the centroid index from the polygon at the given index, and uses its centroid index to get the vertedx.
            p = polygons.get(index);
            centroidIdx = p.getCentroidIdx();
            addVertexAsCity(vertices.get(centroidIdx), rand.nextInt(100));
        }
    }

    // Based on the population, gets the city type, and converts the vertex to a city based on that.
    private void addVertexAsCity(MyVertex v, int population){
        centroidToCity.changeCity(v, cityDecider.getCityType(population));
    }

    private int countIslandPolygons(List<MyPolygon> polygons){
        int count = 0;

        for (MyPolygon p : polygons){
            count += (p.isWaterTile()) ? 0 : 1;
        }

        return count;
    }

}
