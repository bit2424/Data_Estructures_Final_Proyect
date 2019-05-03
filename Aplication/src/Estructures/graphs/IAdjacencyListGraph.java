package Estructures.Graphs;

import java.util.ArrayList;

public interface IAdjacencyListGraph <T> {

    void insertVertex(T value);
    void insertVertex(T value, Edge<T> edgeToU);
    void insertVertex(T value, ArrayList<Edge<T>> adjacentVertices);
    void addEdge(Vertex<T> u, Vertex<T> v, int weight);
    void deleteEdge(Vertex<T> u, Vertex<T> v, int weight);
    Vertex<T> deleteVertex(int index);
    ArrayList<Vertex<T>> BFS(int initialVertexIndex, int targetVertexIndex);
    ArrayList<ArrayList<Vertex<T>>> DFS();

}
