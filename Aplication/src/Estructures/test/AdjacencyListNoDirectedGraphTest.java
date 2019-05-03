package Estructures.test;

import Estructures.Graphs.AdjacencyListGraph;
import Estructures.Graphs.Edge;
import Estructures.Graphs.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListNoDirectedGraphTest {

    private AdjacencyListGraph<Integer> noDirectedGraph;

    private void setupScene1(){
        noDirectedGraph = new AdjacencyListGraph(false, true, new Vertex<>(1));
    }

    private void setupScene2(){
        setupScene1();

        Edge<Integer> edgeTo1 = new Edge<>(noDirectedGraph.getVertices().get(0), 10);
        noDirectedGraph.insertVertex(2, edgeTo1);
        noDirectedGraph.insertVertex(3);
    }

    private void setupScene3(){
        setupScene2();

        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(2), 20);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(1), 30);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(0), 40);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(2), noDirectedGraph.getVertices().get(1), 50);
    }

    private void setupScene4(){
        noDirectedGraph = new AdjacencyListGraph<>(true,true, new Vertex<>(1));

        noDirectedGraph.insertVertex(2, new Edge<>(noDirectedGraph.getVertices().get(0), 10));
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(0), noDirectedGraph.getVertices().get(1), 2);
        noDirectedGraph.insertVertex(3);
        noDirectedGraph.insertVertex(4);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(0), noDirectedGraph.getVertices().get(2), 23);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(0), noDirectedGraph.getVertices().get(3), 15);
        noDirectedGraph.insertVertex(5);
        noDirectedGraph.insertVertex(6);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(2), noDirectedGraph.getVertices().get(5), 40);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(3), noDirectedGraph.getVertices().get(4), 12);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(4), noDirectedGraph.getVertices().get(5), 28);
        noDirectedGraph.insertVertex(7);
        noDirectedGraph.insertVertex(8);
        noDirectedGraph.insertVertex(9);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(6), noDirectedGraph.getVertices().get(7), 30);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(7), noDirectedGraph.getVertices().get(8), 39);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(8), noDirectedGraph.getVertices().get(6), 20);
    }

    private void setupScene5(){
        setupScene1();

        noDirectedGraph.insertVertex(2);
        noDirectedGraph.insertVertex(3);
        noDirectedGraph.insertVertex(4);
        noDirectedGraph.insertVertex(5);

        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(0), noDirectedGraph.getVertices().get(1), 10);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(2), 20);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(2), noDirectedGraph.getVertices().get(3), 30);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(3), noDirectedGraph.getVertices().get(4), 40);
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(4), noDirectedGraph.getVertices().get(0), 50);
    }

    @Test
    void constructorTest(){
        setupScene1();

        assertFalse(noDirectedGraph.isDirected());
        assertTrue(noDirectedGraph.isWeighted());
        assertTrue(noDirectedGraph.getVertices().size() == 1);
        assertTrue(noDirectedGraph.getVertices().get(0).getValue() == 1);
    }

    @Test
    public void insertTest(){
        setupScene1();

        // Case 1: Inserts a vertex with a connection to the first one.
        Edge<Integer> edge = new Edge<>(noDirectedGraph.getVertices().get(0), 10);
        noDirectedGraph.insertVertex(2, edge);

        assertTrue(noDirectedGraph.getVertices().size() == 2);

        assertTrue(noDirectedGraph.getVertices().get(1).getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 1);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 10);

        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 10);

        // Case 2: Inserts a vertex with one connexion to each vertex already inserted in the graph.
        Edge<Integer> edge1 = new Edge<>(noDirectedGraph.getVertices().get(0), 20);
        Edge<Integer> edge2 = new Edge<>(noDirectedGraph.getVertices().get(1), 30);
        ArrayList<Edge<Integer>> adj = new ArrayList<>();
        adj.add(edge1);
        adj.add(edge2);
        noDirectedGraph.insertVertex(3, adj);

        assertTrue(noDirectedGraph.getVertices().size() == 3);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 20);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(1).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(1).getWeight() == 30);

        // Case 3: Inserts a vertex with no connexion to any of the other vertex in the graph.
        noDirectedGraph.insertVertex(4);

        assertTrue(noDirectedGraph.getVertices().size() == 4);
        assertTrue(noDirectedGraph.getVertices().get(3).getValue() == 4);
        assertTrue(noDirectedGraph.getVertices().get(3).getAdjacencyList().size() == 0);
    }

    @Test
    void addEdgeTest(){
        setupScene2();

        // Case 1: Adds an edge between two vertices which are not connected.
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(2), 20);

        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 10);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 3);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(1).getWeight() == 20);

        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 10);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().size() == 1);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 20);

        // Case 2: Adds an edge form one vertex to the same one.
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(1), 30);

        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 3);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(2).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(2).getWeight() == 30);

        // Case 3: Adds an edge between two vertices which are already connected by another edge.
        noDirectedGraph.addEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(0), 40);

        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 4);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(3).getVertex().getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(3).getWeight() == 40);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 40);
    }

    @Test
    void deleteEdge(){
        setupScene3();

        // Case 1: Deletes an edge which does not exist.
        try{
            noDirectedGraph.deleteEdge(noDirectedGraph.getVertices().get(0), noDirectedGraph.getVertices().get(2), 20);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Deletes an edge from a vertex to another one.
        noDirectedGraph.deleteEdge(noDirectedGraph.getVertices().get(2), noDirectedGraph.getVertices().get(1), 50);

        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().size() == 1);

        // Case 3: Deletes an edge whose origin vertex is equal to target vertex.
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 4);

        noDirectedGraph.deleteEdge(noDirectedGraph.getVertices().get(1), noDirectedGraph.getVertices().get(1), 30);

        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 3);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 3);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().get(2).getVertex().getValue() == 1);
    }

    @Test
    void deleteVertexTest(){
        setupScene3();

        assertTrue(noDirectedGraph.getVertices().size() == 3);

        // Case 1: Deletes a vertex which does not exist.
        try{
            noDirectedGraph.deleteVertex(3);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Deletes a vertex with no connection to any of the others.
        setupScene2();

        assertTrue(noDirectedGraph.getVertices().size() == 3);

        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().size() == 0);

        noDirectedGraph.deleteVertex(2);

        assertTrue(noDirectedGraph.getVertices().size() == 2);

        // Case 3: Deletes a leaf vertex.
        setupScene2();

        noDirectedGraph.deleteVertex(0);

        assertTrue(noDirectedGraph.getVertices().size() == 2);

        // Case 4: Deletes a vertex with multiple connections to any others.
        setupScene3();

        assertTrue(noDirectedGraph.getVertices().size() == 3);
        assertTrue(noDirectedGraph.getVertices().get(0).getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getValue() == 2);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 5);
        assertTrue(noDirectedGraph.getVertices().get(2).getValue() == 3);
        assertTrue(noDirectedGraph.getVertices().get(2).getAdjacencyList().size() == 2);

        noDirectedGraph.deleteVertex(1);

        assertTrue(noDirectedGraph.getVertices().size() == 2);
        assertTrue(noDirectedGraph.getVertices().get(0).getValue() == 1);
        assertTrue(noDirectedGraph.getVertices().get(0).getAdjacencyList().size() == 0);
        assertTrue(noDirectedGraph.getVertices().get(1).getValue() == 3);
        assertTrue(noDirectedGraph.getVertices().get(1).getAdjacencyList().size() == 0);
    }

    @Test
    void BFSTest() {
        setupScene4();

        ArrayList<Vertex<Integer>> bfsTree;

        // Case 1: Travels a path which does not exist.
        bfsTree = noDirectedGraph.BFS(0, 8);

        assertNull(bfsTree);

        // Case 2: Travels a path between the same vertex.
        bfsTree = noDirectedGraph.BFS(0, 0);

        assertTrue(bfsTree.size() == 1);
        assertTrue(bfsTree.get(0).getValue() == 1);

        // Case 3: Travels a path between two adjacent vertices.
        bfsTree = noDirectedGraph.BFS(0, 1);

        assertTrue(bfsTree.size() == 2);
        assertTrue(bfsTree.get(0).getValue() == 1);
        assertTrue(bfsTree.get(1).getValue() == 2);

        // Case 4: Travels a path between two non-adjacent vertices.
        bfsTree = noDirectedGraph.BFS(1, 5);

        assertTrue(bfsTree.size() == 4);
        assertTrue(bfsTree.get(0).getValue() == 2);
        assertTrue(bfsTree.get(1).getValue() == 1);
        assertTrue(bfsTree.get(2).getValue() == 3);
        assertTrue(bfsTree.get(3).getValue() == 6);

        for(int i = 0; i < bfsTree.size(); i++){
            System.out.println("Valor: " + bfsTree.get(i).getValue());
            System.out.println("Color: " + bfsTree.get(i).getColor());
            System.out.println("Distancia desde el nodo " + bfsTree.get(0).getValue() + ": " + bfsTree.get(i).getDistance());
            System.out.println("Predecesor: " + ((bfsTree.get(i).getPredecessor() == null) ? null : bfsTree.get(i).getPredecessor().getValue()));
            System.out.println("\n");
        }
    }

    @Test
    void DFSTest(){
        ArrayList<ArrayList<Vertex<Integer>>> forest;

        // Case 1: Travels a graph with one vertex.
        setupScene1();

        forest = noDirectedGraph.DFS();

        assertTrue(forest.size() == 1); //One DFS tree
        assertTrue(forest.get(0).size() == 1); //A DFS tree with one node.
        assertTrue(forest.get(0).get(0).getValue() == 1);

        // Case 2: Travels a connected graph with multiple vertices.
        setupScene5();

        forest = noDirectedGraph.DFS();

        assertTrue(forest.size() == 1); //One DFS tree
        assertTrue(forest.get(0).size() == 5); //A DFS tree with 3 nodes.
        assertTrue(forest.get(0).get(0).getValue() == 1);
        assertTrue(forest.get(0).get(1).getValue() == 2);
        assertTrue(forest.get(0).get(2).getValue() == 3);
        assertTrue(forest.get(0).get(3).getValue() == 4);
        assertTrue(forest.get(0).get(4).getValue() == 5);

        // Case 3: Travels a disconnected graph with multiple vertices.
        setupScene4();

        forest = noDirectedGraph.DFS();

        assertTrue(forest.size() == 2); //Two DFS trees
        assertTrue(forest.get(0).size() == 6); //A DFS tree with 6 nodes.
        assertTrue(forest.get(1).size() == 3); //A DFS tree with 3 nodes.

        System.out.println("--------First tree-------");

        for(int i = 0; i < forest.get(0).size(); i++){
            System.out.println("Valor: " + forest.get(0).get(i).getValue());
            System.out.println("Color: " + forest.get(0).get(i).getColor());
            System.out.println("Tiempo inicial: " + forest.get(0).get(i).getInitialTime());
            System.out.println("Tiempo final: " + forest.get(0).get(i).getFinalTime());
            System.out.println("Predecesor: " + ((forest.get(0).get(i).getPredecessor() == null) ? null : forest.get(0).get(i).getPredecessor().getValue()));
            System.out.println("\n");
        }

        System.out.println("\n--------Second tree-------");

        for(int i = 0; i < forest.get(1).size(); i++){
            System.out.println("Valor: " + forest.get(1).get(i).getValue());
            System.out.println("Color: " + forest.get(1).get(i).getColor());
            System.out.println("Tiempo inicial: " + forest.get(1).get(i).getInitialTime());
            System.out.println("Tiempo final: " + forest.get(1).get(i).getFinalTime());
            System.out.println("Predecesor: " + ((forest.get(1).get(i).getPredecessor() == null) ? null : forest.get(1).get(i).getPredecessor().getValue()));
            System.out.println("\n");
        }
    }

}
