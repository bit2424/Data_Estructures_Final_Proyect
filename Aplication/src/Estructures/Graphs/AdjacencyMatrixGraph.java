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
        elementsReference.remove(positionVertex);
        nVertex--;


        for (int I = 0; I < positionVertex;I++ ){
            for(int J = positionVertex; J<matrixAdyacency.length ; J++){

                matrixAdyacency[I][J] = matrixAdyacency[I][J+1] ;

                if(matrixAdyacency[I][J+1] == null){
                    break;
                }

            }
        }

        for (int J = 0; J < positionVertex; J++){
            for( int I = positionVertex; I < matrixAdyacency.length;I++ ){

                matrixAdyacency[I][J] = matrixAdyacency[I+1][J] ;

                if(matrixAdyacency[I+1][J] == null){
                    break;
                }

            }
        }

        for (int I = positionVertex; I < matrixAdyacency.length ;I++ ){
            for(int J = positionVertex; J<matrixAdyacency.length ; J++){

                matrixAdyacency[I][J] = matrixAdyacency[I+1][J+1] ;

            }
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
    public ArrayList<VertexL<V, E>> getVerticesL() {
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
        int V = nVertex;

        TreeMap<Double,Integer> minDistance = new TreeMap<>();

        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        double key[] = new double [V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++)
        {
            key[i] = Double.MAX_VALUE;
            minDistance.put(Double.MAX_VALUE,i);
            mstSet[i] = false;
        }

        minDistance.replace(0d,startPosition);
        // Always include first 1st vertex in MST.
        //key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST

                int u = minDistance.lowerEntry(Double.MAX_VALUE).getValue();


            // Add the picked vertex to the MST Set
            mstSet[u] = true;
            minDistance.remove(minDistance.lowerEntry(Double.MAX_VALUE).getKey(),u);

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v] l
                if (matrixAdyacency[u][v]!=null && mstSet[v] == false &&
                        (double)matrixAdyacency[u][v].getValue() < key[v])
                {
                    parent[v] = u;
                    key[v] = (double)matrixAdyacency[u][v].getValue();
                    minDistance.replace((double)matrixAdyacency[u][v].getValue(),v);
                }
        }
        ArrayList<Integer> result = new ArrayList<>();

        for(int I = 0; I< parent.length; I++){
            result.add(parent[I]);
        }

        return result;
    }

    // A class to represent a subset for union-find
    class subset
    {
        int parent, rank;
    };


    // A class to represent a graph edge
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        // Comparator function used for sorting edges
        // based on their weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };



    //Nelson
    @Override
    public ArrayList<Integer> Kruskal(int startPosition) {


        return null;
    }


    // A utility function to find set of an element i
    // (uses path compression technique)
    int find(subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as root and increment
            // its rank by one
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }



    //Nelson
    @Override
    public ArrayList<Double> Dijsktra(int startPosition) {
        int V = nVertex;
        double dist[] = new double[V];
        Boolean sptSet[] = new Boolean[V];

        PriorityQueue<VertexM> minDistance = new PriorityQueue<>();



        for (int i = 0; i < V; i++)
        {
            dist[i] = Double.MAX_VALUE;
            minDistance.add(elementsReference.get(i));
        }

        dist[startPosition] = 0;


        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = (int)minDistance.poll().getValue();

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && matrixAdyacency[u][v]!=null &&
                        dist[u] != Double.MAX_VALUE &&
                        dist[u]+(double)matrixAdyacency[u][v].getValue() < dist[v])
                    dist[v] = dist[u] + (double)matrixAdyacency[u][v].getValue();
        }

        ArrayList<Double> result = new ArrayList<>();

        for(int I = 0; I< dist.length; I++){
            result.add(dist[I]);
        }

        return result;
    }

    //Veloza
    @Override
    public double[][] Floyd_Warshal() {
        int V = matrixAdyacency.length;
        double dist[][] = new double[matrixAdyacency.length][matrixAdyacency.length];
        int i, j, k;

        for (i = 0; i < matrixAdyacency.length; i++)
            for (j = 0; j < matrixAdyacency.length; j++)
                if(matrixAdyacency[i][j] == null){
                    dist[i][j] = (double)matrixAdyacency[i][j].getValue();
                }else{
                    dist[i][j] = Double.MAX_VALUE;
                }

        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] != Double.MAX_VALUE && dist[k][j] != Double.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }


        return dist;
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

    public ArrayList<VertexM<V>> getElementsReference() {
        return elementsReference;
    }

    public void setElementsReference(ArrayList<VertexM<V>> elementsReference) {
        this.elementsReference = elementsReference;
    }

    public EdgeM[][] getMatrixAdyacency() {
        return matrixAdyacency;
    }

    public void setMatrixAdyacency(EdgeM[][] matrixAdyacency) {
        this.matrixAdyacency = matrixAdyacency;
    }
}
