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


    public boolean equals(Node n1Other, Node n2Other) {
        return (n1.equals(n1Other) && n1.equals(n2Other)) || (n2.equals(n1Other) && n1.equals(n2Other));
    }


    public Node getN1() {
        return n1;
    }
    public Node getN2() {
        return n2;
    }
}
