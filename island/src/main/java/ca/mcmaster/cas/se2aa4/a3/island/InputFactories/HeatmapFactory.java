package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.ElevationPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.MoisturePainter;

import java.util.HashMap;
import java.util.Map;

public class HeatmapFactory {

    private final Map<String, HeatmapPainter> heatmapOptions = createHeatmapOptions();

    private Map<String, HeatmapPainter> createHeatmapOptions(){
        Map<String, HeatmapPainter> options = new HashMap<>();
        options.put("elevation", new ElevationPainter());
        options.put("moisture", new MoisturePainter());

        return options;
    }

    public HeatmapPainter getHeatmap(String key){
        try {
            return heatmapOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            // Sets elevation heatmap as default if issue arises when searching for elevation.
            return heatmapOptions.get("elevation");
        }
    }

}