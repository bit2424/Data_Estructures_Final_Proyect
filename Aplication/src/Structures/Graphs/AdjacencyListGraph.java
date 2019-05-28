package Structures.Graphs;

import java.util.ArrayList;

import Structures.auxiliary_structures.DisjointSet;
import Structures.auxiliary_structures.MaxPriorityQueue;
import Structures.auxiliary_structures.MinPriorityQueue;
import Structures.auxiliary_structures.Queue;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.GreaterKeyException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.ThereIsNotAnEdgeException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;

public class AdjacencyListGraph<V extends Comparable<V>, E extends Comparable<E>> implements IGraph<V, E> {

    private boolean directed;
    private boolean weighted;
    private int time;
    private int nVertices;
    private int nEdges;
    private ArrayList<VertexL<V, E>> vertices;
    private ArrayList<EdgeL<V, E>> edges;

    public AdjacencyListGraph(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        nVertices = vertices.size();
        nEdges = edges.size();
    }

    public AdjacencyListGraph(boolean directed, boolean weighted, VertexL<V, E> vertex){
        this.directed = directed;
        this.weighted = weighted;
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        vertices.add(vertex);
        nVertices = vertices.size();
        nEdges = edges.size();
    }

    @Override
    public void insertVertex(V value){
        VertexL<V, E> u = new VertexL<>(value);
        vertices.add(u);
        nVertices = vertices.size();
    }

    @Override
    public void insertEdge(int position1, int position2, E connection) {
        EdgeL<V, E> newEdge = new EdgeL<>(vertices.get(position1), vertices.get(position2), connection);
        VertexL<V, E> u = vertices.get(position1);
        VertexL<V, E> v = vertices.get(position2);
        edges.add(newEdge);
        nEdges = edges.size();
        u.setDegree(u.getDegree() + 1);
        if(!u.getAdjacencyList().contains(v))
            u.addAdjacentVertex(v);
        if(!isDirected() && position1 != position2) {
            v.setDegree(v.getDegree() + 1);
            if(!v.getAdjacencyList().contains(u))
                v.addAdjacentVertex(u);
            changeOrderOfTheEdge(newEdge);
        }
    }

    private void changeOrderOfTheEdge(EdgeL<V, E> e){
        VertexL<V, E> origin = e.getOriginVertex();
        VertexL<V, E> destiny = e.getDestinationVertex();
        if(destiny.getValue().compareTo(origin.getValue()) < 0 ){
            e.setOriginVertex(destiny);
            e.setDestinationVertex(origin);
        }
    }

    private void deleteFromAdjacencyList(VertexL<V, E> u){
        for(int i = u.getAdjacencyList().size() - 1; i >= 0; i--){
            VertexL<V, E> v = u.getAdjacentVertex(i);
            ArrayList<EdgeL<V, E>> edgesToDelete = getEdges(vertices.indexOf(u), vertices.indexOf(v));
            int degree = edgesToDelete.size();
            deleteFromEdgesList(edgesToDelete);
            if(!isDirected()) {
                v.deleteFromAdjacencyList(v.getAdjacencyList().indexOf(u));
                v.setDegree(v.getDegree() - degree);
            }
            if(isDirected()){
                edgesToDelete = getEdges(vertices.indexOf(v), vertices.indexOf(u));
                degree = edgesToDelete.size();
                deleteFromEdgesList(edgesToDelete);
                if(degree > 0){
                    v.deleteFromAdjacencyList(v.getAdjacencyList().indexOf(u));
                    v.setDegree(v.getDegree() - degree);
                }
            }
        }
    }

    private void deleteFromEdgesList(ArrayList<EdgeL<V, E>> edgesToDelete){
        for(int i = 0; i < edgesToDelete.size(); i++){
            edges.remove(edgesToDelete.get(i));
            nEdges = edges.size();
        }
    }

    @Override
    public void deleteVertex(int index) throws IndexOutOfBoundsException {
        VertexL<V, E> u = vertices.get(index);
        deleteFromAdjacencyList(u);
        vertices.remove(index);
        nVertices = vertices.size();
    }

    public ArrayList<EdgeL<V, E>> getEdges(int index1, int index2){
        VertexL<V, E> u = vertices.get(index1);
        VertexL<V, E> v = vertices.get(index2);
        ArrayList<EdgeL<V, E>> edgesIncidentToUAndV = new ArrayList<>();
        if(!isDirected()){
            for(int i = nEdges - 1; i >= 0; i--)
                if((edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v)) ||
                        (edges.get(i).getOriginVertex().equals(v) && edges.get(i).getDestinationVertex().equals(u))){
                    edgesIncidentToUAndV.add(edges.get(i));
                }
        }
        else {
            for(int i = nEdges - 1; i >= 0; i--)
                if(edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v)){
                    edgesIncidentToUAndV.add(edges.get(i));
                }
        }
        return edgesIncidentToUAndV;
    }

    public int getEdge(int index1, int index2, E weight){
        VertexL<V, E> u = vertices.get(index1);
        VertexL<V, E> v = vertices.get(index2);
        if(!isDirected()){
            for(int i = 0; i < nEdges; i++)
                if((edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v) && edges.get(i).getWeight().equals(weight)) ||
                        (edges.get(i).getOriginVertex().equals(v) && edges.get(i).getDestinationVertex().equals(u) && edges.get(i).getWeight().equals(weight))){
                    return i;
                }
        }
        else{
            for(int i = 0; i < nEdges; i++)
                if(edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v) && edges.get(i).getWeight().equals(weight)){
                    return i;
                }
        }
        return -1;
    }

    @Override
    public void deleteEdge(int position1, int position2, E weight) throws IndexOutOfBoundsException, ThereIsNotAnEdgeException {
        VertexL<V, E> u = vertices.get(position1);
        VertexL<V, E> v = vertices.get(position2);
        if(isThereAnEdge(u, v, weight)) {
            int edgeToDelete = getEdge(position1, position2, weight);
            u.deleteFromAdjacencyList(u.getAdjacencyList().indexOf(v));
            u.setDegree(u.getDegree() - 1);
            if (!isDirected() && position1 != position2) {
                v.deleteFromAdjacencyList(v.getAdjacencyList().indexOf(u));
                v.setDegree(v.getDegree() - 1);
            }
            edges.remove(edgeToDelete);
            nEdges = edges.size();
        }
        else{
            throw new ThereIsNotAnEdgeException("No existe una arista entre " + u.getValue() + " y " + v.getValue() + ".");
        }
    }

    private boolean isThereAnEdge(VertexL<V, E> u, VertexL<V, E> v, E weight){
        if(!isDirected()){
            for(int i = nEdges - 1; i >= 0; i--)
                if((edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v) && weight.equals(edges.get(i).getWeight())) ||
                        (edges.get(i).getOriginVertex().equals(v) && edges.get(i).getDestinationVertex().equals(u) && weight.equals(edges.get(i).getWeight()))){
                    return true;
                }
        }
        else {
            for(int i = nEdges - 1; i >= 0; i--)
                if(edges.get(i).getOriginVertex().equals(u) && edges.get(i).getDestinationVertex().equals(v)){
                    return true;
                }
        }
        return false;
    }

    @Override
    public void deleteAllEdge(int position1, int position2) throws ThereIsNotAnEdgeException {
        VertexL<V, E> u = vertices.get(position1);
        VertexL<V, E> v = vertices.get(position2);
        ArrayList<EdgeL<V, E>> edgesToDelete = getEdges(position1, position2);
        if(edgesToDelete.size() != 0){
            u.deleteFromAdjacencyList(u.getAdjacencyList().indexOf(v));
            u.setDegree(u.getDegree() - edgesToDelete.size());
            if(!isDirected() && position1 != position2){
                v.deleteFromAdjacencyList(v.getAdjacencyList().indexOf(u));
                v.setDegree(v.getDegree() - edgesToDelete.size());
            }
            deleteFromEdgesList(edgesToDelete);
        }
        else{
            throw new ThereIsNotAnEdgeException("No hay aristas entre los vértices " + u.getValue() + " y " + v.getValue() + ".");
        }
    }

    @Override
    public ArrayList<VertexL<V, E>> getVerticesL(){
        return vertices;
    }

    @Override
    public ArrayList<VertexM<V>> getVertexM(){
        return null;
    }

    public ArrayList<EdgeL<V, E>> getEdges(){
        return edges;
    }

    @Override
    public ArrayList<Integer> BFS(int initialVertexIndex) throws UnderflowException {
        for(int i = 0; i < nVertices; i++){
            vertices.get(i).setColor((byte)0);
            vertices.get(i).setDistance(Double.MAX_VALUE);
            vertices.get(i).setPredecessor(-1);
        }
        return BFS_algorithm(initialVertexIndex);
    }

    private ArrayList<Integer> BFS_algorithm(int initialVertexIndex) throws UnderflowException {
        VertexL<V, E> initialVertex = vertices.get(initialVertexIndex);
        initialVertex.setColor((byte)1);
        initialVertex.setDistance(0);
        Queue<VertexL<V, E>> nextToVisit = new Queue<>();
        ArrayList<Integer> visited = new ArrayList<>();
        nextToVisit.enqueue(initialVertex);
        visited.add(initialVertexIndex);
        while(!nextToVisit.isEmpty()){
            VertexL<V, E> u = nextToVisit.dequeue();
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                VertexL<V, E> v = u.getAdjacencyList().get(i);
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
            VertexL<V, E> v = u.getAdjacencyList().get(j);
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
            vertices.get(i).setDistance(Integer.MAX_VALUE);
            vertices.get(i).setPredecessor(-1);
        }
        r.setDistance(0);
        ArrayList<VertexL<V, E>> heap = new ArrayList<>();
        MinPriorityQueue<VertexL<V, E>> minQueueVertex = new MinPriorityQueue<>(heap, 0);
        minQueueVertex.insert(r);
        for(int i = 0; i < nVertices; i++){
            if(i != initialVertexIndex)
                minQueueVertex.insert(vertices.get(i));
        }
        while(!minQueueVertex.isEmpty()){
            VertexL<V, E> u = minQueueVertex.extract_min();
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                VertexL<V, E> v = u.getAdjacencyList().get(i);
                if(v.getColor() == 0){
                    ArrayList<EdgeL<V, E>> incidentEdges = getEdges(vertices.indexOf(u), vertices.indexOf(v));
                    for(int j = 0; j < incidentEdges.size(); j++){
                        double weight = Double.parseDouble(String.valueOf(incidentEdges.get(j).getWeight()));
                        if(weight < v.getDistance()){
                            v.setDistance(weight);
                            v.setPredecessor(vertices.indexOf(u));
                            minQueueVertex.decrease_key(v, v);
                        }
                    }
                }
            }
            u.setColor((byte)1);
            tree.add(vertices.indexOf(u));
        }
        return tree;
    }

    @Override
    public ArrayList<EdgeL<V, E>> Kruskal() throws HeapUnderFlowException{
        ArrayList<EdgeL<V ,E>> mst = new ArrayList<>();
        ArrayList<EdgeL<V ,E>> cloneOfEdgesArray = (ArrayList<EdgeL<V, E>>)edges.clone();
        MaxPriorityQueue<EdgeL<V, E>> maxPriorityQueueEdges = new MaxPriorityQueue<>(cloneOfEdgesArray, 1000);
        maxPriorityQueueEdges.heapsort();
        DisjointSet disjointSet = new DisjointSet(nVertices);
        while(!maxPriorityQueueEdges.isEmpty()) {
            EdgeL<V, E> minEdge = maxPriorityQueueEdges.extract_max();
            maxPriorityQueueEdges.heapsort();
            int indexOfOriginVertex = vertices.indexOf(minEdge.getOriginVertex());
            int indexOfDestinationVertex = vertices.indexOf(minEdge.getDestinationVertex());
            if (!disjointSet.same_component(indexOfOriginVertex, indexOfDestinationVertex)) {
                mst.add(minEdge);
            }
            disjointSet.union_by_rank(indexOfOriginVertex, indexOfDestinationVertex);
        }
        return mst;
    }

//    @Override
//    public ArrayList<EdgeL<V, E>> Kruskal() throws HeapUnderFlowException {
//        ArrayList<EdgeL<V, E>> mst = new ArrayList<>();
//        MinPriorityQueue<EdgeL<V, E>> minPriorityQueueEdges = new MinPriorityQueue<>(edges, 1000);
//        minPriorityQueueEdges.heapsort();
//        DisjointSet disjointSet = new DisjointSet(nVertices);
//        while(!minPriorityQueueEdges.isEmpty()){
//            EdgeL<V, E> minEdge = minPriorityQueueEdges.extract_min();
//            int indexOfOriginVertex = vertices.indexOf(minEdge.getOriginVertex());
//            int indexOfDestinationVertex = vertices.indexOf(minEdge.getDestinationVertex());
//            if(!disjointSet.same_component(indexOfOriginVertex, indexOfDestinationVertex)){
//                mst.add(minEdge);
//                disjointSet.union_by_rank(indexOfOriginVertex, indexOfDestinationVertex);
//            }
//        }
//        return mst;
//    }

    @Override
    public Object[] Dijsktra(int startPosition) throws IndexOutOfBoundsException {
        Object[] arrays = new Object[2];
        double[] distances = new double[nVertices];
        int[] predecessors = new int[nVertices];
        boolean[] visited = new  boolean[nVertices];
        for(int i = 0; i < nVertices; i++){
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = -1;
            visited[i] = false;
        }
        distances[startPosition] = 0;
        int minimum = getMinimumVertex(distances, visited);
        while(minimum >= 0){
            VertexL<V, E> u = vertices.get(minimum);
            int indexOfU = vertices.indexOf(u);
            for(int i = 0; i < u.getAdjacencyList().size(); i++){
                VertexL<V, E> v = u.getAdjacentVertex(i);
                int indexOfV = vertices.indexOf(v);
                ArrayList<EdgeL<V, E>> incidentEdges = getEdges(vertices.indexOf(u), vertices.indexOf(v));
                for(int j = 0; j < incidentEdges.size(); j++){
                    double weight = Double.parseDouble(String.valueOf(incidentEdges.get(j).getWeight()));
                    double sum = (distances[indexOfU] + weight);
                    if(!visited[indexOfV] && sum < distances[indexOfV]){
                        distances[indexOfV] = sum;
                        predecessors[indexOfV] = indexOfU;
                    }
                }
            }
            visited[indexOfU] = true;
            minimum = getMinimumVertex(distances, visited);
        }
        arrays[0] = distances;
        arrays[1] = predecessors;
        return arrays;
    }

    private int getMinimumVertex(double[] dist, boolean[] visited){
        int u = -1;
        double value = Integer.MAX_VALUE;
        for(int i = 0; i < dist.length; i++){
            if(!visited[i] && dist[i] < value){
                value = dist[i];
                u = i;
            }
        }
        return u;
    }

    @Override
    public double[][] Floyd_Warshal() {
        return new double[0][];
    }

    public EdgeL<V, E> getMinimunEdge(){
        return edges.get(0);
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

    public int getNumberOfEdges(){
        return nEdges;
    }
}
