package Structures.auxiliary_structures.interfaces_auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;

public interface IQueue<T> {
	
	public void enqueue(T data);
	public T dequeue() throws UnderflowException;

}
