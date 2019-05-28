package Structures.Graphs;

public class EdgeL<V extends Comparable<V>, E extends Comparable<E>> implements Comparable<EdgeL<V, E>> {

    private VertexL<V, E> u;
    private VertexL<V, E> v;
    private E weight;

    public EdgeL(VertexL<V, E> u, VertexL<V, E> v, E weight){
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public VertexL<V, E> getOriginVertex(){
        return u;
    }

    public void setOriginVertex(VertexL<V, E> u){
        this.u = u;
    }

    public VertexL<V, E> getDestinationVertex() {
        return v;
    }

    public void setDestinationVertex(VertexL<V, E> toVertex) {
        this.v = toVertex;
    }

    public E getWeight() {
        return weight;
    }

    public void setWeight(E weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(EdgeL<V, E> o) {
        return weight.compareTo(o.getWeight());
    }
}

