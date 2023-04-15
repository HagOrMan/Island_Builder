package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;

public class PropertyManager {

    public static String getProperty(List<Property> values,
                                     String key){
        String val = null;
        for(Property p: values) {
            if (p.getKey().equals(key)) {
                val = p.getValue();
            }
        }
        return val;
    }
}
