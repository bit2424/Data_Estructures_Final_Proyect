package Structures.test;

import Structures.Graphs.EdgeL;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.GreaterKeyException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.HeapUnderFlowException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.ThereIsNotAnEdgeException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;
import Structures.Graphs.AdjacencyListGraph;
import Structures.Graphs.VertexL;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest_NonDirectedGraph {

    private AdjacencyListGraph<Integer, Integer> nondirectedGraph;
    private AdjacencyListGraph<String, Integer> nondirectedGraph2;

    private void setupScene1(){
        nondirectedGraph = new AdjacencyListGraph<>(false, true, new VertexL<Integer, Integer>(1)); // First vertex (1)
    }

    private void setupScene2(){
        setupScene1();
        nondirectedGraph.insertVertex(2); // Second vertex (2)
        nondirectedGraph.insertEdge(1, 0, 10); // Between (2) and (1)
        nondirectedGraph.insertVertex(3); // Third vertex (3)
    }

    private void setupScene3(){
        setupScene2();
        nondirectedGraph.insertEdge(1, 0, 40); // Inserts an edge between (2) and (1)
    }

    private void setupScene4(){
        setupScene2();
        nondirectedGraph.insertEdge(1, 1, 20); // Inserts an edge between (2) and (2)
    }

    private void setupScene5(){
        setupScene3();
        nondirectedGraph.insertEdge(2, 1, 30); // Inserts an edge between (3) and (2)
    }

    private void setupScene6(){
        setupScene2();
        nondirectedGraph.insertEdge(2, 2, 100); // Inserts an edge between (3) and (3)
    }

    private void setupScene7(){
        setupScene6();
        nondirectedGraph.insertEdge(2, 2, 200); // Inserts another edge between (3) and (3)
    }

    /**
     * A multigraph with a cycle.
     */
    private void setupScene8(){
        setupScene2();
        nondirectedGraph.insertEdge(2, 1, 50); // Inserts an edge between (3) and (2)
        nondirectedGraph.insertEdge(1, 0, 40); // Inserts an edge between (2) and (1)
        nondirectedGraph.insertEdge(1,1,30); // Inserts an edge between (2) and (2)
        nondirectedGraph.insertEdge(1, 2, 20); // Inserts an edge between (2) and (3)
    }

    /**
     * A disconnected simple graph
     */
    private void setupScene9(){
        setupScene1();
        for(int i = 2; i <= 9; i++)
            nondirectedGraph.insertVertex(i);
        nondirectedGraph.insertEdge(0, 1, 10);
        nondirectedGraph.insertEdge(0, 2, 23);
        nondirectedGraph.insertEdge(0, 3, 15);
        nondirectedGraph.insertEdge(1, 0, 18);
        nondirectedGraph.insertEdge(2, 5, 40);
        nondirectedGraph.insertEdge(3, 4, 12);
        nondirectedGraph.insertEdge(4, 5, 28);
        nondirectedGraph.insertEdge(6, 7, 30);
        nondirectedGraph.insertEdge(7, 8, 39);
    }

    /**
     * A connected simple graph
     */
    private void setupScene10(){
        setupScene1();
        for (int i = 2; i <= 5; i++)
            nondirectedGraph.insertVertex(i);
        nondirectedGraph.insertEdge(0, 1,10);
        nondirectedGraph.insertEdge(1, 2,20);
        nondirectedGraph.insertEdge(2, 3,30);
        nondirectedGraph.insertEdge(3, 4,40);
        nondirectedGraph.insertEdge(4, 0,50);
    }

    /**
     * A second connected simple graph
     */
    private void setupScene11(){
        setupScene1();
        for(int i = 2; i <= 6; i++ )
            nondirectedGraph.insertVertex(i);
        nondirectedGraph.insertEdge(0, 1, 10);
        nondirectedGraph.insertEdge(0, 2, 23);
        nondirectedGraph.insertEdge(0, 3, 15);
        nondirectedGraph.insertEdge(2, 5, 40);
        nondirectedGraph.insertEdge(5, 4, 28);
        nondirectedGraph.insertEdge(3, 4, 12);
    }

    /**
     * Simple connected graph for Dijkstra.
     */
    private void setupScene12(){
        nondirectedGraph2 = new AdjacencyListGraph<>(false, true);
        nondirectedGraph2.insertVertex("A");
        nondirectedGraph2.insertVertex("B");
        nondirectedGraph2.insertVertex("C");
        nondirectedGraph2.insertVertex("D");
        nondirectedGraph2.insertVertex("E");
        nondirectedGraph2.insertVertex("F");
        nondirectedGraph2.insertEdge(0, 1, 3);
        nondirectedGraph2.insertEdge(0, 2, 2);
        nondirectedGraph2.insertEdge(1, 2, 1);
        nondirectedGraph2.insertEdge(1, 3, 4);
        nondirectedGraph2.insertEdge(1, 4, 1);
        nondirectedGraph2.insertEdge(2, 4, 5);
        nondirectedGraph2.insertEdge(3, 4, 2);
        nondirectedGraph2.insertEdge(3, 5, 2);
        nondirectedGraph2.insertEdge(4, 5, 3);
    }

    /**
     * A multigraph for Dijkstra.
     */
    private void setupScene13(){
        nondirectedGraph2 = new AdjacencyListGraph<>(false, true);
        nondirectedGraph2.insertVertex("A");
        nondirectedGraph2.insertVertex("B");
        nondirectedGraph2.insertVertex("C");
        nondirectedGraph2.insertVertex("D");
        nondirectedGraph2.insertVertex("E");
        nondirectedGraph2.insertVertex("F");
        nondirectedGraph2.insertEdge(0, 1, 3);
        nondirectedGraph2.insertEdge(0, 2, 1);
        nondirectedGraph2.insertEdge(0, 2, 2);
        nondirectedGraph2.insertEdge(0, 3, 2);
        nondirectedGraph2.insertEdge(0, 3, 2);
        nondirectedGraph2.insertEdge(1, 3, 4);
        nondirectedGraph2.insertEdge(1, 4, 5);
        nondirectedGraph2.insertEdge(1, 4, 1);
        nondirectedGraph2.insertEdge(3, 4, 3);
        nondirectedGraph2.insertEdge(3, 4, 2);
        nondirectedGraph2.insertEdge(3, 4, 1);
        nondirectedGraph2.insertEdge(3, 5, 7);
        nondirectedGraph2.insertEdge(4, 5, 1);
    }

    @Test
    void constructorMethodTest(){
        setupScene1();

        assertFalse(nondirectedGraph.isDirected()); // Tests if the graph is directed
        assertTrue(nondirectedGraph.isWeighted()); // Tests if the graph is weighted
        assertEquals(1, nondirectedGraph.getNumberOfVertices()); // Tests the number of vertices in the graph
        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getValue()); // Tests if the first vertex is correct
        assertEquals(0, nondirectedGraph.getNumberOfEdges()); // Tests the number of edges in the graph
    }

    @Test
    void insertVertexTest(){
        // Case 1: Insert a vertex with no connexion to any of the other vertex in the graph.
        setupScene1();
        nondirectedGraph.insertVertex(2);

        assertEquals(2, nondirectedGraph.getNumberOfVertices());
        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getValue());
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree()); // Adjacent vertices to (1).
        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getValue());
        assertEquals(0, nondirectedGraph.getVerticesL().get(1).getDegree()); // Adjacent vertices to (2).
    }

    @Test
    void insertEdgeTest(){
        ArrayList<EdgeL<Integer, Integer>> edges;
        // Case 1: Connect a vertex to another which were not connected previously.
        setupScene1();
        nondirectedGraph.insertVertex(2);
        nondirectedGraph.insertEdge(0, 1, 5);

        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(1, nondirectedGraph.getVerticesL().get(1).getDegree());

        edges = nondirectedGraph.getEdges(0, 1);

        assertEquals(1, edges.size());
        assertEquals(1, edges.get(0).getOriginVertex().getValue()); // The vertex which is adjacent to (2).
        assertEquals(2, edges.get(0).getDestinationVertex().getValue()); // The vertex which is adjacent to (1).
        assertEquals(5, edges.get(0).getWeight()); // The weight of the edge.


        // Case 2: Add an edge from one vertex to the same one.
        setupScene1();
        nondirectedGraph.insertEdge(0,0,10);

        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getDegree());

        edges = nondirectedGraph.getEdges(0, 0);

        assertEquals(1, edges.size());
        assertEquals(nondirectedGraph.getVerticesL().get(0), edges.get(0).getOriginVertex()); // The edge points to the same vertex making a cycle.
        assertEquals(nondirectedGraph.getVerticesL().get(0), edges.get(0).getDestinationVertex()); // The edge points to the same vertex making a cycle.
        assertEquals(10, edges.get(0).getWeight()); // The weight of the edge.

        // Case 3: Add an edge between two vertices which were already connected before.
        setupScene2();
        nondirectedGraph.insertEdge(1, 0, 20);

        edges = nondirectedGraph.getEdges(1, 0);
        Collections.reverse(edges);
        assertEquals(2, edges.size());

        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
        assertEquals(nondirectedGraph.getVerticesL().get(1).getValue(), edges.get(0).getDestinationVertex().getValue()); //The destination vertex of the edge from (2) to (1)
        assertEquals(nondirectedGraph.getVerticesL().get(0).getValue(), edges.get(0).getOriginVertex().getValue()); //The origin vertex of the edge from (2) to (1)
        assertEquals(10, edges.get(0).getWeight()); // The weight of the edge.

        assertEquals(nondirectedGraph.getVerticesL().get(1), edges.get(1).getDestinationVertex()); //The destination vertex of the edge from (2) to (1)
        assertEquals(nondirectedGraph.getVerticesL().get(0), edges.get(1).getOriginVertex()); //The origin vertex of the edge from (2) to (1)
        assertEquals(20, edges.get(1).getWeight()); // The weight of the edge.

        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
    }

    @Test
    void deleteVertexTest(){
        setupScene8();

        assertEquals(3, nondirectedGraph.getNumberOfVertices()); // Number of vertices in the graph

        // Case 1: Delete a vertex which does not exist.
        try{
            nondirectedGraph.deleteVertex(3);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Delete a vertex with no connection to any of the others.
        setupScene2();

        assertEquals(3, nondirectedGraph.getNumberOfVertices()); // Number of vertices in the graph

        nondirectedGraph.deleteVertex(2);

        assertEquals(2, nondirectedGraph.getNumberOfVertices()); // Number of vertices in the graph

        // Case 5: Delete a vertex with multiple connections to any others.
        setupScene8();

        assertEquals(3, nondirectedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals(5, nondirectedGraph.getNumberOfEdges()); // Number of edges in the graph
        assertEquals( 1, nondirectedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 2, nondirectedGraph.getVerticesL().get(1).getValue()); // Vertex (2)
        assertEquals( 3, nondirectedGraph.getVerticesL().get(2).getValue()); // Vertex (3)
        assertEquals(2, nondirectedGraph.getVerticesL().get(0).getDegree()); // The degree of the vertex (1)
        assertEquals(5, nondirectedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2)
        assertEquals(2, nondirectedGraph.getVerticesL().get(2).getDegree()); // The degree of the vertex (3)

        nondirectedGraph.deleteVertex(1);

        assertEquals(2, nondirectedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals(0, nondirectedGraph.getNumberOfEdges()); // Number of edges in the graph
        assertEquals( 1, nondirectedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 3, nondirectedGraph.getVerticesL().get(1).getValue()); // Vertex (3)
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree()); // The degree of the vertex (1)
        assertEquals(0, nondirectedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (3)
    }

    @Test
    void deleteEdgeTest() throws ThereIsNotAnEdgeException {
        setupScene8();

        // Case 1: Delete an edge which does not exist.
        try{
            nondirectedGraph.deleteEdge(1, 0, 55);
            fail();
        }
        catch (IndexOutOfBoundsException | ThereIsNotAnEdgeException e){
            assertTrue(true);
        }

        // Case 2: Delete an edge between two vertices in a simple graph.
        setupScene2();
        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(1, nondirectedGraph.getVerticesL().get(1).getDegree());
        nondirectedGraph.deleteEdge(1, 0, 10);
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, nondirectedGraph.getVerticesL().get(1).getDegree());

        // Case 3: Delete an edge between two vertices in a multigraph.
        setupScene3();
        assertEquals(2, nondirectedGraph.getNumberOfEdges());
        assertEquals(2, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getDegree());
        nondirectedGraph.deleteEdge(1, 0, 10);
        assertEquals(1, nondirectedGraph.getNumberOfEdges());
        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(1, nondirectedGraph.getVerticesL().get(1).getDegree());
        assertEquals(40, nondirectedGraph.getEdges().get(0).getWeight());
        assertEquals(nondirectedGraph.getVerticesL().get(1).getValue(), nondirectedGraph.getEdges().get(0).getDestinationVertex().getValue());
        assertEquals(nondirectedGraph.getVerticesL().get(0).getValue(), nondirectedGraph.getEdges().get(0).getOriginVertex().getValue());

        // Case 4: Delete an edge pointing to the same vertex.
        setupScene4();

        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getDegree());
        assertEquals(2, nondirectedGraph.getNumberOfEdges());
        nondirectedGraph.deleteEdge(1, 1, 20);
        assertEquals(1, nondirectedGraph.getVerticesL().get(1).getDegree());
        assertEquals(1, nondirectedGraph.getNumberOfEdges());
        assertEquals(10, nondirectedGraph.getEdges().get(0).getWeight());
    }

    @Test
    void deleteAllEdge() throws ThereIsNotAnEdgeException {
        // Case 1: Two vertices without any edges between them.
        setupScene3();
        try{
            nondirectedGraph.deleteAllEdge(0, 2); // Vertices (1) and (3) are not connected.
            fail();
        }
        catch (ThereIsNotAnEdgeException e){
            assertTrue(true);
        }

        // Case 2: Two vertices connected by one edge without any other edges to other vertices.
        setupScene2();
        assertEquals(1, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(1, nondirectedGraph.getVerticesL().get(1).getDegree());
        nondirectedGraph.deleteAllEdge(1, 0);
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, nondirectedGraph.getVerticesL().get(1).getDegree());


        // Case 3: Two vertices connected by one edge with other edges to other vertices.
        setupScene5();
        nondirectedGraph.deleteAllEdge(2, 1);
        assertEquals(2, nondirectedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, nondirectedGraph.getVerticesL().get(2).getDegree());

        // Case 4: Two vertices connected by many edges without any other edges to other vertices.
        setupScene3();
        nondirectedGraph.deleteAllEdge(1, 0);
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, nondirectedGraph.getVerticesL().get(1).getDegree());

        // Case 5: Two vertices connected by many edges with other edges to other vertices.
        setupScene8();
        assertEquals(2, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(5, nondirectedGraph.getVerticesL().get(1).getDegree());
        nondirectedGraph.deleteAllEdge(1, 0);
        assertEquals(0, nondirectedGraph.getVerticesL().get(0).getDegree());
        assertEquals(3, nondirectedGraph.getVerticesL().get(1).getDegree());

        // Case 6: One edge from one vertex to the same one without any connection to other vertices.
        setupScene6();
        nondirectedGraph.deleteAllEdge(2, 2);
        assertEquals(0, nondirectedGraph.getVerticesL().get(2).getDegree());

        // Case 7: One edge from one vertex to the same one with other edges to other vertices.
        setupScene6();
        nondirectedGraph.insertEdge(2, 1, 50);
        nondirectedGraph.deleteAllEdge(2, 2);
        assertEquals(1, nondirectedGraph.getVerticesL().get(2).getDegree());

        // Case 8: Many edges from one vertex to the same one without any connection to other vertices.
        setupScene7();
        nondirectedGraph.deleteAllEdge(2, 2);
        assertEquals(0, nondirectedGraph.getVerticesL().get(2).getDegree());

        // Case 9: Many edges from one vertex to the same one with other edges to other vertices.
        setupScene7();
        nondirectedGraph.insertEdge(2, 1, 50);
        nondirectedGraph.deleteAllEdge(2, 2);
        assertEquals(1, nondirectedGraph.getVerticesL().get(2).getDegree());
    }

    private void printBFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = nondirectedGraph.getVerticesL().get(j).getPredecessor();
            System.out.println("Valor: " + nondirectedGraph.getVerticesL().get(j).getValue());
            System.out.println("Color: " + nondirectedGraph.getVerticesL().get(j).getColor());
            System.out.println("Distancia desde el vértice " + nondirectedGraph.getVerticesL().get(tree.get(0)).getValue() + ": " + nondirectedGraph.getVerticesL().get(j).getDistance());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : nondirectedGraph.getVerticesL().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    private void printDFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = nondirectedGraph.getVerticesL().get(j).getPredecessor();
            System.out.println("Valor: " + nondirectedGraph.getVerticesL().get(j).getValue());
            System.out.println("Color: " + nondirectedGraph.getVerticesL().get(j).getColor());
            System.out.println("Tiempo inicial: " + nondirectedGraph.getVerticesL().get(j).getInitialTime());
            System.out.println("Tiempo final: " + nondirectedGraph.getVerticesL().get(j).getFinalTime());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : nondirectedGraph.getVerticesL().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    @Test
    void BFSWithStartPositionTest() throws UnderflowException {
        ArrayList<Integer> tree = null;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = nondirectedGraph.BFS(0);
        assertEquals(1, tree.size());
        assertEquals(nondirectedGraph.getVerticesL().get(0), nondirectedGraph.getVerticesL().get(tree.get(0)));

        // Case 2: A disconnected graph of n vertices.
        setupScene9();
        tree = nondirectedGraph.BFS(0);
        assertEquals(6, tree.size());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(0, nondirectedGraph.getVerticesL().get(tree.get(0)).getDistance());
        assertEquals(-1, nondirectedGraph.getVerticesL().get(tree.get(0)).getPredecessor());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(1)).getDistance());
        assertEquals(0, nondirectedGraph.getVerticesL().get(tree.get(1)).getPredecessor());
        assertEquals(3, nondirectedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(2)).getDistance());
        assertEquals(0, nondirectedGraph.getVerticesL().get(tree.get(2)).getPredecessor());
        assertEquals(4, nondirectedGraph.getVerticesL().get(tree.get(3)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(3)).getDistance());
        assertEquals(0, nondirectedGraph.getVerticesL().get(tree.get(3)).getPredecessor());
        assertEquals(6, nondirectedGraph.getVerticesL().get(tree.get(4)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(4)).getDistance());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(4)).getPredecessor());
        assertEquals(5, nondirectedGraph.getVerticesL().get(tree.get(5)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(5)).getDistance());
        assertEquals(3, nondirectedGraph.getVerticesL().get(tree.get(5)).getPredecessor());

        tree = nondirectedGraph.BFS(6);
        assertEquals(3, tree.size());
        assertEquals(7, nondirectedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(0, nondirectedGraph.getVerticesL().get(tree.get(0)).getDistance());
        assertEquals(-1, nondirectedGraph.getVerticesL().get(tree.get(0)).getPredecessor());
        assertEquals(8, nondirectedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(1)).getDistance());
        assertEquals(6, nondirectedGraph.getVerticesL().get(tree.get(1)).getPredecessor());
        assertEquals(9, nondirectedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(2)).getDistance());
        assertEquals(7, nondirectedGraph.getVerticesL().get(tree.get(2)).getPredecessor());

        // Case 3: A connected graph with n vertices.
        setupScene10();
        tree = nondirectedGraph.BFS(0);
        assertEquals(5, tree.size());

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("\n------------------------------ BFS with start position ------------------------------");
        System.out.println("--------- Starting from (3) ---------");
        tree = nondirectedGraph.BFS(2);
        assertEquals(3, tree.size());
        printBFSTree(tree);
        System.out.println("--------- Starting from (2) ---------");
        tree = nondirectedGraph.BFS(1);
        assertEquals(3, tree.size());
        printBFSTree(tree);
    }

    @Test
    void BFSWithoutParametersTest() throws UnderflowException {
        ArrayList<ArrayList<Integer>> forest;
        // Case 1: A graph with one vertex.
        setupScene1();
        forest = nondirectedGraph.BFS();
        assertEquals(1, forest.size());
        assertEquals(1, forest.get(0).size());
        assertEquals(nondirectedGraph.getVerticesL().get(0), nondirectedGraph.getVerticesL().get(forest.get(0).get(0)));

        // Case 2: A disconnected graph of n vertices.
        setupScene9();
        forest = nondirectedGraph.BFS();
        assertEquals(2, forest.size());
        assertEquals(6, forest.get(0).size());
        assertEquals(3, forest.get(1).size());
        System.out.println("\n------------------------------ BFS without start position ------------------------------");
        System.out.println("===================CASE 2: DISCONNECTED GRAPH===================");
        System.out.println("--------First tree-------");
        printBFSTree(forest.get(0));
        System.out.println("\n--------Second tree-------");
        printBFSTree(forest.get(1));

        // Case 3: A connected graph with n vertices.
        setupScene10();
        forest = nondirectedGraph.BFS();
        assertEquals(1, forest.size());
        assertEquals(5, forest.get(0).size());
        System.out.println("===================CASE 3: CONNECTED GRAPH===================");
        printBFSTree(forest.get(0));

        // Case 4: A graph with a cycle.
        setupScene8();
        forest = nondirectedGraph.BFS();
        assertEquals(1, forest.size());
        assertEquals(3, forest.get(0).size());
        assertEquals(1, nondirectedGraph.getVerticesL().get(forest.get(0).get(0)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(forest.get(0).get(1)).getValue());
        assertEquals(3, nondirectedGraph.getVerticesL().get(forest.get(0).get(2)).getValue());
    }

    @Test
    void DFSWithInitialVertexTest(){
        ArrayList<Integer> tree;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = nondirectedGraph.DFS(0);
        assertEquals(1, tree.size());
        assertEquals(nondirectedGraph.getVerticesL().get(0), nondirectedGraph.getVerticesL().get(tree.get(0)));

        // Case 2: A connected graph of n vertices.
        setupScene11();
        tree = nondirectedGraph.DFS(2);
        assertEquals(6, tree.size());
        assertEquals(3, nondirectedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(4, nondirectedGraph.getVerticesL().get(tree.get(3)).getValue());
        assertEquals(5, nondirectedGraph.getVerticesL().get(tree.get(4)).getValue());
        assertEquals(6, nondirectedGraph.getVerticesL().get(tree.get(5)).getValue());

        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        tree = nondirectedGraph.DFS(1);
        assertEquals(6, tree.size());
        System.out.println("\n------------------------------ DFS with start position ------------------------------");
        System.out.println("---------- Case 3: A disconnected graph of n vertices ---------");
        System.out.println("--------First tree-------");
        printDFSTree(tree);

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("--------- Starting from (3) ---------");
        tree = nondirectedGraph.DFS(2);
        assertEquals(3, tree.size());
        printDFSTree(tree);
        System.out.println("--------- Starting from (2) ---------");
        tree = nondirectedGraph.DFS(1);
        assertEquals(3, tree.size());
        printDFSTree(tree);
    }

    @Test
    void DFSWithoutStartPositionTest(){
        ArrayList<ArrayList<Integer>> forest;
        // Case 1: A graph with one vertex.
        setupScene1();
        forest = nondirectedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(1, forest.get(0).size());
        assertEquals(1, nondirectedGraph.getVerticesL().get(forest.get(0).get(0)).getValue());

        // Case 2: A connected graph of n vertices.
        setupScene11();
        forest = nondirectedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(6, forest.get(0).size());
        System.out.println("\n------------------------------ DFS without start position ------------------------------");
        System.out.println("---------- Case 2: A connected graph of n vertices ---------");
        printDFSTree(forest.get(0));

        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        forest = nondirectedGraph.DFS();
        assertEquals(2, forest.size());
        assertEquals(6, forest.get(0).size());
        assertEquals(3, forest.get(1).size());
        System.out.println("===================CASE 3: DISCONNECTED GRAPH===================");
        System.out.println("--------First tree-------");
        printDFSTree(forest.get(0));
        System.out.println("\n--------Second tree-------");
        printDFSTree(forest.get(1));
    }

    private void printPrimTree(ArrayList<Integer> tree){
        for(int i = tree.size() - 1; i >= 0; i--) {
            int j = tree.get(i);
            int pred = nondirectedGraph.getVerticesL().get(j).getPredecessor();
            System.out.println("Vértice: " + nondirectedGraph.getVerticesL().get(j).getValue());
            System.out.println("Predecesor: " + ((pred == -1) ? null : nondirectedGraph.getVerticesL().get(pred).getValue()));
            System.out.println("Peso: " + nondirectedGraph.getVerticesL().get(j).getDistance());
            System.out.println("\n");
        }
    }

    @Test
    void PrimTest() throws GreaterKeyException, HeapUnderFlowException {
        ArrayList<Integer> tree;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = nondirectedGraph.Prim(0);
        assertEquals(1, tree.size());

        // Case 2: A connected graph.
        setupScene11();
        tree = nondirectedGraph.Prim(0);
        assertEquals(6, tree.size());
        assertEquals(6, nondirectedGraph.getVerticesL().get(tree.get(5)).getValue());
        assertEquals(3, nondirectedGraph.getVerticesL().get(tree.get(4)).getValue());
        assertEquals(5, nondirectedGraph.getVerticesL().get(tree.get(3)).getValue());
        assertEquals(4, nondirectedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(0)).getValue());
        System.out.println("\n------------------------------ PRIM ------------------------------");
        System.out.println("------------- Case 2: A connected graph -------------");
        printPrimTree(tree);

        // Case 3: A multigraph.
        setupScene5();
        tree = nondirectedGraph.Prim(1);
        assertEquals(3, tree.size());
        assertEquals(2, nondirectedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(1, nondirectedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(3, nondirectedGraph.getVerticesL().get(tree.get(2)).getValue());
        System.out.println("------------- Case 3: A multigraph -------------");
        printPrimTree(tree);
    }

    @Test
    void KruskalTest() throws HeapUnderFlowException {
        ArrayList<EdgeL<String, Integer>> mst;

        // Case 1: A graph with one vertex.
        setupScene1();
        ArrayList<EdgeL<Integer, Integer>> mst1 = nondirectedGraph.Kruskal();
        assertEquals(0, mst1.size());

        // Case 2: A connected graph.
        setupScene12();

        mst = nondirectedGraph2.Kruskal();

        assertEquals(5, mst.size());
        assertEquals("B", mst.get(0).getOriginVertex().getValue());
        assertEquals("E", mst.get(0).getDestinationVertex().getValue());
        assertEquals(1, mst.get(0).getWeight());

        assertEquals("B", mst.get(1).getOriginVertex().getValue());
        assertEquals("C", mst.get(1).getDestinationVertex().getValue());
        assertEquals(1, mst.get(1).getWeight());

        assertEquals("A", mst.get(2).getOriginVertex().getValue());
        assertEquals("C", mst.get(2).getDestinationVertex().getValue());
        assertEquals(2, mst.get(2).getWeight());

        assertEquals("D", mst.get(3).getOriginVertex().getValue());
        assertEquals("E", mst.get(3).getDestinationVertex().getValue());
        assertEquals(2, mst.get(3).getWeight());

        assertEquals("D", mst.get(4).getOriginVertex().getValue());
        assertEquals("F", mst.get(4).getDestinationVertex().getValue());
        assertEquals(2, mst.get(4).getWeight());

        System.out.println("------------------------------ KRUSKAL ------------------------------");
        System.out.println("--------------- case 2: A connected graph ---------------");
        printKruskal(mst);

        // Case 3: A multigraph.
        setupScene13();

        mst = nondirectedGraph2.Kruskal();

        assertEquals(5, mst.size());
        assertEquals("B", mst.get(0).getOriginVertex().getValue());
        assertEquals("E", mst.get(0).getDestinationVertex().getValue());
        assertEquals(1, mst.get(0).getWeight());

        assertEquals("D", mst.get(1).getOriginVertex().getValue());
        assertEquals("E", mst.get(1).getDestinationVertex().getValue());
        assertEquals(1, mst.get(1).getWeight());

        assertEquals("A", mst.get(2).getOriginVertex().getValue());
        assertEquals("C", mst.get(2).getDestinationVertex().getValue());
        assertEquals(1, mst.get(2).getWeight());

        assertEquals("E", mst.get(3).getOriginVertex().getValue());
        assertEquals("F", mst.get(3).getDestinationVertex().getValue());
        assertEquals(1, mst.get(3).getWeight());

        assertEquals("A", mst.get(4).getOriginVertex().getValue());
        assertEquals("D", mst.get(4).getDestinationVertex().getValue());
        assertEquals(2, mst.get(4).getWeight());

        System.out.println("--------------- case 3: A multigraph ---------------");
        printKruskal(mst);

    }

    private void printKruskal(ArrayList<EdgeL<String, Integer>> tree){
        for(int i = 0; i < tree.size(); i++){
            System.out.print(tree.get(i).getOriginVertex().getValue() + " - " + tree.get(i).getDestinationVertex().getValue() + " : ");
            System.out.print(tree.get(i).getWeight());
            System.out.println();
        }
    }

    @Test
    void DijkstraTest(){
        Object[] a;
        double[] dist;
        int[] pred;
        // Case 1: A graph with one vertex.
        setupScene1();
        a = nondirectedGraph.Dijsktra(0);
        dist = (double[]) a[0];
        pred = (int[]) a[1];
        assertEquals(1, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(1, pred.length);
        assertEquals(-1, pred[0]);

        // Case 2: A simple connected graph
        setupScene12();
        a = nondirectedGraph2.Dijsktra(0);
        dist = (double[]) a[0];
        pred = (int[]) a[1];

        assertEquals(6, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(6, dist[3]);
        assertEquals(4, dist[4]);
        assertEquals(7, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(0, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(4, pred[3]);
        assertEquals(1, pred[4]);
        assertEquals(4, pred[5]);

        a = nondirectedGraph2.Dijsktra(3);
        dist = (double[]) a[0];
        pred = (int[]) a[1];

        assertEquals(6, dist.length);
        assertEquals(6, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(4, dist[2]);
        assertEquals(0, dist[3]);
        assertEquals(2, dist[4]);
        assertEquals(2, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(1, pred[0]);
        assertEquals(4, pred[1]);
        assertEquals(1, pred[2]);
        assertEquals(-1, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(3, pred[5]);

        // Case 3: A connected multigraph
        setupScene13();
        a = nondirectedGraph2.Dijsktra(0);
        dist = (double[]) a[0];
        pred = (int[]) a[1];

        assertEquals(6, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(1, dist[2]);
        assertEquals(2, dist[3]);
        assertEquals(3, dist[4]);
        assertEquals(4, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(0, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(0, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(4, pred[5]);

        a = nondirectedGraph2.Dijsktra(4);
        dist = (double[]) a[0];
        pred = (int[]) a[1];

        assertEquals(6, dist.length);
        assertEquals(3, dist[0]);
        assertEquals(1, dist[1]);
        assertEquals(4, dist[2]);
        assertEquals(1, dist[3]);
        assertEquals(0, dist[4]);
        assertEquals(1, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(3, pred[0]);
        assertEquals(4, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(4, pred[3]);
        assertEquals(-1, pred[4]);
        assertEquals(4, pred[5]);

        // Case 3: Begin the path from a vertex which does not exist.
        setupScene1();
        try{
            a = nondirectedGraph.Dijsktra(90);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }
    }

}

