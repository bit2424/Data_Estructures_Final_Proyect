package Estructures.Graphs;

import java.util.ArrayList;

public interface IGraph<V,E extends Comparable<E>> {
    void insertVertex(V value);
    void insertEdge( int position1, int position2 , E conection);
    void deleteVertex(int positionVertex);
    void deleteEdge( int position1, int position2, E conection);
    void deleteAllEdge( int position1, int position2);
    ArrayList<Vertex<V>> getVertex();
    ArrayList<Integer> BFS(int startPosition);
    ArrayList<Integer> DFS(int startPosition);
    ArrayList<ArrayList<Integer>> DFS();
    ArrayList<Integer> Prim(int startPosition);
    ArrayList<Integer> Kruskal(int startPosition);
    ArrayList<int[]> Dijsktra(int startPosition);
    int[][] Floyd_Warshal();
}
