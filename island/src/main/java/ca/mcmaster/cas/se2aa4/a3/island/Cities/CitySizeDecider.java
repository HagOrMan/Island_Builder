package ca.mcmaster.cas.se2aa4.a3.island.Cities;


public class CitySizeDecider implements SizeDecider {

    // Takes in a population and returns city type that should be used.
    public CityOption getCityType(int population) {
        return ((population < 40) ? CityOption.HAMLET :
                (population < 70) ? CityOption.VILLAGE :
                CityOption.CITY);
    }

}
