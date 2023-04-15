package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a3.island.Cities.CapitalCityCreator;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.MyCapitalCityMaker;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.MyCityPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.AquiferGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.RiverGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.MoistureAdder;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.WhittakerDiagram;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IslandBuilder extends AbstractBuilder {

    private final IslandShape islandShape;

    public void constructElevation (BaseElevation elevation){
        elevation.generateElevation(islandShape, findPolygonsWithinIsland(), myVertices);
    }

    public void constructRivers(Random rand, int numRivers) {
        // River generator
        new RiverGenerator(findPolygonsWithinIsland(), myPolygons,  mySegments, numRivers, rand);

    }

    public IslandBuilder(IslandShape shape){
        islandShape = shape;
    }

    @Override
    public void buildIsland(Mesh aMesh, Random rand, int numAquifers, int numLakes, int numRivers){

        extractFromMesh(aMesh);

        // Creates island shape.
        Geometry shape = islandShape.getShape();

        // Sets land tiles and neighbours.
        setTileInsideShape(shape, myPolygons, LandTile.class);
        setPolyNeighbours(myPolygons);

        // Lake generator
        new LakeGenerator(findPolygonsWithinIsland(), numLakes, rand);

        // Aquifer generator
        new AquiferGenerator(findPolygonsWithinIsland(), numAquifers, rand);

    }

    public void addMoistureToPolygons(SoilProfile soilProfile){
        MoistureAdder moistureAdder = new MoistureAdder();
        moistureAdder.addMoistureToPolygons(findPolygonsWithinIsland(), soilProfile, mySegments);
    }

    private List<MyPolygon> findPolygonsWithinIsland(){
        Geometry island = islandShape.getShape();
        List<MyPolygon> withinIsland = new ArrayList<>();
        for (MyPolygon p : myPolygons){
            if (island.contains(p.getJTSPolygon())){
                withinIsland.add(p);
            }
        }

        return withinIsland;
    }

    protected void setPolyNeighbours(List<MyPolygon> myPolygons){
        for (MyPolygon p1 : myPolygons){
            for (MyPolygon p2: myPolygons){
                p1.checkForNeighbour(p2);
            }
        }
    }

    public void generateBiome(WhittakerDiagram biome){
        if (biome == null){
            return;
        }
        for (MyPolygon p : findPolygonsWithinIsland()){
            Tile newTile = biome.getTile((int) p.getMoisture(), p.getElevation());
            p.attemptChange(newTile);
        }
    }

    public void generateCities(int numCities, Random rand){

        // Randomly adds cities to map based on input.
        MyCityPainter cityPainter = new CityPainter();
        normalizeVertices();
        cityPainter.addCitiesToIsland(findPolygonsWithinIsland(), numCities, myVertices, rand);

        // Makes a capital city and saves the vertex where this is found.
        MyCapitalCityMaker capitalCityMaker = new CapitalCityCreator();
        MyVertex capital = capitalCityMaker.makeCapitalCity(findIslandVertices(), findPolygonsWithinIsland(), islandShape);
    }

    private void normalizeVertices(){
        for (MyPolygon p : myPolygons){
            p.changeCentroidColour("0,0,0,0");
        }
        for (MyVertex v : myVertices){
            v.setThick(1);
        }
    }

    // Gets list of all vertices in island that aren't on lake tiles.
    private List<MyVertex> findIslandVertices(){
        List<MyVertex> islandVertices = new ArrayList<>();

        for (MyPolygon p : findPolygonsWithinIsland()){
            if (!p.isWaterTile()){
                islandVertices.add(myVertices.get(p.getCentroidIdx()));
            }
        }

        return islandVertices;
    }

}
