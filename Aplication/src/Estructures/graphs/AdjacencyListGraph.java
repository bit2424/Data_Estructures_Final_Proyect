package Estructures.Graphs;

import java.util.ArrayList;

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
            Edge<T> edgeToU = new Edge<>(u, weight);
            v.addAdjacentVertex(edgeToU);
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
    public void deleteEdge(Vertex<T> u, int i) {
        if(!isDirected()){
            Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
            v.getAdjacencyList().remove(findEdge(v, u, u.getAdjacencyList().get(i).getWeight()));
        }
        u.getAdjacencyList().remove(i);
    }

    @Override
    public Vertex<T> deleteVertex(int index) {
        Vertex<T> u = vertices.get(index);
        for(int i = 0; i < u.getAdjacencyList().size(); i++){
            Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
            v.getAdjacencyList().remove(findEdge(v, u, u.getAdjacencyList().get(i).getWeight()));
        }
        return vertices.remove(index);
    }

    @Override
    public Vertex<T> BFS(int index) throws UnderflowException {
        Vertex<T> s = vertices.get(index);
        s.setColor((byte)1);
        s.setDistance(0);
        Queue<Vertex<T>> q = new Queue();
        q.enqueue(s);
        while(!q.isEmpty()){
            Vertex<T> u = q.dequeue();
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                Vertex<T> v = u.getAdjacencyList().get(i).getVertex();
                if(v.getColor() == 0){
                    v.setColor((byte)1);
                    v.setDistance(u.getDistance() + 1);
                    v.setPredecessor(u);
                    q.enqueue(v);
                }
            }
            u.setColor((byte)2);
        }
        return s;
    }

    @Override
    public ArrayList<Vertex<T>> DFS() {
        ArrayList<Vertex<T>> forest = new ArrayList<>();
        time = 0;
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getColor() == 0){
                DFS_Visit(i);
                forest.add(vertices.get(i));
            }
        }
        return forest;
    }

    private void DFS_Visit(int i){
        time ++;
        Vertex<T> u = vertices.get(i);
        u.setInitialTime(time);
        u.setColor((byte)1);
        for(int j = 0; j < u.getAdjacencyList().size(); j++){
            Vertex<T> v = u.getAdjacencyList().get(j).getVertex();
            if(v.getColor() == 0){
                v.setPredecessor(u);
                DFS_Visit(vertices.indexOf(v));
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
