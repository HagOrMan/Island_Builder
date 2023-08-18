# Assignment A4: Urbanism
This project was completed as part of my 'Introduction to Software Development' course, and involved an iterative approach through 3 projects.
Each built off the previous, from creating grids and irregular meshes, to building an island from a mesh, to mapping roads onto that island by using a shortest path algorithm.
Assignment 2 and 3, the creation of meshes as well as island building, were completed with two teammates whose contributions can be seen in Backlog.md.
The io module is all template code and has not been touched in the creation of this project.

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
For A4, the associated features are found in the linked project to this repo. For previous assignments, they are in the Backlog.md file.

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
The `makefile` contains many ways to customize your island generation. The main one would be running 
```
make city
```

which would generate a city based on the parameters specified in `gencity` in the `makefile`. Commands are generally split by what they create, such as `cirvol` creating a circle shaped island with a volcano. However, for an elevation like this, you'd probably want to run
```
make cirelvol
```
The `el` means elevation heatmap, which will accurately show the volcano elevation in the island.

Feel free to look in the makefile for more basic examples of how to run the program. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one main argument, the name of the file where the generated mesh will be stored as binary. See [here](#running-the-generator) for more customization. This will create a basic mesh with polygons, segments, and vertices.

```
cd generator 
java -jar generator.jar sample.mesh
```

### Island
To add island properties to an existing mesh, go to the `island` directory, and use `java -jar` to run the product. The product takes two main arguments: the file containing the mesh, and the name of the file to store the enhanced island mesh. See [here](#running-the-island-generator) for more customization. This will enhance the basic mesh to look like an island.
```
cd island
java -jar island.jar -o island.mesh -i ../generator/sample.mesh
```

### Visualizer

To visualize an existing mesh, go to the `visualizer` directory, and use `java -jar` to run the product. The product take two main arguments: the file containing the mesh, and the name of the file to store the visualization (as an SVG image). See [here](#running-the-visualizer) for more customization.

To visualize an island
```
cd visualizer 
java -jar visualizer.jar -i ../island/island.mesh -o island.svg

... (lots of debug information printed to stdout) ...
```

To visualize a mesh that has not been enhanced to an island.
```
cd visualizer 
java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg

... (lots of debug information printed to stdout) ...
```

## Running the island generator
### Sample command: Island with cities
``````
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -elevation volcano -soil wet -lake 5 -river 6 -aquifer 4 -seed 6812161995636894525 -biome warmtemperate -city 10
``````

### Legend
- -i = input file path to use
- -o = output file path to use
- -seed = seed to use which will always generate the same map for the same seed and other parameters. Seed is printed if none is specified so you can reproduce that generation
- -mode = (defaults to `circle`) either `lagoon` for lagoon mode or valid shape (see [list of valid shapes](#list-of-valid-shapes))
- -city = number of cities to generate (`default = 0`)
- -elevation = (defaults to `plains`) elevation profile to use (see [list of valid profiles](#list-of-valid-elevation-profiles))
- -aquifer = maximum number of aquifers to create (`default = 0`)
- -lake = maximum number of lakes to generate (`default = 0`)
- -river = maximum number of rivers to generate (`default = 0`)
- -soil = (defaults to `wet` profile) soil absorption profile to use, determines how much moisture is absorbed from surrounding water sources (see [list of soil profiles](#list-of-valid-soil-absorption-profiles))
- -biome = (optional, if not used then land tiles will be green so no difference is seen in tiles based on their elevation and moisture) Whittaker diagram to take biomes from when visualizing (see [list of valid Whittaker profiles](#list-of-valid-whittaker-profiles))
- -heatmap = (optional) heatmap type to use for visualizing properties (see [list of valid heatmaps](#list-of-valid-heatmaps)). 

## Valid Parameter Values
### Adding your own
Note: All of these items are open to extension. Create a class in the proper folder that extends/implements the proper superclass/interface

Example: Go to `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\IslandShapes`, create a file named `Star.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

public class Star extends AbstractIslandShape{
  ...code here
}
```
with your custom logic inside the proper method, `getShape()` for this example.

Then go to their respective factory (eg. `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\InputFactories\IslandShapeFactory.java` for Shapes), and add that class as an option following the format of the other options! 
For this example, in the function `createBuilderOptions()`, add the line 
```
options.put("star", new Star(width, height));
```

### List of valid shapes
1. 'circle' (default)
2. 'hexagon'

To add to this list, follow the steps [above](#adding-your-own)

### List of valid heatmaps
1. 'elevation' : Shows elevation of polygons (default if non-valid heatmap is input when heatmap command is used)
2. 'moisture' : Shows moisture of polygons
3. 'vertexelevation' : Shows elevation of vertices

To add to this list:<br>
Go to `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\Heatmaps`, create a file named `NewPainter.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

public class NewPainter extends HeatmapPainter{
  ...code here
}
```
with your custom logic inside the proper method: `determineColor(Shape s)`.

Then go to the respective factory ( `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\InputFactories\HeatmapFactory.java`), and add to the function `createHeatmapOptions()`
```
options.put("newPainter", new NewPainter());
```

Here, you just need to add your own profile on how to determine colour based on the input shape.

### List of valid elevation profiles
1. 'plains' (default) : Randomly generated elevations per tile in low range
2. 'volcano' : Largest elevation in middle of island, slopes down

To add to this list:<br>
Go to `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\Elevation`, create a file named `NewElevation.java`, and include the lines
```
package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

public class NewElevation extends GeneralElevationProperties{
  ...code here
}
```
with your custom logic inside the proper method: `generateElevationProfile(IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues)`.

Then go to the respective factory ( `island\src\main\java\ca\mcmaster\cas\se2aa4\a3\island\InputFactories\ElevationFactory.java`), and add to the function `createElevationOptions()`
```
options.put("newElevation", new NewElevation(rand));
```

Here, you just need to generate a profile from the input polygon list, vertices list, and island shape.

# TODO: EVERYTHING UNDER HERE
### List of valid soil absorption profiles
1. 'wet' (default) : Polygons within a large range gain moisture from surrounding water sources
2. 'dry' : Has 1/4 the range of wet

The soil absorption profiles are open to extension, such that someone may add their own custom absorption profiles
to the project without having to edit prior source code. The SoilProfile interface has the abstract calcMoisture
method that must be overridden depending on how the profile needs to be generated. As such, the user only needs to provide
how to calculate moisture received based on the input moisture and distance to that source.

### List of valid Whittaker profiles
1. 'arctic' : contains Dry, Moist, Wet, and Rain tundra tiles
2. 'warmtemperate' : contains Desert, Desert Scrub, Woodland, Dry forest, Moist Forest, Wet Forest, and Rain forest tiles

The Whittaker profiles are open to extension, such that someone may add their own custom profiles
to the project without having to edit prior source code. The diagrams are the source for what kind of biome the mesh
generates, but the general interface which needs to be extended only need take in elevation and moisture, and return
the type of tile that corresponds to those values. To make it easier, the user can extend the biome abstract class that 
implements that interface, and the arctic and WarmTemperate classes inherit from this abstract class. For the user to extend
biome, it simulates a Whittaker graph as a Geometry and allows the user to populate that geometry with regions (smaller geometries)
that correspond to a certain tile.


### Information regarding lakes and rivers
1. Endorheic lakes do not count towards the maximum number of lakes 
2. Rivers which randomly generate at a vertex where they cannot move any further just begin and end there

## How to run the product
### Sample commands to run the program: 
To run the lagoon, copy and paste the following: 
cd island
java -jar island.jar -o lagoon.mesh -i ../generator/input.mesh -mode lagoon
cd ..
cd visualizer
java -jar visualizer.jar -i ../island/lagoon.mesh -o lagoon.svg

To run the main island, copy and paste the following: 
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -elevation volcano -biome warmtemperate -soil wet -lake 2 -river 4 -aquifer 2 -seed 6812161995636894525
cd ..
cd visualizer
java -jar visualizer.jar -i ../island/island.mesh -o island.svg

The island generation command can be customized to have different modes depending on what you wish to generate, and 
should you choose not to input a seed value, then the program will automatically choose one and output it for you. 

To run the main island while seeing a heatmap, copy and paste the following: 
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -elevation volcano -soil dry -lake 2 -river 4 -aquifer 2 -seed 6812161995636894525 -heatmap moisture
cd ..
cd visualizer
java -jar visualizer.jar -i ../island/island.mesh -o island.svg

The same customization as outlined in the second example can be applied to the heatmap. 

To visualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tools like `rsvg-convert`


## Documentation
To get thickness of vertices and lines, extract the thickness from the properties list using the key 'thickness'.

## Running the Generator
## Format for user input when running generator main file
- Different arguments accepted depending on if using a grid or an irregular mesh.
- For a grid, that is the default, for an irregular mesh, use '-ir'.
- For help mode, enter '-h'.  
- For changing anything from default values, consult legend for command to use, and then insert value wanted after a space.  
- For relaxation level, the value specifies the number of times lloyd relaxation is applied.

## Part Two Scenario Description 
- To generate a grid mesh in debug mode, you must input the following commands into the terminal: 
- cd generator 
- java -jar generator.jar sample.mesh
- cd ..
- cd visualizer 
- java -jar visualizer.jar ../generator/sample.mesh sample.svg -X

## Part Three Scenario Description
- To generate an irregular mesh with the following specifications: 
- 200 polygons, relaxed mesh 5 times, polygon transparency of 200, and segment thickness of 10, the rest being default values found below
- cd generator 
- java -jar generator.jar sample.mesh -ir -np 200 -rl 5 -pa 200 -st 10
- cd ..
- cd visualizer
- java -jar visualizer.jar ../generator/sample.mesh sample.svg 

## Legend
- -ir = use an irregular mesh instead of grid
- -pa = polygon transparency (0 to 255) (default = 255)
- -sa = segment transparency (0 to 255) (default = 255)
- -va = vertex transparency (0 to 255) (default = 255)
- -pt = polygon thickness (default = 0.5)
- -st = segment thickness (default = 0.5)
- -vt = vertex thickness (default = 3)

Only applicable in irregular mesh
- -np = number of polygons (default = 100)
- -rl = number of relaxations (default = 0)

## Grid
sample.mesh (extra commands from legend)

### Example
User wants output file sample.mesh, polygon transparency of 200, and segment thickness of 10
sample.mesh -pa 200 -st 10

## Irregular
sample.mesh -ir (extra commands from legend)

### Example
User wants output file sample.mesh, 200 polygons, relaxed mesh 5 times, polygon transparency of 200, and segment thickness of 10
sample.mesh -ir -np 200 -rl 5 -pa 200 -st 10

## Running the visualizer
## Format for user input when running visualizer main file
Add '-X' after first 2 arguments to enter debug mode. Anything else is taken as default visualization.
