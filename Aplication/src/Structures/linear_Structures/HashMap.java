package Structures.linear_Structures;

public class HashMap<K,V> implements MyHashMap<K, V> {
	
	private Object container[] = new Object[200];
	
	@Override
	public void put(K key, V value) {
		int P = (System.identityHashCode(key) % container.length);
		int c = VerifyCollisions(P);
		container[c] = new NodoHash<K,V>(key,value);
		System.out.println(container[c] + " " + c);
	}
	
	//Double-Hashing 
	private int VerifyCollisions(int p) {
		int a = p;
		while(container[a] != null){
			System.out.println(a);
			a = (java.util.Objects.hash(container[a], a)%container.length);
		}
		return a;
	}

	@Override
	public V get(K key) {
		int a = System.identityHashCode(key) % container.length;
		while(container[a] != null && ((NodoHash)container[a]).getActualkey() != key){
			a = (java.util.Objects.hash(container[a], a)%container.length);
		}
		System.out.println("Obtuve" + " " + a);
		return (V)((NodoHash)container[a]).getValue();
	}

	@Override
	public boolean contains(K key) {
		
		int a = System.identityHashCode(key) % container.length;
		
		while(container[a] != null && ((NodoHash)container[a]).getActualkey() != key){
			a = (java.util.Objects.hash(container[a], a)%container.length);
		}
		System.out.println("Obtuve" + " " + a);
		
		if(container[a] != null) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean delete(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
