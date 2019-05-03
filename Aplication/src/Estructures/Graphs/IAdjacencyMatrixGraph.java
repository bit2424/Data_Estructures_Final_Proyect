package Estructures.Graphs;

import java.util.ArrayList;

public interface IAdjacencyMatrixGraph<V,E extends Comparable<E>> {
    void addVertex(V v);

    void addConection(int position1 , int  position2 , E conection);

    ArrayList<VertexM> getAdyacencyList(int positionNode);

    void deleteVertex(int positionNode);

    void deleteEdge(int position1, int position2);

    ArrayList<VertexM<V>> DFS(int startPosition);

    ArrayList<VertexM<V>> BFS(int startPosition);

    ArrayList<ArrayList<VertexM<V>>> DFS();

    ArrayList<ArrayList<VertexM<V>>> BFS();

}
