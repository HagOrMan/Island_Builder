# Assignment A2: Mesh Generator

  - Kyle Hagerman [hagermak@mcmaster.ca]
  - Arjun Karthik [kartha4@mcmaster.ca]
  - Derron Li [li1578@mcmaster.ca]

## How to run the product

_This section needs to be edited to reflect how the user can interact with the feature released in your project_

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
To visualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tools like `rsvg-convert`

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog

### Definition of Done

-- Each feature compiles properly on maven, following all necessary business requirements.  --

### Product Backlog

| Id | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
|  F01  |  Draw segments between vertices to visualize squares  |  Everyone  |  02/03/23  |  02/05/23  |  D  |
|  F02  |  Mesh generates at a precision model of 2 decimal places  |  S  |  .  |  .  |  B  |
|  F03  |  Mesh has minimal vertices  |  S  |  .  |  .  |  B  |
|  F04  |  Mesh has minimal segments  |  S  |  .  |  .  |  B  |
|  F05  |  Mesh has minimal polygons  |  S  |  .  |  .  |  B  |
|  F06  |  Polygon has changeable colour transparency  |  S  |  .  |  .  |  B  |
|  F07  |  Vertex has changeable colour transparency  |  S  |  .  |  .  |  B  |
|  F08  |  Segment has changeable colour transparency  |  S  |  .  |  .  |  B  |
|  F09  |  Polygon has changeable thickness  |  S  |  .  |  .  |  B  |
|  F10  |  Vertex has changeable thickness  |  S  |  .  |  .  |  B  |
|  F11  |  Segment has changeable thickness  |  S  |  .  |  .  |  B  |
|  F12  |  User can switch into debug mode for different colour display  |  S  |  .  |  .  |  B  |
|  F  |  Template  |  S  |  .  |  .  |  B  |


## ideas
segments are in 3D array. First [] says if horizontal or vertical segment. Next two [] say position

vertices in 2D array



