# Backlog
This file contains the backlog information for Assignment 2 and 3. These were group projects, so I am not the only one who worked on every feature.
<br><br> Assignment 4 features can be found in the linked project as issues.

## A2
The backlog for assignment 2 does not include our definition of a feature being done, only the actual backlog.

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

## A3
The backlog for assignment 3 includes our definition of a feature being done, as well as the actual backlog.

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
|  F#  |  description  |  contributor  |  dd/mm/yy  |  dd/mm/yy  |  ...  |