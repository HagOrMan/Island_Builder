package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

//take in geometry in the constructor
//set protected fields island.getCentre (Point object)
//pass in list of polygons, set protected my centre polygon in the field
//WHEN IMPLEMENTING LAKES AND RIVERS, NOT WATER TILE
public abstract class GeneralElevationProperties implements BaseElevation{

    protected IslandShape island;
    protected List <MyPolygon> polygons;
    protected int maxElevation = 100;
    protected List <Integer> elevationValues = new ArrayList<>(polygons.size());

    protected Point islandCentre;
    public GeneralElevationProperties (IslandShape i, List <MyPolygon> polygons){
        this.island = i;
        this.polygons = checkPolygonsWithinIsland(polygons, i.getShape());
    }

    protected void getIslandCentre (){
       islandCentre = island.getCenter();
    }

    protected MyPolygon getMiddlePolygon (){
        for (MyPolygon polygon : polygons) {
            if (polygon.containsPoint(islandCentre)) {
                return polygon;
            }
        }
        return null; //CHECK LATER IF THIS IS FINE
    }

    protected List<MyPolygon> checkPolygonsWithinIsland (List <MyPolygon> polygons, Geometry island){
        List<MyPolygon> withinIsland = new ArrayList<>();
        for (MyPolygon p : polygons){
            if (island.contains(p.getJTSPolygon())){
                withinIsland.add(p);
            }
        }

        return withinIsland;
    }

    private void setElevation() {
        for (int i = 0; i<polygons.size(); i++){
            //SET POLYGON ELEVATIONS
            polygons.get(i).setElevation(elevationValues.get(i));
        }
    }

    protected abstract void generateElevationProfile ();

    @Override
    public void generateElevation() {
        generateElevationProfile();
        setElevation();
    }
}
