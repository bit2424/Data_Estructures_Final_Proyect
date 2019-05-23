package Estructures.Graphs;

import java.util.ArrayList;
import Estructures.auxiliary_estructures.MinPriorityQueue;
import Estructures.auxiliary_estructures.Queue;
import Estructures.auxiliary_estructures.GreaterKeyException;
import Estructures.auxiliary_estructures.HeapUnderFlowException;
import Estructures.auxiliary_estructures.UnderflowException;

public class AdjacencyListGraph<V, E extends Comparable<E>> implements IGraph<V, E> {

    private boolean directed;
    private boolean weighted;
    private int time;
    private int nVertices;
    private ArrayList<VertexL<V, E>> vertices;

    public AdjacencyListGraph(boolean directed, boolean weighted, VertexL<V, E> vertexL){
        this.directed = directed;
        this.weighted = weighted;
        vertices = new ArrayList<>();
        vertices.add(vertexL);
        nVertices = vertices.size();
    }

    @Override
    public void insertVertex(V value){
        VertexL<V, E> u = new VertexL<>(value);
        vertices.add(u);
        nVertices = vertices.size();
    }

    @Override
    public void insertEdge(int position1, int position2, E connection) {
        vertices.get(position1).addAdjacentVertex(new EdgeL<>(vertices.get(position2), connection));
        if(!isDirected() && position1 != position2)
            vertices.get(position2).addAdjacentVertex(new EdgeL<>(vertices.get(position1), connection));
    }

    private boolean deleteIncidentEdgesToAVertex(VertexL<V, E> u, VertexL<V, E> v){
        boolean oneVertexHasBeenDeleted = false;
        for(int i = u.getAdjacencyList().size() - 1; i >= 0; i--){
            if(v.equals(u.getAdjacencyList().get(i).getVertex())) {
                u.deleteFromAdjacencyList(i);
                oneVertexHasBeenDeleted = true;
            }
        }
        return oneVertexHasBeenDeleted;
    }

    @Override
    public void deleteVertex(int index) throws IndexOutOfBoundsException {
        VertexL<V, E> u = vertices.get(index);
        if(!isDirected())
            for(int i = 0; i < u.getAdjacencyList().size(); i++)
                deleteIncidentEdgesToAVertex(u.getAdjacencyList().get(i).getVertex(), u);
        else
            for(int i = 0; i < vertices.size(); i++)
                deleteIncidentEdgesToAVertex(vertices.get(i), u);
        vertices.remove(index);
        nVertices = vertices.size();
    }

    private int findEdge(VertexL<V, E> u, VertexL<V, E> v, E weight){
        for(int i = 0; i < u.getAdjacencyList().size(); i++)
            if(v.equals(u.getAdjacencyList().get(i).getVertex()) && weight.equals(u.getAdjacencyList().get(i).getWeight()))
                return i;
        return -1;
    }

    @Override
    public void deleteEdge(int position1, int position2, E weight) throws IndexOutOfBoundsException {
        VertexL<V, E> u = vertices.get(position1);
        VertexL<V, E> v = vertices.get(position2);
        u.deleteFromAdjacencyList(findEdge(u, v, weight));
        if (!isDirected() && position1 != position2)
            v.deleteFromAdjacencyList(findEdge(v, u, weight));
    }

    @Override
    public void deleteAllEdge(int position1, int position2){
        VertexL<V, E> u = vertices.get(position1);
        VertexL<V, E> v = vertices.get(position2);
        if(!isDirected() && position1 != position2)
            deleteIncidentEdgesToAVertex(v, u);
        deleteIncidentEdgesToAVertex(u, v);
    }

    @Override
    public ArrayList<VertexL<V, E>> getVerticesL(){
        return vertices;
    }

    @Override
    public ArrayList<VertexM<V>> getVertexM() {
        return null;
    }

    @Override
    public ArrayList<Integer> BFS(int initialVertexIndex) throws UnderflowException {
        for(int i = 0; i < nVertices; i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setDistance(Integer.MAX_VALUE);
            vertices.get(i).setPredecessor(-1);
        }
        return BFS_algorithm(initialVertexIndex);
    }

    private ArrayList<Integer> BFS_algorithm(int initialVertexIndex) throws UnderflowException {
        VertexL<V, E> initialVertexL = vertices.get(initialVertexIndex);
        initialVertexL.setColor((byte)1);
        initialVertexL.setDistance(0);
        Queue<VertexL<V, E>> nextToVisit = new Queue<>();
        ArrayList<Integer> visited = new ArrayList<>();
        nextToVisit.enqueue(initialVertexL);
        visited.add(initialVertexIndex);
        while(!nextToVisit.isEmpty()){
            VertexL<V, E> u = nextToVisit.dequeue();
            for(int i = 0; i < u.getDegree(); i++){
                VertexL<V, E> v = u.getAdjacencyList().get(i).getVertex();
                if(v.getColor() == 0){
                    v.setPredecessor(vertices.indexOf(u));
                    v.setDistance(u.getDistance() + 1);
                    v.setColor((byte)1);
                    visited.add(vertices.indexOf(v));
                    nextToVisit.enqueue(v);
                }
            }
            u.setColor((byte)2);
        }
        return visited;
    }


    public ArrayList<ArrayList<Integer>> BFS() throws UnderflowException {
        ArrayList<ArrayList<Integer>> forest = new ArrayList<>();
        for(int i = 0; i < nVertices; i++){
            boolean alreadyInForest = false;
            for(int j = 0; j < forest.size() && !alreadyInForest; j++){
                if(forest.get(j).contains(i))
                    alreadyInForest = true;
            }
            if(!alreadyInForest)
                forest.add(BFS_algorithm(i));
        }
        return forest;
    }

    @Override
    public ArrayList<Integer> DFS(int initialVertexIndex){
        ArrayList<Integer> tree = new ArrayList<>();
        DFS_restart();
        tree.add(initialVertexIndex);
        DFS_Visit(tree, initialVertexIndex);
        return tree;
    }

    @Override
    public ArrayList<ArrayList<Integer>> DFS() {
        ArrayList<ArrayList<Integer>> forest = new ArrayList<>();
        DFS_restart();
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getColor() == 0){
                ArrayList<Integer> tree = new ArrayList<>();
                tree.add(i);
                DFS_Visit(tree, i);
                forest.add(tree);
            }
        }
        return forest;
    }

    private void DFS_restart(){
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setPredecessor(-1);
        }
        time = 0;
    }

    private void DFS_Visit(ArrayList<Integer> tree, int i){
        time ++;
        VertexL<V, E> u = vertices.get(i);
        u.setInitialTime(time);
        u.setColor((byte)1);
        for(int j = 0; j < u.getAdjacencyList().size(); j++){
            VertexL<V, E> v = u.getAdjacencyList().get(j).getVertex();
            if(v.getColor() == 0){
                tree.add(vertices.indexOf(v));
                v.setPredecessor(vertices.indexOf(u));
                DFS_Visit(tree, vertices.indexOf(v));
            }
        }
        u.setColor((byte)2);
        time ++;
        u.setFinalTime(time);
    }

    @Override
    public ArrayList<Integer> Prim(int initialVertexIndex) throws GreaterKeyException, HeapUnderFlowException, IndexOutOfBoundsException {
        VertexL<V, E> r = vertices.get(initialVertexIndex);
        ArrayList<Integer> tree = new ArrayList<>();
        for(int i = 0; i < nVertices; i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setKey(null);
            vertices.get(i).setPredecessor(-1);
        }
        r.setKey(r.getAdjacencyList().get(0).getWeight());
        ArrayList<VertexL<V, E>> heap = new ArrayList<>();
        MinPriorityQueue<VertexL<V, E>> minQueue = new MinPriorityQueue<>(heap, 0);
        minQueue.insert(r);
        for(int i = 0; i < nVertices; i++){
            if(i != initialVertexIndex)
                minQueue.insert(vertices.get(i));
        }
        while(!minQueue.isEmpty()){
            VertexL<V, E> u = minQueue.extract_min();
            for(int i = 0; i < u.getDegree(); i++){
                VertexL<V, E> v = u.getAdjacencyList().get(i).getVertex();
                EdgeL<V, E> edgeLToV = u.getAdjacencyList().get(i);
                if(v.getColor() == 0 && v.getKey() == null){
                    v.setKey(edgeLToV.getWeight());
                    minQueue.decrease_key(v, v);
                    v.setPredecessor(vertices.indexOf(u));
                }
                else if(v.getColor() == 0 && edgeLToV.getWeight().compareTo(v.getKey()) < 0){
                    v.setKey(edgeLToV.getWeight());
                    minQueue.decrease_key(v, v);
                    v.setPredecessor(vertices.indexOf(u));
                }
            }
            u.setColor((byte)1);
            tree.add(vertices.indexOf(u));
        }
        r.setKey(null);
        return tree;
    }

    @Override
    public ArrayList<Integer> Kruskal(int initialVertexIndex){

        return null;
    }

    @Override
    public ArrayList<Double> Dijsktra(int startPosition) {
        return null;
    }

    @Override
    public double[][] Floyd_Warshal() {
        return new  double[0][];
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public int getNumberOfVertices(){
        return nVertices;
    }
}
