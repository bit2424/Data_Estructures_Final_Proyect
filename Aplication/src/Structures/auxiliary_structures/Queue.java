package Structures.auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;
import Structures.auxiliary_structures.interfaces_auxiliary_structures.IQueue;

public class Queue<T> implements IQueue<T> {
	
	private QueueNode<T> head;
	private QueueNode<T> tail;
	private int size;
	
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public QueueNode<T> getHead(){
		return head;
	}
	
	public QueueNode<T> getTail(){
		return tail;
	}

	@Override
	public void enqueue(T data) {
		QueueNode<T> newNode = new QueueNode<>(data);
		if(isEmpty()) {
			tail = newNode;
			head = newNode;
		}
		else {
			tail.setNext(newNode);
			tail = tail.getNext();
		}
		size++;
	}

	@Override
	public T dequeue() throws UnderflowException {
		if(isEmpty()) {
			throw new UnderflowException("Underflow error.");
		}
		else {
			QueueNode<T> dequeuedNode = head;
			head = head.getNext();
			if(size == 1){
				tail = null;
			}
			size--;
			return dequeuedNode.getData();
		}
	}
	
	public boolean isEmpty() {
		return head == null && tail == null;
	}

}
