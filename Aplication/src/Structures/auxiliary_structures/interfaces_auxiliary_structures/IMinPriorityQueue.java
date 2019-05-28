package Structures.auxiliary_structures.interfaces_auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.GreaterKeyException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;

public interface IMinPriorityQueue<V extends Comparable<V>> {
	
	void insert(V element) throws GreaterKeyException, HeapUnderFlowException;
	V minimum() throws HeapUnderFlowException;
	V extract_min() throws HeapUnderFlowException;
	void decrease_key(int i, V value) throws GreaterKeyException, HeapUnderFlowException;
	void decrease_key(V originaValue, V newValue) throws GreaterKeyException, HeapUnderFlowException;
	boolean isEmpty();

}
