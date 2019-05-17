package Estructures.Graphs;

import Estructures.auxiliary_estructures.GreaterKeyException;
import Estructures.auxiliary_estructures.HeapUnderFlowException;
import Estructures.auxiliary_estructures.UnderflowException;

import java.util.ArrayList;

public interface IAdjacencyListGraph <V, E extends Comparable<E>> {

    void insertVertex(V value);
    void insertEdge(int position1, int position2, E connection);
    void deleteVertex(int positionVertex);
    boolean deleteAllEdge(int position1, int position2);
    void deleteEdge(int position1, int position2, E connection);
    ArrayList<VertexL<V, E>> getVertices();
    ArrayList<Integer> BFS(int startPosition) throws UnderflowException;
    ArrayList<ArrayList<Integer>> BFS() throws UnderflowException;
    ArrayList<Integer> DFS(int startPosition);
    ArrayList<ArrayList<Integer>> DFS();
    ArrayList<Integer> Prim(int startPosition) throws GreaterKeyException, HeapUnderFlowException;
    ArrayList<Integer> Kruskal(int startPosition);
}
