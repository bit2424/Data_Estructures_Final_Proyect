package Estructures.Graphs;

import java.util.ArrayList;
import Estructures.linear_Structures.Queue;

public class AdjacencyListGraph<T> implements IAdjacencyListGraph<T> {

    private boolean directed;
    private boolean weighted;
    private int time;
    private ArrayList<Vertex<T>> vertices;

    public AdjacencyListGraph(boolean derected, boolean weighted, Vertex<T> vertex){
        this.directed = directed;
        this.weighted = weighted;
        vertices = new ArrayList<>();
        vertices.add(vertex);
    }

    @Override
    public void insertVertex(T value){
        Vertex<T> u = new Vertex<>(value);
        vertices.add(u);
    }

    @Override
    public void insertVertex(T value, Edge<T> edgeToU){
        Vertex<T> u = new Vertex<>(value);
        ArrayList<Edge<T>> adjacencyList = new ArrayList<>();
        adjacencyList.add(edgeToU);
        connectVertex(u, adjacencyList);
        vertices.add(u);
    }

    @Override
    public void insertVertex(T value, ArrayList<Edge<T>> adjacentVertices) {
        Vertex<T> u = new Vertex<>(value);
        connectVertex(u, adjacentVertices);
        vertices.add(u);
    }


    private void connectVertex(Vertex<T> u, ArrayList<Edge<T>> adjacentVertices) {
        u.addAdjacencyList(adjacentVertices);
        if(!isDirected()){
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
                Edge<T> edge = new Edge<>(u, u.getAdjacencyList().get(i).getWeight());
                v.addAdjacentVertex(edge);
            }
        }
    }

    @Override
    public void addEdge(Vertex<T> u, Vertex<T> v, int weight) {
        Edge<T> edgeToV = new Edge<>(v, weight);
        u.addAdjacentVertex(edgeToV);
        if(!isDirected()){
            if(!u.equals(v)) {
                Edge<T> edgeToU = new Edge<>(u, weight);
                v.addAdjacentVertex(edgeToU);
            }
        }
    }

    private int findEdge(Vertex<T> u, Vertex<T> v, int weight){
        for(int i = 0; i < u.getAdjacencyList().size(); i++){
            if(v.equals(u.getAdjacencyList().get(i).getVertex()) && weight == u.getAdjacencyList().get(i).getWeight()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteEdge(Vertex<T> u, int i) throws IndexOutOfBoundsException {
        Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
        int edgeIndex = findEdge(v, u, u.getAdjacencyList().get(i).getWeight());
        if(edgeIndex >= 0){
            if(!isDirected()){
                v.getAdjacencyList().remove(findEdge(v, u, u.getAdjacencyList().get(i).getWeight()));
            }
            u.getAdjacencyList().remove(i);
        }
    }

    @Override
    public Vertex<T> deleteVertex(int index) throws IndexOutOfBoundsException {
        Vertex<T> u = vertices.get(index);
        for(int i = 0; i < u.getAdjacencyList().size(); i++){
            Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
            v.getAdjacencyList().remove(findEdge( v, u, u.getAdjacencyList().get(i).getWeight()));
        }
        return vertices.remove(index);
    }

    @Override
    public ArrayList<Vertex<T>> BFS(int initialVertexIndex, int targetVertexIndex) {
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setDistance(-1);
            vertices.get(i).setPredecessor(null);
        }
        Vertex<T> initialVertex = vertices.get(initialVertexIndex);
        Vertex<T> targetVertex = vertices.get(targetVertexIndex);
        initialVertex.setColor((byte)1);
        initialVertex.setDistance(0);
        Queue<Vertex<T>> nextToVisit = new Queue<>();
        ArrayList<Vertex<T>> visited = new ArrayList<>();
        nextToVisit.offer(initialVertex);
        visited.add(initialVertex);
        while(nextToVisit.peek() != null){
            Vertex<T> u = nextToVisit.poll();
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
                if (v.getColor() == 0){
                    visited.add(v);
                    v.setColor((byte)1);
                    v.setDistance(u.getDistance() + 1);
                    v.setPredecessor(u);
                    nextToVisit.offer(v);
                }
            }
            u.setColor((byte)2);
        }
        ArrayList<Vertex<T>> path = new ArrayList<>();
        if(visited.contains(targetVertex)){
            Vertex<T> currentVertex = targetVertex;
            while(currentVertex != null){
                path.add(0, currentVertex);
                currentVertex = currentVertex.getPredecessor();
            }
            return path;
        }
        return null;
    }

    @Override
    public ArrayList<ArrayList<Vertex<T>>> DFS() {
        ArrayList<ArrayList<Vertex<T>>> forest = new ArrayList<>();
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setPredecessor(null);
        }
        time = 0;
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getColor() == 0){
                ArrayList<Vertex<T>> tree = new ArrayList<>();
                tree.add(vertices.get(i));
                DFS_Visit(tree, i);
                forest.add(tree);
            }
        }
        return forest;
    }

    private void DFS_Visit(ArrayList<Vertex<T>> tree, int i){
        time ++;
        Vertex<T> u = vertices.get(i);
        u.setInitialTime(time);
        u.setColor((byte)1);
        for(int j = 0; j < u.getAdjacencyList().size(); j++){
            Vertex<T> v = u.getAdjacencyList().get(j).getVertex();
            if(v.getColor() == 0){
                tree.add(v);
                v.setPredecessor(u);
                DFS_Visit(tree, vertices.indexOf(v));
            }
        }
        u.setColor((byte)2);
        time ++;
        u.setFinalTime(time);
    }

    public ArrayList<Vertex<T>> getVertices(){
        return vertices;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isWeighted() {
        return weighted;
    }
}
