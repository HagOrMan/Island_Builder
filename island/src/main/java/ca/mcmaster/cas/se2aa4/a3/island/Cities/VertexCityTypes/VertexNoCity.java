package ca.mcmaster.cas.se2aa4.a3.island.Cities.VertexCityTypes;

import ca.mcmaster.cas.se2aa4.a3.island.Cities.CityOption;

public class VertexNoCity implements VertexGroup{
    @Override
    public CityOption getCityType() {
        return CityOption.NONE;
    }
}
