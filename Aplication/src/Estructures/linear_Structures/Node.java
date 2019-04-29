package Estructures.linear_Structures;

public class Node<E> {
	E info;
	Node<E> next;
	Node<E> before;
	
	public Node(E info) {
		this.info = info;
	}
	
	public void addNext(Node<E> insert) {
		next = insert;
	}
	
	public void addBefore(Node<E> insert) {
		before = insert;
	}
	
	public void deleteNext() {
		next = null;
	}
	
	public void deleteBefore() {
		before = null;
	}
	
	public E getInfo() {
		return info;
	}
	
	public void changeInfo(E info) {
		this.info = info;
	}
	
}
