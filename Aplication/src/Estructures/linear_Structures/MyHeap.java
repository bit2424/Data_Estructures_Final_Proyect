package Estructures.linear_Structures;

public interface MyHeap<V>{
	public void add(V element);
	public V obtain();
	public void buidHeap(V Nelemnts[], boolean type);
	public V getTop();
	public void haepSort(V arr[]);
}
