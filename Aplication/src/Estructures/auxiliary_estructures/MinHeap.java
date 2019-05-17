package Estructures.auxiliary_estructures;

import java.util.ArrayList;

public class MinHeap<V extends Comparable<V>> implements IMinHeap<V> {

    protected ArrayList<V> heap;
    protected int heap_size;
    protected int max_size;

    public MinHeap(ArrayList<V> heap, int max_size) {
        this.max_size = max_size;
        heap_size = heap.size();
        this.heap = heap;
    }

    public int getHeap_size() {
        return heap_size;
    }

    public int getMax_size() {
        return max_size;
    }

    @Override
    public int parent(int i) {
        return (i%2 == 0)? i/2 - 1 : i/2;
    }

    @Override
    public int left(int i) {
        return i*2 + 1;
    }

    @Override
    public int right(int i) {
        return i*2 + 2;
    }

    public V get(int i) throws HeapUnderFlowException {
        if(heap_size == 0)
            throw new HeapUnderFlowException();
        return heap.get(i);
    }

    protected void exchange(int index1, int index2){
        V temp = heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    @Override
    public void min_heapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest;
        if(left < heap_size && heap.get(left).compareTo(heap.get(i)) < 0)
            smallest = left;
        else
            smallest = i;
        if(right < heap_size && heap.get(right).compareTo(heap.get(smallest)) < 0)
            smallest = right;
        if(smallest != i){
            exchange(i, smallest);
            min_heapify(smallest);
        }
    }

    @Override
    public void build_min_heap() {
        for(int i =  heap_size/2 - 1; i >= 0; i--)
            min_heapify(i);
    }

    @Override
    public void heapsort() {
        build_min_heap();
        int temp_heap_size = heap_size;
        for(int i = heap_size - 1; i >= 1; i--){
            exchange(0, i);
            heap_size--;
            min_heapify(0);
        }
        heap_size = temp_heap_size;
    }
}


