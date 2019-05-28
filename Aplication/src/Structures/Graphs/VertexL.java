package Structures.Graphs;

import java.util.ArrayList;

public class VertexL<V extends Comparable<V>, E extends Comparable<E>> implements Comparable<VertexL<V, E>>{

    private V value;
    private E key;
    private int degree;
    private double distance;
    private byte color;
    private int predecessor;
    private int initialTime;
    private int finalTime;
    private ArrayList<VertexL<V, E>> adjacencyList;
    private ArrayList<EdgeL<V, E>> incidenceList;

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
        incidenceList = new ArrayList<>();
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

    public void setDegree(int degree){
        this.degree = degree;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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

    public VertexL<V, E> getAdjacentVertex(int i){
        return adjacencyList.get(i);
    }

    public ArrayList<VertexL<V, E>> getAdjacencyList(){
        return adjacencyList;
    }

    public void addAdjacentVertex(VertexL<V, E> adjacent){
        adjacencyList.add(adjacent);
    }

    public void addAdjacencyList(ArrayList<VertexL<V, E>> adjacencyList){
        this.adjacencyList = adjacencyList;
        degree = this.adjacencyList.size();
    }

    public void deleteFromAdjacencyList(int index) throws IndexOutOfBoundsException{
        adjacencyList.remove(index);
    }

    public void deleteAdjacencyList(){
        adjacencyList = null;
        degree = 0;
    }

    @Override
    public int compareTo(VertexL<V, E> v) {
        if(distance == v.distance)
            return 0;
        else if(distance > v.distance)
            return 1;
        else
            return -1;
    }

}
