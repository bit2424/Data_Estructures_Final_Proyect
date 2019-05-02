package Estructures.Graphs;

import java.util.ArrayList;

public class Vertex<T> {

    private T value;
    private int degree;
    private int distance;
    private byte color;
    private Vertex predecessor;
    private int initialTime;
    private int finalTime;
    private ArrayList<Edge<T>> adjacencyList;

    public Vertex(T value){
        this.value = value;
        degree = 0;
        distance = -1;
        color = 0;
        predecessor = null;
        initialTime = 0;
        finalTime = 0;
        adjacencyList = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getDegree() {
        return degree = adjacencyList.size();
    }

    public void setDegree(int degree) {
        this.degree = degree;
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

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
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

    public ArrayList<Edge<T>> getAdjacencyList(){
        return adjacencyList;
    }

    public void addAdjacentVertex(Edge<T> adjacent){
        adjacencyList.add(adjacent);
    }

    public void addAdjacencyList(ArrayList<Edge<T>> adjacencyList){
        this.adjacencyList = adjacencyList;
    }

    public void deleteFromAdjacencyList(int index){
        adjacencyList.remove(index);
    }

    public void deleteAdjacencyList(){
        adjacencyList = null;
    }

}
