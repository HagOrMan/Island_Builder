package ca.mcmaster.cas.se2aa4.a3.island.Cities;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Takes in a vertex and paints it as a city based on its type.
public class CentroidToCity {

    // Mappings of each city type to a given vertex size and colour.
    private final Map<CityOption, Integer> citySizes;
    private final Map<CityOption, Color> cityColours;

    public CentroidToCity(){
        citySizes = makeCitySizes();
        cityColours = makeCityColours();
    }

    private Map<CityOption, Integer> makeCitySizes(){
        Map<CityOption, Integer> options = new HashMap<>();
        options.put(CityOption.CAPITAL, 15);
        options.put(CityOption.CITY, 10);
        options.put(CityOption.VILLAGE, 7);
        options.put(CityOption.HAMLET, 4);
        return options;
    }

    private Map<CityOption, Color> makeCityColours(){
        Map<CityOption, Color> options = new HashMap<>();
        options.put(CityOption.CAPITAL, new Color(0xD2AC2A));
        options.put(CityOption.CITY, new Color(0xDE41DD));
        options.put(CityOption.VILLAGE, new Color(0xFCB28C));
        options.put(CityOption.HAMLET, new Color(0xFF5C5C));
        return options;
    }

    // Changes a vertex to represent a given city type.
    public void changeCity(MyVertex vertex, CityOption option){
        vertex.setThick(citySizes.get(option));
        Color colour = cityColours.get(option);
        vertex.changeColor(colour.getRed() + "," + colour.getGreen() + "," + colour.getBlue());
        vertex.makeVertexCity(option);
    }

}
