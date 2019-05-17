package Estructures.Graphs;

public class EdgeL<V, E extends Comparable<E>> {

    private VertexL<V, E> v;
    private E value;

    public EdgeL(VertexL<V, E> v, E value){
        this.v = v;
        this.value = value;
    }

    public VertexL<V, E> getVertex() {
        return v;
    }

    public void setVertex(VertexL<V, E> v) {
        this.v = v;
    }

    public E getWeight() {
        return value;
    }

    public void setWeight(E value) {
        this.value = value;
    }
}

