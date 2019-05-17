package Estructures.Graphs;

import Estructures.auxiliary_estructures.GreaterKeyException;
import Estructures.auxiliary_estructures.HeapUnderFlowException;
import Estructures.auxiliary_estructures.UnderflowException;

import java.util.ArrayList;

public interface IGraph<V,E extends Comparable<E>> {
    void insertVertex(V value);
    void insertEdge( int position1, int position2 , E conection);
    void deleteVertex(int positionVertex);
    void deleteEdge( int position1, int position2, E conection);
    void deleteAllEdge( int position1, int position2);
    ArrayList<VertexL<V, E>> getVerticesL();
    ArrayList<VertexM<V>> getVertexM();
    ArrayList<Integer> BFS(int startPosition) throws UnderflowException;
    ArrayList<Integer> DFS(int startPosition);
    ArrayList<ArrayList<Integer>> DFS();
    ArrayList<Integer> Prim(int startPosition) throws GreaterKeyException, HeapUnderFlowException;
    ArrayList<Integer> Kruskal(int startPosition);
    ArrayList<int[]> Dijsktra(int startPosition);
    int[][] Floyd_Warshal();
}
