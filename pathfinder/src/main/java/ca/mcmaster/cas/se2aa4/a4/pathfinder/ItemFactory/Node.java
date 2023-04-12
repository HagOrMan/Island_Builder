package ca.mcmaster.cas.se2aa4.a4.pathfinder.ItemFactory;


public class Node {

    private final int index;
    private int elevation;
    private String name;

    Node(int index){
        this.index = index;
        this.elevation = 0;
        this.name = "";
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

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setName(String name) {
        this.name = name;
    }

}
