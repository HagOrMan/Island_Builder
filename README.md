# Assignment A4: Urbanism
This project was completed as part of my 'Introduction to Software Development' course, and involved an iterative approach through 3 projects.
Each built off the previous, from creating grids and irregular meshes, to building an island from a mesh, to mapping roads onto that island by using a shortest path algorithm.
Assignment 2 and 3, the creation of meshes as well as island building, were completed with two teammates whose contributions can be seen in [Backlog.md](Backlog.md).
The io module is all template code and has not been touched in the creation of this project.

## Examples
To see examples of generation commands and the created islands/meshes, see [GenerationExamples.md](GenerationExamples.md).
Note that lakes are red.

## More about this project
As an extension to A3-Island Generation, A4 looks to create a star network of cities on the island.
It implements a shortest path algorithm using dijkstra's algorithm and an adjacency list, although interface segregation allows anyone to create their own graph representation and path finding algorithm to be used instead.
First, the most central city is found from the randomly generated cities and made the capital (see [here](#how-capital-city-works)).
Then, the shortest path to each other city is calculated, and roads are made to connect the capital to other cities.

There are also roads connecting cities to cities/villages, villages to villages/hamlets, and hamlets to hamlets.
Taking city to be the largest and hamlet the smallest, the road sizes and colour also differ based on the size of the city types connected.
The darker and larger the road is, the bigger the city types being connected are. There is also a component of distance, where certain city types will only connect to other types within a certain tile range (eg. only villages within 7 blocks will be connected to ensure roads make sense and villages across the island aren't connected).

### How Capital City Works
Capital city is decided based on the most central city once they have been randomly generated on the map.
This is done by calculating the shortest path from every node as a source node to every other node, taking the longest path from every source node, and comparing each longest path to other source nodes to find the shortest of all the longest paths.

## Documentation
For A4, the associated features are found in the linked project to this repo. For previous assignments, they are in the [Backlog.md](Backlog.md) file.

# Running the Program

## Installation instructions

This product is handled by Maven, as a multi-module project. Ensure you have maven before continuing.

To install the tooling on your computer, simply run:
```
mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### After code development
Run the following after changing any of the code to update .jar files and everything else.
```
mvn clean install
mvn compile
mvn package
```

### Makefile use
The `makefile` contains many ways to customize your island generation. 

Note that most of these commands require first running 
```
make genis
```
to generate a base mesh to paint islands on top of.

The main command would be running 
```
make city
```

which would generate a city based on the parameters specified in `gencity` in the `makefile`. Commands are generally split by what they create, such as `cirvol` creating a `cir`cle shaped island with a `vol`cano. 
However, for an elevation like this, you'd probably want to run
```
make cirelvol
```
The `el` means elevation heatmap, which will accurately show the volcano elevation in the island. Without this, you'd get a circular island without much to tell that it has a volcano.

Feel free to look in the makefile for more basic examples of how to run the program. 

### Generator
For help, run 
```
cd generator 
java -jar generator.jar -h
```

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one main argument, the name of the file where the generated mesh will be stored as binary. See [here](#running-the-generator) for more customization. This will create a basic mesh with polygons, segments, and vertices.

The following command creates a grid mesh with no further specifications. This is not meant to be turned into an island.
```
cd generator 
java -jar generator.jar sample.mesh
```

To create an irregular mesh for an island (polygons rather than squares), we use `-ir` in our arguments. I also use the convention of naming the island mesh `input.mesh`. The following command creates an island of 400 polygons (`-np 400`), a Lloyd relaxation applied 40 times to normalize the polygon shapes (`-rl 40`), and a polygon thickness of 0 (`-pt 0`).
```
cd generator 
java -jar generator.jar input.mesh -ir -pt 0 -rl 40 -np 400
```

### Island
For help, run 
```
cd island 
java -jar island.jar -h
```
To add island properties to an existing mesh, go to the `island` directory, and use `java -jar` to run the product. The product takes two main arguments: the file containing the mesh, and the name of the file to store the enhanced island mesh. See [here](#running-the-island-generator) for more customization. 
```
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh
```

To create a lagoon instead, use commands similar to the following. `lagoon` mode creates an inner lake of lagoon water, and all tiles adjacent to water are beach tiles.
```
cd island
java -jar island.jar -o lagoon.mesh -i ../generator/input.mesh -mode lagoon
```

### Visualizer
For help, run 
```
cd visualizer 
java -jar visualizer.jar -h
```
To visualize an existing mesh, go to the `visualizer` directory, and use `java -jar` to run the product. The product take two main arguments: the file containing the mesh, and the name of the file to store the visualization (as an SVG image). See [here](#running-the-visualizer) for more customization.

To visualize an island
```
cd visualizer 
java -jar visualizer.jar -i ../island/island.mesh -o island.svg

... (lots of debug information printed to stdout) ...
```
You can alternatively use `-i ../island/lagoon.mesh` if you created a lagoon in the island step.

To visualize a mesh that has not been enhanced to an island.
```
cd visualizer 
java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg

... (lots of debug information printed to stdout) ...
```

**To visualize the SVG file:**
  - Open it with a web browser
  - Convert it into something else with tools like `rsvg-convert`

## Full Example
The following will create a polygon mesh with `400 polygons`, and then create an island with `10 cities` on top of it. Open `visualizer/island.svg` to see the result.
```
cd generator 
java -jar generator.jar input.mesh -ir -pt 0 -rl 40 -np 400
cd ..
```
```
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -elevation volcano -soil wet -lake 5 -river 6 -aquifer 4 -seed 6812161995636894525 -biome warmtemperate -city 10
cd ..
```
```
cd visualizer 
java -jar visualizer.jar -i ../island/island.mesh -o island.svg
cd ..
```
See the [image](GenerationExamples.md#full-example)

## Running the Generator
### Sample command: Irregular mesh with 400 polygons
```
cd generator 
java -jar generator.jar input.mesh -ir -pt 0 -rl 40 -np 400
cd ..
```

## Legend
- `-ir` = (optional) create an irregular mesh instead of grid
- `-pa` = polygon transparency (0 to 255) (`default = 255`)
- `-sa` = segment transparency (0 to 255) (`default = 255`)
- `-va` = vertex transparency (0 to 255) (`default = 255`)
- `-pt` = polygon thickness (`default = 0.5`)
- `-st` = segment thickness (`default = 0.5`)
- `-vt` = vertex thickness (`default = 3`)

Note that a transparancy of `255` means it is fully opaque, and `0` means fully transparent.

**Only applicable in irregular mesh** (using `-ir`):
- `-np` = number of polygons (`default = 100`)
- `-rl` = number of relaxations (`default = 0`)

## Running the island generator
### Sample command: Island with cities
```
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -elevation volcano -soil wet -lake 5 -river 6 -aquifer 4 -seed 6812161995636894525 -biome warmtemperate -city 10
```

### Information regarding lakes and rivers
1. Endorheic lakes do not count towards the maximum number of lakes 
2. Rivers which randomly generate at a vertex where they cannot move any further just begin and end there

### Legend
- `-i` = input file path to use
- `-o` = output file path to use
- `-seed` = seed to use which will always generate the same map for the same seed and other parameters. Seed is printed if none is specified, so you can reproduce that generation
- `-mode` = (defaults to `circle`) either `lagoon` for lagoon mode or valid shape (see [list of valid shapes](#list-of-valid-shapes)). 
- `-city` = number of cities to generate (`default = 0`)
- `-elevation` = (defaults to `plains`) elevation profile to use (see [list of valid profiles](#list-of-valid-elevation-profiles))
- `-aquifer` = maximum number of aquifers to create (`default = 0`)
- `-lake` = maximum number of lakes to generate (`default = 0`)
- `-river` = maximum number of rivers to generate (`default = 0`)
- `-soil` = (defaults to `wet` profile) soil absorption profile to use, determines how much moisture is absorbed from surrounding water sources (see [list of soil profiles](#list-of-valid-soil-absorption-profiles))
- `-biome` = (optional, if not used then land tiles will be green so no difference is seen in tiles based on their elevation and moisture) Whittaker diagram to take biomes from when visualizing (see [list of valid Whittaker profiles](#list-of-valid-whittaker-profiles))
- `-heatmap` = (optional) heatmap type to use for visualizing properties (see [list of valid heatmaps](#list-of-valid-heatmaps)). Heatmaps show water sources (lakes and aquifers) as turquoise.

### Valid Parameter Values
#### Adding your own
Note: All of these items are open to extension. To add your own, create a class in the proper folder that extends/implements the proper superclass/interface, and add that class to the respective Factory in `island/src/main/java/ca/mcmaster/cas/se2aa4/a3/island/InputFactories/`.

**Example:** 
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


***
***

#### List of valid shapes
1. `circle` (default)
2. `hexagon`

To add to this list, follow the steps [above](#adding-your-own)

***
***

#### List of valid heatmaps
1. `elevation` : Shows elevation of polygons (default if non-valid heatmap is input when heatmap command is used)
2. `moisture` : Shows moisture of polygons
3. `vertexelevation` : Shows elevation of vertices

To add your own profile:<br>
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

***
***

#### List of valid elevation profiles
1. `plains` (default) : Randomly generated elevations per tile in low range
2. `volcano` : Largest elevation in middle of island, slopes down

To add your own profile:<br>
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

***
***

#### List of valid soil absorption profiles
1. `wet` (default) : Polygons within a large range gain moisture from surrounding water sources
2. `dry` : Has 1/4 the range of wet

To add your own profile:<br>
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

***
***

#### List of valid Whittaker profiles
1. `arctic` : contains Dry, Moist, Wet, and Rain tundra tiles
2. `warmtemperate` : contains Desert, Desert Scrub, Woodland, Dry forest, Moist Forest, Wet Forest, and Rain forest tiles

To add your own profile:<br>
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

## Running the visualizer
The visualizer is simple to run, and uses 3 arguments at the most.

### Legend
- `-i` = input file path to use
- `-o` = output file path to use
- `-X` = (optional) run in debug mode. This uses basic colours to view the connections between polygons and their neighbours, where centroids are red and are joined by a grey segment. Regular segments are white.
