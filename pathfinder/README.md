# Readme for Pathfinder

## Basic Information
Author: Kyle Hagerman, hagermak@mcmaster.ca
Rationale: Developing a pathfinder algorithm for the path between nodes, and allows users to extend and develop their own algorithms.
Currently available algorithm is dijkstra's shortest path algorithm.

### How to extend the library
Extending the library is simple:
1. Create a new class which implements the pathfinder interface
2. Develop your own algorithm which follows the contract of the pathfinder interface's method:
   Input a graph and source node, and return a list of all nodes and the node before them in the shortest path to the source.
   For getting the path from this map, work backwards from any node to the source node, as each node maps to the node which 
   comes before it in the shortest path to the source.

### Other information
Extending MyGraph allows for new graph representations to be used. Should a new algorithm be developed which implements the
PathFinding interface, and this algorithm is optimal with a different graph representation than that in the Graph class,
simply extend MyGraph and input that into the newly made path finding class.
