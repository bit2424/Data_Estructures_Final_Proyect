package Structures.auxiliary_structures.interfaces_auxiliary_structures;

import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.SmallerKeyException;

public interface IMaxPriorityQueue<V extends Comparable<V>> {

	void insert(V element) throws SmallerKeyException, HeapUnderFlowException;
	V maximum() throws HeapUnderFlowException;
	V extract_max() throws HeapUnderFlowException;
	void increase_key(int i, V value) throws SmallerKeyException, HeapUnderFlowException;
	boolean isEmpty();
	
}
