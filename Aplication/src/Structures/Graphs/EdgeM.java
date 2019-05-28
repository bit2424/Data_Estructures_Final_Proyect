package Structures.Graphs;

public class EdgeM<V,E extends Comparable<E>> {
    private E value;
    private VertexM<V> origin;
    private VertexM<V> destination;

    public EdgeM(E value,VertexM<V> origin,VertexM<V> destination) {
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }


    public VertexM<V> getOrigin() {
        return origin;
    }

    public void setOrigin(VertexM<V> origin) {
        this.origin = origin;
    }

    public VertexM<V> getDestination() {
        return destination;
    }

    public void setDestination(VertexM<V> destination) {
        this.destination = destination;
    }
}
