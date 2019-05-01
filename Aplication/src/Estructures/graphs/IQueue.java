package Estructures.graphs;

public interface IQueue<T> {
	
	void enqueue(T data);
	T dequeue() throws UnderflowException;

}
