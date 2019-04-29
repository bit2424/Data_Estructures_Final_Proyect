package Estructures.linear_Structures;

public class NodoHash<K,V> {
	K Actualkey;
	V value;
	
	public NodoHash(K actualkey, V value) {
		Actualkey = actualkey;
		this.value = value;
	}
	
	public K getActualkey() {
		return Actualkey;
	}
	public void setActualkey(K actualkey) {
		Actualkey = actualkey;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
}
