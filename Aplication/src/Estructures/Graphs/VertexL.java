package Estructures.Graphs;

import java.util.ArrayList;

public class VertexL<V, E extends Comparable<E>> implements Comparable<VertexL<V, E>>{

    private V value;
    private E key;
    private int degree;
    private int distance;
    private byte color;
    private int predecessor;
    private int initialTime;
    private int finalTime;
    private ArrayList<EdgeL<V, E>> adjacencyList;

    public VertexL(V value){
        this.value = value;
        key = null;
        degree = 0;
        distance = -1;
        color = 0;
        predecessor = -1;
        initialTime = 0;
        finalTime = 0;
        adjacencyList = new ArrayList<>();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public E getKey(){
        return key;
    }

    public void setKey(E key){
        this.key = key;
    }

    public int getDegree() {
        return degree;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public byte getColor() {
        return color;
    }

    public void setColor(byte color) {
        this.color = color;
    }

    public int getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(int predecessor) {
        this.predecessor = predecessor;
    }

    public int getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    public int getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
    }

    public ArrayList<EdgeL<V, E>> getAdjacencyList(){
        return adjacencyList;
    }

    public void addAdjacentVertex(EdgeL<V, E> adjacent){
        adjacencyList.add(adjacent);
        degree = adjacencyList.size();
    }

    public void addAdjacencyList(ArrayList<EdgeL<V, E>> adjacencyList){
        this.adjacencyList = adjacencyList;
        degree = this.adjacencyList.size();
    }

    public void deleteFromAdjacencyList(int index) throws IndexOutOfBoundsException{
        adjacencyList.remove(index);
        degree = adjacencyList.size();
    }

    public void deleteAdjacencyList(){
        adjacencyList = null;
        degree = 0;
    }

    @Override
    public int compareTo(VertexL<V, E> o) {
        if(key == null && o.getKey() == null)
            return 0;
        else if(o.getKey() == null)
            return -1;
        else if(key == null)
            return 1;
        else
            return key.compareTo(o.getKey());
    }
}