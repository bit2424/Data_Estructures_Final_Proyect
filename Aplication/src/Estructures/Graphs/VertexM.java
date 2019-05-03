package Estructures.Graphs;

public class VertexM<V> {
    V value;

    public VertexM(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
