package Estructures.test;

import Estructures.Graphs.AdjacencyListGraph;
import Estructures.Graphs.Edge;
import Estructures.Graphs.Vertex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class AdjacencyListDirectedGraphTest {

    private AdjacencyListGraph<Integer> directedGraph;

    private void setupScene1(){
        directedGraph = new AdjacencyListGraph<>(true, true, new Vertex<>(1));
    }

    private void setupScene2(){
        setupScene1();

        Edge<Integer> edgeTo1 = new Edge<>(directedGraph.getVertices().get(0), 10);
        directedGraph.insertVertex(2, edgeTo1);
        directedGraph.insertVertex(3);
    }

    private void setupScene3(){
        setupScene2();

        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(2), 20);
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(1), 30);
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(0), 40);
        directedGraph.addEdge(directedGraph.getVertices().get(2), directedGraph.getVertices().get(1), 50);
    }

    private void setupScene4(){
        directedGraph = new AdjacencyListGraph<>(true,true, new Vertex<>(1));

        directedGraph.insertVertex(2, new Edge<>(directedGraph.getVertices().get(0), 10));
        directedGraph.addEdge(directedGraph.getVertices().get(0), directedGraph.getVertices().get(1), 2);
        directedGraph.insertVertex(3);
        directedGraph.insertVertex(4);
        directedGraph.addEdge(directedGraph.getVertices().get(0), directedGraph.getVertices().get(2), 23);
        directedGraph.addEdge(directedGraph.getVertices().get(0), directedGraph.getVertices().get(3), 15);
        directedGraph.insertVertex(5);
        directedGraph.insertVertex(6);
        directedGraph.addEdge(directedGraph.getVertices().get(2), directedGraph.getVertices().get(5), 40);
        directedGraph.addEdge(directedGraph.getVertices().get(3), directedGraph.getVertices().get(4), 12);
        directedGraph.addEdge(directedGraph.getVertices().get(4), directedGraph.getVertices().get(5), 28);
        directedGraph.insertVertex(7);
        directedGraph.insertVertex(8);
        directedGraph.insertVertex(9);
        directedGraph.addEdge(directedGraph.getVertices().get(6), directedGraph.getVertices().get(7), 30);
        directedGraph.addEdge(directedGraph.getVertices().get(7), directedGraph.getVertices().get(8), 39);
        directedGraph.addEdge(directedGraph.getVertices().get(8), directedGraph.getVertices().get(6), 20);
    }

    private void setupScene5(){
        setupScene1();

        directedGraph.insertVertex(2);
        directedGraph.insertVertex(3);
        directedGraph.insertVertex(4);
        directedGraph.insertVertex(5);

        directedGraph.addEdge(directedGraph.getVertices().get(0), directedGraph.getVertices().get(1), 10);
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(2), 20);
        directedGraph.addEdge(directedGraph.getVertices().get(2), directedGraph.getVertices().get(3), 30);
        directedGraph.addEdge(directedGraph.getVertices().get(3), directedGraph.getVertices().get(4), 40);
        directedGraph.addEdge(directedGraph.getVertices().get(4), directedGraph.getVertices().get(0), 50);
    }

    @Test
    void constructorTest(){
        setupScene1();

        assertTrue(directedGraph.isDirected());
        assertTrue(directedGraph.isWeighted());
        assertTrue(directedGraph.getVertices().size() == 1);
        assertTrue(directedGraph.getVertices().get(0).getValue() == 1);
    }

    @Test
    void insertVertexTest(){
        setupScene1();

        assertTrue(directedGraph.getVertices().size() == 1);

        // Case 1: Inserts a vertex with one connexion to the first vertex inserted by the constructor method.
        Edge<Integer> edge = new Edge<>(directedGraph.getVertices().get(0), 10);
        directedGraph.insertVertex(2, edge);

        assertTrue(directedGraph.getVertices().size() == 2);
        assertTrue(directedGraph.getVertices().get(0).getAdjacencyList().size() == 0);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 1);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);

        // Case 2: Inserts a vertex with one connexion to each vertex already inserted in the graph.
        Edge<Integer> edge1 = new Edge<>(directedGraph.getVertices().get(0), 20);
        Edge<Integer> edge2 = new Edge<>(directedGraph.getVertices().get(1), 30);
        ArrayList<Edge<Integer>> adj = new ArrayList<>();
        adj.add(edge1);
        adj.add(edge2);
        directedGraph.insertVertex(3, adj);

        assertTrue(directedGraph.getVertices().size() == 3);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 20);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(1).getVertex().getValue() == 2);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(1).getWeight() == 30);

        // Case 3: Inserts a vertex with no connexion to any of the other vertex in the graph.
        directedGraph.insertVertex(4);

        assertTrue(directedGraph.getVertices().size() == 4);
        assertTrue(directedGraph.getVertices().get(3).getValue() == 4);
        assertTrue(directedGraph.getVertices().get(3).getAdjacencyList().size() == 0);
    }

    @Test
    void addEdgeTest(){
        setupScene2();

        // Case 1: Adds an edge between two vertices which are not connected.
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(2), 20);

        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 10);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 3);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(1).getWeight() == 20);

        assertTrue(directedGraph.getVertices().get(0).getAdjacencyList().size() == 0);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 0);

        // Case 2: Adds an edge form one vertex to the same one.
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(1), 30);

        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 3);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(2).getVertex().getValue() == 2);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(2).getWeight() == 30);

        // Case 3: Adds an edge with the same direction than the one already inserted between two vertices.
        directedGraph.addEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(0), 40);

        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 4);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(3).getVertex().getValue() == 1);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(3).getWeight() == 40);

        // Case 4: Adds an edge with an opposite direction than the one already inserted between two vertices.
        directedGraph.addEdge(directedGraph.getVertices().get(2), directedGraph.getVertices().get(1), 50);

        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 1);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 2);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 50);
    }

    @Test
    void deleteEdge(){
        setupScene3();

        // Case 1: Deletes an edge which does not exist.
        try{
            directedGraph.deleteEdge(directedGraph.getVertices().get(0), directedGraph.getVertices().get(1), 10);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Deletes an edge from a vertex to another one.
        directedGraph.deleteEdge(directedGraph.getVertices().get(2), directedGraph.getVertices().get(1), 50);

        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 0);

        // Case 3: Deletes an edge whose origin vertex is equal to target vertex.
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 4);

        directedGraph.deleteEdge(directedGraph.getVertices().get(1), directedGraph.getVertices().get(1), 30);

        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 3);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 1);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 3);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().get(2).getVertex().getValue() == 1);

    }

    @Test
    void deleteVertexTest(){
        setupScene3();

        assertTrue(directedGraph.getVertices().size() == 3);

        // Case 1: Deletes a vertex which does not exist.
        try{
            directedGraph.deleteVertex(3);
            assertTrue(false);
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Deletes a vertex with no connection to any of the others.
        setupScene2();

        assertTrue(directedGraph.getVertices().size() == 3);

        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 0);

        directedGraph.deleteVertex(2);

        assertTrue(directedGraph.getVertices().size() == 2);

        // Case 3: Deletes a leaf vertex.
        setupScene2();

        directedGraph.deleteVertex(0);

        assertTrue(directedGraph.getVertices().size() == 2);

        // Case 4: Deletes a vertex with multiple connections to any others.
        setupScene3();

        assertTrue(directedGraph.getVertices().size() == 3);
        assertTrue(directedGraph.getVertices().get(0).getValue() == 1);
        assertTrue(directedGraph.getVertices().get(1).getValue() == 2);
        assertTrue(directedGraph.getVertices().get(2).getValue() == 3);
        assertTrue(directedGraph.getVertices().get(2).getAdjacencyList().size() == 1);

        directedGraph.deleteVertex(1);

        assertTrue(directedGraph.getVertices().size() == 2);
        assertTrue(directedGraph.getVertices().get(0).getValue() == 1);
        assertTrue(directedGraph.getVertices().get(1).getValue() == 3);
        assertTrue(directedGraph.getVertices().get(1).getAdjacencyList().size() == 0);
    }

    @Test
    void BFSTest() {
        setupScene4();

        ArrayList<Vertex<Integer>> bfsTree;

        // Case 1: Travels a path which does not exist.
        bfsTree = directedGraph.BFS(0, 8);

        assertNull(bfsTree);

        // Case 2: Travels a path between the same vertex.
        bfsTree = directedGraph.BFS(0, 0);

        assertTrue(bfsTree.size() == 1);
        assertTrue(bfsTree.get(0).getValue() == 1);

        // Case 3: Travels a path between two adjacent vertices.
        bfsTree = directedGraph.BFS(0, 1);

        assertTrue(bfsTree.size() == 2);
        assertTrue(bfsTree.get(0).getValue() == 1);
        assertTrue(bfsTree.get(1).getValue() == 2);

        // Case 4: Travels a path between two non-adjacent vertices.
        bfsTree = directedGraph.BFS(1, 5);

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

        forest = directedGraph.DFS();

        assertTrue(forest.size() == 1); //One DFS tree
        assertTrue(forest.get(0).size() == 1); //A DFS tree with one node.
        assertTrue(forest.get(0).get(0).getValue() == 1);

        // Case 2: Travels a connected graph with multiple vertices.
        setupScene5();

        forest = directedGraph.DFS();

        assertTrue(forest.size() == 1); //One DFS tree
        assertTrue(forest.get(0).size() == 5); //A DFS tree with 3 nodes.
        assertTrue(forest.get(0).get(0).getValue() == 1);
        assertTrue(forest.get(0).get(1).getValue() == 2);
        assertTrue(forest.get(0).get(2).getValue() == 3);
        assertTrue(forest.get(0).get(3).getValue() == 4);
        assertTrue(forest.get(0).get(4).getValue() == 5);

        // Case 3: Travels a disconnected graph with multiple vertices.
        setupScene4();

        forest = directedGraph.DFS();

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

