package Estructures.graphs;

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
		QueueNode<T> newNode = new QueueNode<T>(data);
		if(isEmpty()) {
			tail = newNode;
			head = tail;
		}
		else {
			tail.setNext(newNode);
			tail = newNode;
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
			size--;
			return dequeuedNode.getData();
		}
	}
	
	public boolean isEmpty() {
		return head == null && tail == null;
	}

}
