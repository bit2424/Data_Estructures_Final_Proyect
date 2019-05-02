package Estructures.Graphs;

public interface IQueue<T> {
	
	void enqueue(T data);
	T dequeue() throws UnderflowException;

}
