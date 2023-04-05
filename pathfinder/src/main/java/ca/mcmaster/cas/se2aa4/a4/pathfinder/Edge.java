package ca.mcmaster.cas.se2aa4.a4.pathfinder;

public class Edge {

    private final Node n1, n2;
    private final int weight;


    public Edge(Node n1, Node n2, int weight){
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public boolean equals(Edge other) {
        return super.equals(other) || (n2.equals(other.getN1Idx()) && n1.equals(other.getN2Idx()));
    }

    public boolean equals(int n1Idx, int n2Idx) {
        return (n1.equals(n1Idx) && n1.equals(n2Idx)) || (n2.equals(n1Idx) && n1.equals(n2Idx));
    }

    public boolean n1Equals(int index){
        return this.n1.equals(index);
    }


    public int getN1Idx() {
        return n1.getIndex();
    }
    public int getN2Idx() {
        return n2.getIndex();
    }
}
