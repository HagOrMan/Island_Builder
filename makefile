g:
	cd generator && java -jar generator.jar sample.mesh

gi:
	cd generator && java -jar generator.jar sample.mesh -ir

gt:
	cd generator && java -jar generator.jar sample.mesh -pa 150 -sa 170 -va 200 -pt 3 -st 2 -vt 2

git:
	cd generator && java -jar generator.jar sample.mesh -ir -pa 150 -sa 170 -va 200 -pt 3 -st 2 -vt 2 -np 100 -rl 20

v:
	cd visualizer && java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg

vd:
	cd visualizer && java -jar visualizer.jar -i ../generator/sample.mesh -o sample.svg -X

s:
	mvn clean install
	mvn compile
	mvn package

run: s g v

rund: s g vd

# run grid with command line args
rungt: s gt v

# run irregular
runi: s gi v

# run irregular with command line args
runit: s git v

# run grid with command line args
rungtd: s gt vd

# run irregular
runid: s gi vd

# run irregular with command line args
runitd: s git vd

.PHONY: generator visualizer

genis:
	cd generator && java -jar generator.jar input.mesh -pt 0 -ir -rl 40 -np 400

genlagoon:
	cd island && java -jar island.jar -o lagoon.mesh -i ../generator/input.mesh -mode lagoon

vislagoon:
	cd visualizer && java -jar visualizer.jar -i ../island/lagoon.mesh -o lagoon.svg

runlag: s genlagoon vislagoon

visisland:
	cd visualizer && java -jar visualizer.jar -i ../island/island.mesh -o island.svg

gencir:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle

gencirvol:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -elevation volcano

gencirel:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -heatmap elevation

genhexel:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -heatmap elevation

gencirmo:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -heatmap moisture

genhexmo:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -heatmap moisture -elevation volcano

gencirelvol:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -heatmap elevation -elevation volcano

genhexelvol:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -heatmap elevation -elevation volcano

gencirmovol:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -heatmap moisture -elevation volcano

genhexmovol:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -heatmap moisture -elevation volcano

runcir: s gencir visisland

genhex:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon

runhex: s genhex visisland

cirel: s gencirel visisland

cirmo: s gencirmo visisland

hexel: s genhexel visisland

hexmo: s genhexmo visisland

cirelvol: s gencirelvol visisland

cirmovol: s gencirmovol visisland

hexelvol: s genhexelvol visisland

hexmovol: s genhexmovol visisland

cirvol: s gencirvol visisland

gencity:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon -elevation volcano -soil wet -lake 60 -river 6 -aquifer 4 -biome warmtemperate -city 20 -seed 2818189228017187034

city: s gencity visisland
