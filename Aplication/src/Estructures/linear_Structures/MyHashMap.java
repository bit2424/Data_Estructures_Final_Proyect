package Estructures.linear_Structures;

public interface MyHashMap<K,V> {
	
	public void put(K key, V value);
	public V get(K key);
	public boolean contains(K key);
	public boolean delete(K key);
	public boolean isEmpty();
}
