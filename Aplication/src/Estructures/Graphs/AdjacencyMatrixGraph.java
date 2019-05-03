package Estructures.Graphs;

import java.util.*;

public class AdjacencyMatrixGraph<V,E extends Comparable<E>> implements IAdjacencyMatrixGraph<V,E>{
    private HashMap<Integer,VertexM<V>> elementsReference;
    private int nVertex;
    private boolean weighted;
    private  boolean directed;
    private EdgeM matrixAdyacency[][];

    public AdjacencyMatrixGraph(HashMap<Integer,VertexM<V>> elementsReference, boolean weighted, boolean directed) {
        this.elementsReference = elementsReference;
        this.weighted = weighted;
        this.directed = directed;
        nVertex = elementsReference.size();
        matrixAdyacency = new EdgeM[1000][1000];
        Iterator loop = elementsReference.entrySet().iterator();
        while(loop.hasNext()){
            Map.Entry pair = (Map.Entry)loop.next();

            loop.remove();
        }
    }

    public AdjacencyMatrixGraph(boolean weighted, boolean directed) {
        this.elementsReference = new HashMap<>();
        this.weighted = weighted;
        this.directed = directed;
        matrixAdyacency = new EdgeM[1000][1000];
        nVertex = 0;
    }
    @Override
    public void addVertex(V v){
        elementsReference.put(nVertex,new VertexM<>(v));
        nVertex++;

    }
    @Override
    public  void addConection(int position1 , int  position2 , E conection){
        if(directed){
            matrixAdyacency[position1][position2] = new EdgeM(conection);
        }else{
            matrixAdyacency[position1][position2] = new EdgeM(conection);
            matrixAdyacency[position2][position1] = new EdgeM(conection);
        }
    }
    @Override
    public ArrayList<VertexM> getAdyacencyList(int positionNode){
        ArrayList<VertexM> temp = new ArrayList<>();
        for (int I = 0; I<matrixAdyacency[0].length ;I++){
            if(matrixAdyacency[positionNode][I] != null){
                temp.add(elementsReference.get(I));
            }
        }
        return temp;
    }

    @Override
    public void deleteVertex(int positionNode) {
        for (int I = 0; I<matrixAdyacency[0].length ;I++){
            matrixAdyacency[positionNode][I] = null;
        }

        for (int I = 0; I<matrixAdyacency.length ;I++){
            matrixAdyacency[I][positionNode] = null;
        }
    }

    @Override
    public void deleteEdge(int position1, int position2) {
        if(directed){
            matrixAdyacency[position1][position2] = null;
        }else{
            matrixAdyacency[position1][position2] = null;
            matrixAdyacency[position2][position1] = null;
        }
    }

    @Override
    public ArrayList<VertexM<V>> DFS(int startPosition) {
        
        return null;
    }

    @Override
    public ArrayList<VertexM<V>> BFS(int startPosition) {
        return null;
    }

    @Override
    public ArrayList<ArrayList<VertexM<V>>> DFS() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<VertexM<V>>> BFS() {
        return null;
    }


    public HashMap<Integer, VertexM<V>> getElementsReference() {
        return elementsReference;
    }

    public void setElementsReference(HashMap<Integer, VertexM<V>> elementsReference) {
        this.elementsReference = elementsReference;
    }

    public int getnVertex() {
        return nVertex;
    }

    public void setnVertex(int nVertex) {
        this.nVertex = nVertex;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }
}
