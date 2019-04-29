package Estructures.linear_Structures;

public class PriorityQueue<K extends Comparable<K>,V> implements MyQueue<K> {
	Heap<K> elements;
	
	public PriorityQueue (boolean Type) {
		elements = new Heap<>(Type);
	}

	@Override
	public void offer(K obj) {
		elements.add(obj);
	}

	@Override
	public K poll() {
		return elements.obtain();
	}

	@Override
	public K peek() {
		return elements.getTop();
	}

}

