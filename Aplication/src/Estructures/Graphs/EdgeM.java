package Estructures.Graphs;

public class EdgeM<E extends Comparable<E>> {
    private E value;

    public EdgeM(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
