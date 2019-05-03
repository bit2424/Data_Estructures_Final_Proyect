package Estructures.Graphs;

public class Edge<T> {

    private Vertex<T> v;
    private int weight;

    public Edge(Vertex<T> v, int weight){
        this.v = v;
        this.weight = weight;
    }

    public Vertex<T> getVertex() {
        return v;
    }

    public void setVertex(Vertex<T> v) {
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
