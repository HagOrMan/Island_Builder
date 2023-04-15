# Assignment A4: Urbanism

  - Kyle Hagerman [hagermak@mcmaster.ca]

Check A3 Information --> Legend for information about command line arguments and specifying the number of cities.
Note: Capital city is decided based on proximity to center of island.

# A3 Information

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog

### Definition of Done

-- Each feature compiles properly on maven, functioning both on the generation side and the visualizing side, following all necessary business requirements.  --

### Product Backlog A3

| Id | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
|  F01  |  One preset shape given to user to create island from (circle)  |  Kyle  |  20/03/23  |  21/03/23  |  D  |
|  F02  |  Shape interface is open for extension and user can choose between options  |  Kyle  |  21/03/23  |  21/03/23  |  D  |
|  F03  |  Preset elevation profile given to user to modify island altitudes  |  Arjun  |  19/03/23  |  25/03/23  |  D  |
|  F04  |  Elevation profile is open for extension and user can choose between options  |  Arjun  |  19/03/23  |  26/03/23  |  D  |
|  F05  |  One tile of lake is shown on each island at lowest elevation  |  Derron  |  21/03/23  |  22/03/23  |  D  |
|  F06  |  User can set maximum number of lakes  |  Derron/Arjun  |  21/03/23  |  26/03/23  |  D  |
|  F07  |  One river is randomly generated and flows until no lower elevations  |  Derron  |  25/03/23  |  25/03/23  |  D  |
|  F08  |  River creates a lake when it cannot move to a lower elevation anymore  |  Derron  |  26/03/23  |  26/03/23  |  D  |
|  F09  |  User can specify number of rivers to generate  |  Arjun  |  25/03/23  |  25/03/23  |  D  |
|  F10  |  Two rivers can merge, making the new river wider  |  Derron  |  26/03/23  |  26/03/23  |  D  |
|  F11  |  Randomly generate one aquifer on island of tile size 1  |  Arjun  |  24/03/23  |  25/03/23  |  D  |
|  F12  |  Allow user to specify number of aquifers to make, each of a random size  |  Arjun  |  25/03/23  |  25/03/23  |  D  |
|  F13  |  Soil absorption profile makes only tiles directly connected to lakes absorb their humidity  |  Kyle  |  23/03/23  |  24/03/23  |  D  |
|  F14  |  Soil absorption profile makes only tiles directly connected to rivers absorb their humidity  |  Kyle  |  26/03/23  |  26/03/23  |  D  |
|  F15  |  Soil absorption profile makes only tiles directly connected to aquifers absorb their humidity  |  Kyle  |  25/03/23  |  25/03/23  |  D  |
|  F16  |  Soil absorption profile is open for extension and user can choose between options  |  Kyle  |  25/03/23  |  25/03/23  |  D  |
|  F17  |  Elevation and moisture determines biome of each tile and colour is changed accordingly  |  Kyle  |  25/03/23  |  26/03/23  |  D  |
|  F18  |  User can choose which biomes the terrain can be generated from using Whittaker  |  Kyle  |  26/03/23  |  26/03/23  |  D  |
|  F19  |  A seed is generated on a run of the creation and output to user  |  Arjun  |  24/03/23  |  25/03/23  |  D  |
|  F20  |  A seed can be input and will always generate the same mesh  |  Arjun  |  24/03/23  |  25/03/23  |  D  |
|  F21  |  User can select a heatmap to view elevation properties  |  Kyle  |  22/03/23  |  22/03/23  |  D  |
|  F22  |  User can select a heatmap to view moisture properties  |  Kyle  |  22/03/23  |  22/03/23  |  D  |
|  F  |  a  |  a  |  dd/mm/yy  |  dd/mm/yy  |  s  |

## Documentation

### Legend
- -i = input file path to use
- -o = output file path to use
- -mode = (optional, defaults to circle) either 'lagoon' for lagoon mode or valid shape
- -heatmap = (optional) heatmap type to use for visualizing properties (see list of valid heatmaps for more details)
- -elevation = (defaults to plains) elevation profile to use (see list of valid profiles for more details)
- -soil = (defaults to wet profile) soil absorption profile to use, determines how much moisture is absorbed from water sources
- -aquifer = maximum number of aquifers to create (default = 0)
- -seed = seed to use which will always generate the same map for the same seed
- -biome = Whittaker diagram to take biomes from when visualizing (defaults to only visualizing with general green land tiles)
- -lake = maximum number of lakes to generate (default = 0)
- -river = maximum number of rivers to generate (default = 0)
- -city = number of cities to generate (default = 0)

### List of valid shapes
1. 'circle' (default)
2. 'hexagon'

### List of valid heatmaps
1. 'elevation' : Shows elevation of polygons (default if non-valid heatmap is input when heatmap command is used)
2. 'moisture' : Shows moisture of polygons
3. 'vertexelevation' : Shows elevation of vertices

The heatmap profiles are open to extension, such that someone may add their own custom heatmap profiles
to the project without having to edit prior source code. The HeatmapPainter abstract class is implemented to hold the 
common heatmap methods, that can be used by any potential heatmap profile. As such, a user would just need to
add their own profile on how to determine colour based on the input shape, and the program will be able to use it.

### List of valid elevation profiles (and extension capabilities)
1. 'plains' (default) : Randomly generated elevations per tile in low range
2. 'volcano' : Largest elevation in middle of island, slopes down

The elevation profiles are open to extension, such that someone may add their own custom elevation profiles
to the project without having to edit prior source code. The BaseElevation interface has the general generateElevation 
method that can be overridden depending on how the profile needs to be generated. Additionally, there is the
GeneralElevationProperties abstract class that does the basic generation of the profile should a user extending BaseElevation 
want access to its methods. As such, a user would just need to add their own profile with how they would generate a profile
from the input polygon list, vertices list, and island shape, and the program will be able to use it. 

### List of valid soil absorption profiles
1. 'wet' (default) : Polygons within a large range gain moisture from surrounding water sources
2. 'dry' : Has 1/4 the range of wet

The soil absorption profiles are open to extension, such that someone may add their own custom absorption profiles
to the project without having to edit prior source code. The SoilProfile interface has the abstract calcMoisture
method that must be overridden depending on how the profile needs to be generated. As such, the user only needs to provide
how to calculate moisture received based on the input moisture and distance to that source.

### List of valid soil Whittaker profiles
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


# A2 Information

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Visualizer

To visualize an existing mesh, go to the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```


## Product Backlog A2

| Id | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
|  F01  |  Draw segments between vertices to visualize squares  |  Everyone  |  02/03/23  |  02/05/23  |  D  |
|  F02  |  Mesh generates at a precision model of 2 decimal places  |  Derron  |  02/06/23  |  02/08/23  |  D  |
|  F03  |  Mesh has minimal vertices  |  Kyle  |  02/03/23  |  02/07/23  |  D  |
|  F04  |  Mesh has minimal segments  |  Kyle  |  02/05/23  |  02/09/23  |  D  |
|  F05  |  Mesh has minimal polygons  |  Arjun/Derron  |  02/09/23  |  02/14/23  |  D  |
|  F06  |  Polygon has changeable colour transparency  |  Arjun  |  02/09/23  |  02/14/23  |  D  |
|  F07  |  Vertex has changeable colour transparency  |  Kyle  |  02/14/23  |  02/15/23  |  D  |
|  F08  |  Segment has changeable colour transparency  |  Kyle  |  02/15/23  |  02/15/23  |  D  |
|  F09  |  Polygon has changeable thickness  | Arjun |  02/09/23  |  02/14/23  |  D  |
|  F10  |  Vertex has changeable thickness  |  Kyle  |  02/13/23  |  02/13/23  |  D  |
|  F11  |  Segment has changeable thickness  |  Kyle  |  02/13/23  |  02/13/23  |  D  |
|  F12  |  User can switch into debug mode for different colour display  |  Derron  |  02/20/23  |  02/22/23  |  D  |
|  F13  |  Debug mode displays the centroids in a red colour  |  Derron/Arjun  |  02/20/23  |  02/22/23  |  D  |
|  F14  |  Debug mode displays the neighbourhood relations in grey |  Derron/Arjun  |  02/20/23  |  02/22/23  |  D  |
|  F15  |  Debug mode displays the polygon, vertices, and segments in black and purple |  Derron  |  02/20/23  |  02/22/23  |  D  |
|  F16  |  User can switch between grid meshes or irregular mesh |  Kyle  |  02/24/23 | 02/24/23 |  D  |
|  F17  |  Points are randomly generated to construct the irregular meshes  |  Derron  |  02/22/23  | 02/25/23 |  D |
|  F18  |  Voronoi diagram for each point is calculated  |  Derron  |  02/22/23  |  02/25/23  |  D  |
|  F19  |  Each originally generated voronoi point is converted to a centroid to relax the mesh |  Derron  |  02/22/23  | 02/24/23 |  D  |
|  F20  |  User can select to relax the mesh or not  |  Kyle/Derron  |  02/24/23  |  02/24/23  |  D  |
|  F21  |  Neighbourhood relations are computed using Delaunay's triangulation |  Arjun  |  02/22/23  | 02/27/23  |  D  |
|  F22  |  User can enter "help" mode if a -h argument is inputted when running generator |  Kyle  |  02/24/23  |  02/24/23  |  D  |
|  F23  |  User can select the number of polygons they wish to generate through the command line  |  Kyle/Derron  |  02/24/23  |  02/24/23  |  D  |

## Documentation
To get thickness of vertices and lines, extract the thickness from the properties list using the key 'thickness'.

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

## Format for user input when running visualizer main file
Add '-X' after first 2 arguments to enter debug mode. Anything else is taken as default visualization.
