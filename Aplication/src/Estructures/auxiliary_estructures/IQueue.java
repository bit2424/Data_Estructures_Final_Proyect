package Estructures.auxiliary_estructures;

public interface IQueue<T> {
	
	public void enqueue(T data);
	public T dequeue() throws UnderflowException;

}
