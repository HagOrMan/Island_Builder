package ca.mcmaster.cas.se2aa4.a4.pathfinder;

public class Edge {

    private final int n1Idx, n2Idx;
    private final int weight;


    public Edge(int n1Idx, int n2Idx, int weight){
        this.n1Idx = n1Idx;
        this.n2Idx = n2Idx;
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public boolean equals(int n1Idx, int n2Idx) {
        return this.n1Idx == n1Idx && this.n2Idx == n2Idx;
    }

    public int getN1Idx() {
        return n1Idx;
    }
    public int getN2Idx() {
        return n2Idx;
    }
}
