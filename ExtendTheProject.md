# Extending the Project
You can add to this project and have fun making your own customizations easily! Just follow the steps in any of the following sections to add to them with no issues to the code!

## Shapes
1. Go to `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/IslandShapes`, create a file named `Star.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

public class Star extends AbstractIslandShape{
  ...code here
}
```
with your custom logic inside the proper method, `getShape()` for this example. For shapes, you need to return a `Geometry` object that represents the outline of the island.

2. Then go to the respective factory (eg. `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/IslandShapeFactory.java` for Shapes), and add that class as an option following the format of the other options.
   For this example, in the function `createBuilderOptions()`, add the line
```
options.put("star", new Star(width, height));
```

---

You may also create a class that `implements IslandShape` if you don't want access to the properties of `AbstractIslandShape`.

## Elevations
1. Go to `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/Elevation`, create a file named `NewElevation.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

public class NewElevation extends GeneralElevationProperties{
  ...code here
}
```
with your custom logic inside the proper method: `generateElevationProfile(IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues)`. This function creates a list of elevation values, each will be mapped to the corresponding polygons in the `polygons` list. The island shape is passed in in case you want to use any island properties such as the midpoint for your elevation generation.

2. Then go to the respective factory ( `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/ElevationFactory.java`), and add to the function `createElevationOptions()`
```
options.put("newElevation", new NewElevation(rand));
```

---

You may also create a class that `implements BaseElevation` if you don't want to work with `GeneralElevationProperties`. Here, you would instead create the method `generateElevation(IslandShape i, List <MyPolygon> polygons, List <MyVertex> vertices)`. This must set the elevation values of all of the input polygons and vertices. 

## Soil Absorption
1. Go to `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/Humidity`, create a file named `NewSoilProfile.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class NewSoilProfile implements SoilProfile{
  @Override
  public int calcMoisture(int moisture, double distance) {
      ...code here
  }
}
```
with your custom logic inside the proper method: `calcMoisture(int moisture, double distance)`. This must return an `int` based on the input moisture and the distance from that moisture.

2. Then go to the respective factory ( `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/SoilFactory.java`), and add to the function `createSoilOptions()`
```
options.put("newSoilProfile", new NewSoilProfile());
```

## Whittaker Profile
1. Go to `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/Whittaker/Biomes`, create a file named `NewBiome.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

public class NewBiome extends Biome{
  ...code here
}
```
with your custom logic inside the proper method: `setRegions()` and `setMappings()`.
* `setRegions()` must create and return a `List<Geometry> regions`, where each item is a shape that fits within the `width` and `height`.
* `setMappings()` must then create and return a `Map<Geometry, Class<? extends Tile>> mappings` where each item in `regions` maps to a `Tile` found in `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/Whittaker/BiomeTiles`.

The idea behind the abstract class `Biome` is that it simulates a Whittaker graph as a `Geometry` object and allows the user to populate that geometry with `regions` (smaller geometries) that correspond to a certain `Tile`. Thus elevation and moisture are the axes which determine the region a `Tile` is in.

2. Then go to the respective factory ( `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/BiomeFactory.java`), and add to the function `createBiomeOptions()`
```
options.put("newBiome", new NewBiome());
```

---

You may also create a class that `implements WhittakerDiagram` if you don't want to work with `Biome`. Here, you would instead create the method `getTile(int moisture, int elevation)`. This must return any tile based on the moisture and elevation. Each Tile object has a `getColor()` that returns the color of the tile to help visualize it on the island.

## Heatmaps
1. Go to `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/Heatmaps`, create a file named `NewPainter.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

public class NewPainter extends HeatmapPainter{
  ...code here
}
```
with your custom logic inside the proper method: `determineColor(Shape s)`. This must return a `Color` based on a property of the input shape.

2. Then go to the respective factory ( `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/HeatmapFactory.java`), and add to the function `createHeatmapOptions()`
```
options.put("newPainter", new NewPainter());
```
