package Estructures.auxiliary_estructures;

public class QueueNode<T> {

	private T data;
	private QueueNode<T> next;
	
	public QueueNode(T data) {
		this.data = data;
		next = null;
	}
	
	public T getData() {
		return data;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public QueueNode<T> getNext() {
		return next;
	}
	
	public void setNext(QueueNode<T> next) {
		this.next = next;
	}
	
}
