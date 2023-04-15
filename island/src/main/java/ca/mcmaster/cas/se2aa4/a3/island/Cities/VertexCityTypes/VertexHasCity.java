package ca.mcmaster.cas.se2aa4.a3.island.Cities.VertexCityTypes;

import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityOption;

public class VertexHasCity implements VertexGroup{

    private CityOption type;

    public VertexHasCity(CityOption cityOption){
        type = cityOption;
    }


    @Override
    public CityOption getCityType() {
        return type;
    }
}
