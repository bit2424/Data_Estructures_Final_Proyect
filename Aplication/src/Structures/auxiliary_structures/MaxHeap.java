package Structures.auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;
import Structures.auxiliary_structures.interfaces_auxiliary_structures.IMaxHeap;

import java.util.ArrayList;

public class MaxHeap<V extends Comparable<V>> implements IMaxHeap<V> {

    protected ArrayList<V> heap;
    protected int heap_size;
    protected int max_size;

    public MaxHeap(ArrayList<V> heap, int max_size) {
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
    public void max_heapify(int i) {
        int left = left(i);
        int right = right(i);
        int largest;
        if(left < heap_size && heap.get(left).compareTo(heap.get(i)) > 0)
            largest = left;
        else
            largest = i;
        if(right < heap_size && heap.get(right).compareTo(heap.get(largest)) > 0)
            largest = right;
        if(largest != i){
            exchange(i, largest);
            max_heapify(largest);
        }
    }

    @Override
    public void build_max_heap() {
        for(int i =  heap_size/2 - 1; i >= 0; i--)
            max_heapify(i);
    }

    @Override
    public void heapsort() {
        build_max_heap();
        int temp_heap_size = heap_size;
        for(int i = heap_size - 1; i >= 1; i--){
            exchange(0, i);
            heap_size--;
            max_heapify(0);
        }
        heap_size = temp_heap_size;
    }
}
