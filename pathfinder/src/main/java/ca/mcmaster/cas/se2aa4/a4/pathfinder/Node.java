package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private final int index;
    private static List<Integer> usedIndices = new ArrayList<>();

    public Node(int index){
        // Checks if the index is already used by another node, if so, sets new index to 1 higher than current highest index.
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
}
