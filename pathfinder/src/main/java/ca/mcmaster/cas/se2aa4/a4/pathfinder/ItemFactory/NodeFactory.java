package ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NodeFactory {

    private final Set<Integer> indices;

    public NodeFactory(){
        indices = new HashSet<>();
    }

    public Node makeNode(int index){

        // Checks if the node index is already in use by another node.
        if (!indices.contains(index)){
            indices.add(index);
            return new Node(index);
        }

        // Makes the new index 1 greater than the current max in the used indices list.
        int newIndex = Collections.max(indices) + 1;
        indices.add(newIndex);
        return new Node(newIndex);

    }

}
