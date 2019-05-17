package Estructures.Graphs;

import java.util.*;

public class AdjacencyMatrixGraph<V,E extends Comparable<E>> implements IGraph<V,E>{
    private ArrayList<VertexM<V>> elementsReference;
    private int nVertex;
    private boolean weighted;
    private  boolean directed;
    private EdgeM matrixAdyacency[][];
    private boolean visitedG[];

    public AdjacencyMatrixGraph(ArrayList<VertexM<V>> elementsReference, boolean weighted, boolean directed) {
        this.elementsReference = elementsReference;
        this.weighted = weighted;
        this.directed = directed;
        nVertex = elementsReference.size();
        matrixAdyacency = new EdgeM[1000][1000];
    }

    public AdjacencyMatrixGraph(boolean weighted, boolean directed) {
        this.elementsReference = new ArrayList<>();
        this.weighted = weighted;
        this.directed = directed;
        matrixAdyacency = new EdgeM[1000][1000];
        nVertex = 0;

    }

    @Override
    public void insertVertex(V value) {
        elementsReference.add(new VertexM<>(value));
        nVertex++;
        visitedG = new boolean[nVertex];
    }

    @Override
    public void insertEdge(int position1, int position2, E conection) {
        if(directed){
            matrixAdyacency[position1][position2] = new EdgeM(conection);
        }else{
            matrixAdyacency[position1][position2] = new EdgeM(conection);
            matrixAdyacency[position2][position1] = new EdgeM(conection);
        }

    }

    @Override
    public void deleteVertex(int positionVertex) {
        elementsReference.set(positionVertex,null);
        nVertex--;
        for (int I = 0; I<matrixAdyacency[0].length ;I++){
            matrixAdyacency[positionVertex][I] = null;
        }

        for (int I = 0; I<matrixAdyacency.length ;I++){
            matrixAdyacency[I][positionVertex] = null;
        }

    }

    @Override
    public void deleteEdge(int position1, int position2, E conection) {
        if(directed){
            matrixAdyacency[position1][position2] = null;
        }else{
            matrixAdyacency[position1][position2] = null;
            matrixAdyacency[position2][position1] = null;
        }
    }

    @Override
    public void deleteAllEdge(int position1, int position2) {
        if(directed){
            matrixAdyacency[position1][position2] = null;
        }else{
            matrixAdyacency[position1][position2] = null;
            matrixAdyacency[position2][position1] = null;
        }
    }

    @Override
    public ArrayList<VertexM<V>> getVertexM() {
        return elementsReference;
    }


    @Override
    public ArrayList<Vertex<V>> getVertex() {
        return null;
    }

    //Veloza
    @Override
    public ArrayList<Integer> BFS(int startPosition) {

        if(elementsReference.get(startPosition) == null){
            return null;
        }

        int s = startPosition;
        ArrayList<Integer> Solution = new ArrayList<>();

        boolean visited[] = new boolean[elementsReference.size()];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while(queue.size() != 0){
            s = queue.poll();

            Solution.add(s);

            for (int I = 0; I< matrixAdyacency[s].length ; I++){
                if(matrixAdyacency[s][I] != null && !visited[I]){
                    visited[I] = true;
                    queue.add(I);
                }
            }
        }


        return Solution;
    }

    public ArrayList<int[]> BFS_freedomDegrees(int startPosition) {

        return null;
    }

    @Override
    public ArrayList<Integer> DFS(int startPosition) {
        if(elementsReference.get(startPosition) == null){
            return null;
        }

        int s = startPosition;
        ArrayList<Integer> Solution = new ArrayList<>();


        Stack<Integer> stack = new Stack<>();

        boolean visited[] = new boolean[elementsReference.size()];

        visited[s]=true;
        stack.add(s);
        visitedG[s] = true;

        while(stack.size() != 0){
            s = stack.pop();

            Solution.add(s);

            for (int I = 0; I< matrixAdyacency[s].length ; I++){
                if(matrixAdyacency[s][I] != null && !visited[I]){
                    visited[I] = true;
                    visitedG[I] = true;
                    stack.add(I);
                }
            }
        }


        return Solution;
    }

    //Fabio
    @Override
    public ArrayList<ArrayList<Integer>> DFS() {
        ArrayList<ArrayList<Integer>> Solution = new ArrayList<>();
        visitedG = new boolean[elementsReference.size()];

        for(int I = 0; I< elementsReference.size() ; I++){
            if(visitedG[I] = false && elementsReference.get(I) != null){
                Solution.add(DFS(I));
            }
        }

        return Solution;
    }

    //Nelson
    @Override
    public ArrayList<Integer> Prim(int startPosition) {
        ArrayList<Integer> Solution = new ArrayList<>();
        return Solution;
    }

    //Nelson
    @Override
    public ArrayList<Integer> Kruskal(int startPosition) {
        return null;
    }

    //Nelson
    @Override
    public ArrayList<int[]> Dijsktra(int startPosition) {
        return null;
    }

    //Veloza
    @Override
    public int[][] Floyd_Warshal() {
        return new int[0][];
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
