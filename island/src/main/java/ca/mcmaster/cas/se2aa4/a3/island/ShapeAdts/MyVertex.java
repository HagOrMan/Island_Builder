package ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityOption;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.VertexCityTypes.VertexGroup;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.VertexCityTypes.VertexHasCity;
import ca.mcmaster.cas.se2aa4.a3.island.Cities.VertexCityTypes.VertexNoCity;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex.*;
import ca.mcmaster.cas.se2aa4.a3.island.PropertyManager;

import java.awt.*;

public class MyVertex implements MyShape {

    private static int totalIndex = 0;
    private final int index;
    private Vertex vertex;
    private IslandVertex islandVertex;
    private VertexGroup cityType;

    public MyVertex(Vertex v){
        vertex = v;
        index = totalIndex;
        totalIndex++;
        islandVertex = new LandVertex();
        cityType = new VertexNoCity();
    }

    public void changeColor(String colorCode){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder(vertex).setProperties(0, color).build();
    }


    // Getters
    public int getIndex(){ return index; }
    public double getX(){ return vertex.getX(); }
    public double getY(){ return vertex.getY(); }
    public Vertex getVertex(){ return vertex; }

    public boolean makeRiverVertex(int discharge){
        if (islandVertex.getClass() == RiverVertex.class){
            return false;
        }
        // Ensure that the originally set elevation is maintained, when changing the vertex type
        islandVertex = new RiverVertex(discharge, islandVertex.getElevation());
        Color riverColor = islandVertex.getColor();
        changeColor(riverColor.getRed() + "," + riverColor.getGreen() + "," + riverColor.getBlue());
        return true;
    }

    public void setElevation(int elevation){ islandVertex.setElevation(elevation);}


    public int getElevation() { return islandVertex.getElevation(); }

    public double getMoisture(){
        return islandVertex.getDischarge();
    }

    public double getMoistureProvided(){ return islandVertex.getDischarge(); }

    public void addToDischarge(int n) {
        islandVertex.addToDischarge(n);
    }

    /**
     * Sets the thickness of this vertex.
     * @param thickness int value of thickness wanted
     */
    public void setThick(float thickness){
        Structs.Property thick = Structs.Property.newBuilder().setKey("thickness").setValue("" + thickness).build();

        String val = PropertyManager.getProperty(this.getPropertiesList(), "thickness");

        // If thickness property does not already exist.
        if (val == null) {
            vertex = Vertex.newBuilder(vertex).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            vertex = Vertex.newBuilder(vertex).setProperties(1, thick).build();
        }
    }

    public void makeVertexCity(CityOption cityOption){
        cityType = new VertexHasCity(cityOption);
    }

    public java.util.List<Structs.Property> getPropertiesList() {
        return vertex.getPropertiesList();
    }

    /**
     * Tells if this vertex is a city or not.
     * @return true if this vertex is a city.
     */
    public boolean isCity(){ return cityType.getCityType() != CityOption.NONE; }
    public CityOption getCityType(){ return cityType.getCityType(); }

}
