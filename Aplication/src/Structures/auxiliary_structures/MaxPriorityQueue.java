package Structures.auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.SmallerKeyException;
import Structures.auxiliary_structures.interfaces_auxiliary_structures.IMaxPriorityQueue;

import java.util.ArrayList;

public class MaxPriorityQueue<V extends Comparable<V>> extends MaxHeap<V> implements IMaxPriorityQueue<V> {

    public MaxPriorityQueue(ArrayList<V> heap, int max_size){
        super(heap, max_size);
        build_max_heap();
    }

    @Override
    public void insert(V element) throws SmallerKeyException, HeapUnderFlowException {
        heap_size++;
        heap.add(element);
        increase_key(heap_size - 1, element);
    }

    @Override
    public V maximum() throws HeapUnderFlowException {
        return get(0);
    }

    @Override
    public V extract_max() throws HeapUnderFlowException {
        if(isEmpty())
            throw new HeapUnderFlowException();
        V max = heap.set(0, heap.get(heap_size - 1));
        heap.remove(heap_size - 1);
        heap_size--;
        max_heapify(0);
        return max;
    }

    @Override
    public void increase_key(int i, V value) throws SmallerKeyException, HeapUnderFlowException {
        if(value.compareTo(get(i)) < 0)
            throw new SmallerKeyException();
        heap.set(i, value);
        for(; i >= 1 && get(parent(i)).compareTo(get(i)) < 0; i = parent(i))
            exchange(i, parent(i));
    }

    @Override
    public boolean isEmpty() {
        return heap_size == 0;
    }
}
