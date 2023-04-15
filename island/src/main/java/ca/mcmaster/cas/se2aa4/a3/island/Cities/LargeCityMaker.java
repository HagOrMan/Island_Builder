package ca.mcmaster.cas.se2aa4.a3.island.Cities;

// LargeCityMaker only makes large cities and has no variety no matter what the population.
public class LargeCityMaker implements CityMaker{

    @Override
    public CityOption getCityType(int population) {
        return CityOption.CITY;
    }

}
