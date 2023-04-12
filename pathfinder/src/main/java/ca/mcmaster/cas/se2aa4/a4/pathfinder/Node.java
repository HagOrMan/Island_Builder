package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private int index;
    private final int elevation;
    private final String name;
    private static List<Integer> usedIndices = new ArrayList<>();

    public Node(int index){
        addIndex(index);
        this.elevation = 0;
        this.name = "";
    }
    public Node(int index, int elevation){
        addIndex(index);
        this.elevation = elevation;
        this.name = "";
    }
    public Node(int index, int elevation, String name){
        addIndex(index);
        this.elevation = elevation;
        this.name = name;
    }
    public Node(int index, String name){
        addIndex(index);
        this.elevation = 0;
        this.name = name;
    }

    // Checks if the index is already used by another node, if so, sets new index to 1 higher than current highest index.
    private void addIndex(int index){
        if (!usedIndices.contains(index)){
            this.index = index;
        }
        else{
            this.index = Collections.max(usedIndices) + 1;
        }
        usedIndices.add(this.index);
    }



    public boolean equals(int index) {
        return this.index == index;
    }

    public int getIndex() {
        return index;
    }

    public int getElevation() {
        return elevation;
    }

    public String getName() {
        return name;
    }
}
