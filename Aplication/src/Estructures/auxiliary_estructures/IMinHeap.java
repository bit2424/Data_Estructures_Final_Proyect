package Estructures.auxiliary_estructures;

public interface IMinHeap<V extends Comparable<V>> {
	
	void min_heapify(int i);
	void build_min_heap();
	void heapsort();
	int parent(int i);
	int left(int i);
	int right(int i);

}
