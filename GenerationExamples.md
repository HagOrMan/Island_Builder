# Island Generation

## Lagoon 
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

## Heatmap
To run the main island while seeing a heatmap, copy and paste the following: 
cd island
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -elevation volcano -soil dry -lake 2 -river 4 -aquifer 2 -seed 6812161995636894525 -heatmap moisture
cd ..
cd visualizer
java -jar visualizer.jar -i ../island/island.mesh -o island.svg

The same customization as outlined in the second example can be applied to the heatmap. 

# Regular Mesh Generation

## Generate a Grid
To generate a grid with the following specifications:
- polygon transparency of `200` (`-pa`)
- segment thickness of `10` (`-st`)
```
cd generator 
java -jar generator.jar sample.mesh -pa 200 -st 10

cd ..

cd visualizer
java -jar visualizer.jar ../generator/sample.mesh sample.svg 
```

## Generate a Grid in Debug Mode
```
cd generator 
java -jar generator.jar sample.mesh

cd ..

cd visualizer 
java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg -X
```

## Generate an Irregular Mesh
To generate an irregular mesh (`-ir`) with the following specifications: 
- `200` polygons (`-np`)
- relaxed `5` times (`-rl`)
- polygon transparency of `200` (`-pa`)
- segment thickness of `10` (`-st`)
- everything else `default`

```
cd generator 
java -jar generator.jar sample.mesh -ir -np 200 -rl 5 -pa 200 -st 10

cd ..

cd visualizer
java -jar visualizer.jar ../generator/sample.mesh sample.svg 
```
