package Estructures.graphs;


import java.util.ArrayList;

public interface IAdjacencyListGraph <T> {

    void insertVertex(T value);
    void insertVertex(T value, Edge<T> edgeToU);
    void insertVertex(T value, ArrayList<Edge<T>> adjacentVertices);
    void addEdge(Vertex<T> u, Vertex<T> v, int weight);
    void deleteEdge(Vertex<T> u, int i);
    Vertex<T> deleteVertex(int index);
    Vertex<T> BFS(int index) throws UnderflowException;
    ArrayList<Vertex<T>> DFS();

}
