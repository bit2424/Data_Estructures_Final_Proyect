package Structures.test;

import Structures.Graphs.EdgeL;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.ThereIsNotAnEdgeException;
import Structures.auxiliary_structures.exceptions_auxiliary_structures.UnderflowException;
import Structures.Graphs.AdjacencyListGraph;
import Structures.Graphs.VertexL;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest_DirectedGraph {

    private AdjacencyListGraph<Integer, Integer> directedGraph;
    private AdjacencyListGraph<String, Integer> directedGraph2;

    private void setupScene1(){
        directedGraph = new AdjacencyListGraph<>(true, true, new VertexL<Integer, Integer>(1)); // First vertex (1)
    }

    private void setupScene2(){
        setupScene1();
        directedGraph.insertVertex(2); // Second vertex (2)
        directedGraph.insertEdge(1, 0, 10); // From (2) to (1)
        directedGraph.insertVertex(3); // Third vertex (3)
    }

    private void setupScene3(){
        setupScene2();
        directedGraph.insertEdge(1, 0, 40); // Inserts an edge from (2) to (1)
    }

    private void setupScene4(){
        setupScene2();
        directedGraph.insertEdge(1, 1, 20); // Inserts an edge from (2) to (2)
    }

    private void setupScene5(){
        setupScene3();
        directedGraph.insertEdge(2, 1, 30); // Inserts an edge from (3) to (2)
    }

    private void setupScene6(){
        setupScene2();
        directedGraph.insertEdge(2, 2, 100); // Inserts an edge from (3) to (3)
    }

    private void setupScene7(){
        setupScene6();
        directedGraph.insertEdge(2, 2, 200); // Inserts another edge from (3) to (3)
    }

    /**
     * A multigraph with a cycle.
     */
    private void setupScene8(){
        setupScene2();
        directedGraph.insertEdge(2, 1, 50); // From (3) to (2)
        directedGraph.insertEdge(1, 0, 40); // From (2) to (1)
        directedGraph.insertEdge(1,1,30); // From (2) to (2)
        directedGraph.insertEdge(1, 2, 20); // From (2) to (3)
    }


    /**
     * A disconnected simple graph
     */
    private void setupScene9(){
        setupScene1();
        for(int i = 2; i <= 9; i++){
            directedGraph.insertVertex(i);
        }
        directedGraph.insertEdge(0, 1, 10);
        directedGraph.insertEdge(0, 2, 23);
        directedGraph.insertEdge(0, 3, 15);
        directedGraph.insertEdge(1, 0, 18);
        directedGraph.insertEdge(2, 5, 40);
        directedGraph.insertEdge(3, 4, 12);
        directedGraph.insertEdge(4, 5, 28);
        directedGraph.insertEdge(6, 7, 30);
        directedGraph.insertEdge(7, 8, 39);
        directedGraph.insertEdge(8, 6, 20);
    }

    /**
     * A connected simple graph
     */
    private void setupScene10(){
        setupScene1();
        for (int i = 2; i <= 5; i++){
            directedGraph.insertVertex(i);
        }
        directedGraph.insertEdge(0, 1,10);
        directedGraph.insertEdge(1, 2,20);
        directedGraph.insertEdge(2, 3,30);
        directedGraph.insertEdge(3, 4,40);
        directedGraph.insertEdge(4, 0,50);
    }

    /**
     * A second connected simple graph
     */
    private void setupScene11(){
        setupScene1();
        for(int i = 2; i <= 6; i++ )
            directedGraph.insertVertex(i);
        directedGraph.insertEdge(0, 1, 10);
        directedGraph.insertEdge(0, 2, 23);
        directedGraph.insertEdge(0, 3, 15);
        directedGraph.insertEdge(2, 5, 40);
        directedGraph.insertEdge(4, 5, 28);
        directedGraph.insertEdge(3, 4, 12);
    }

    /**
     * Simple connected graph for Dijkstra.
     */
    private void setupScene12(){
        directedGraph2 = new AdjacencyListGraph<>(true, true);
        directedGraph2.insertVertex("A");
        directedGraph2.insertVertex("B");
        directedGraph2.insertVertex("C");
        directedGraph2.insertVertex("D");
        directedGraph2.insertVertex("E");
        directedGraph2.insertVertex("F");
        directedGraph2.insertEdge(0, 1, 3);
        directedGraph2.insertEdge(0, 2, 2);
        directedGraph2.insertEdge(1, 3, 1);
        directedGraph2.insertEdge(2, 1, 1);
        directedGraph2.insertEdge(2, 4, 5);
        directedGraph2.insertEdge(4, 1, 1);
        directedGraph2.insertEdge(4, 5, 3);
        directedGraph2.insertEdge(3, 4, 2);
        directedGraph2.insertEdge(3, 5, 2);
    }

    /**
     * A multigraph for Dijkstra.
     */
    private void setupScene13(){
        directedGraph2 = new AdjacencyListGraph<>(true, true);
        directedGraph2.insertVertex("A");
        directedGraph2.insertVertex("B");
        directedGraph2.insertVertex("C");
        directedGraph2.insertVertex("D");
        directedGraph2.insertVertex("E");
        directedGraph2.insertVertex("F");
        directedGraph2.insertEdge(0, 1, 3);
        directedGraph2.insertEdge(0, 2, 1);
        directedGraph2.insertEdge(0, 2, 2);
        directedGraph2.insertEdge(0, 3, 2);
        directedGraph2.insertEdge(0, 3, 2);
        directedGraph2.insertEdge(1, 3, 4);
        directedGraph2.insertEdge(1, 4, 1);
        directedGraph2.insertEdge(4, 1, 1);
        directedGraph2.insertEdge(3, 4, 3);
        directedGraph2.insertEdge(3, 4, 2);
        directedGraph2.insertEdge(3, 4, 1);
        directedGraph2.insertEdge(3, 5, 7);
        directedGraph2.insertEdge(4, 5, 1);
    }

    @Test
    void constructorMethodTest(){
        setupScene1();

        assertTrue(directedGraph.isDirected()); // Tests if the graph is directed
        assertTrue(directedGraph.isWeighted()); // Tests if the graph is weighted
        assertEquals(1, directedGraph.getNumberOfVertices()); // Tests the number of vertices in the graph
        assertEquals(1, directedGraph.getVerticesL().get(0).getValue()); // Tests if the first vertex is correct
        assertEquals(0, directedGraph.getNumberOfEdges()); // Tests the number of edges in the graph
    }

    @Test
    void insertVertexTest(){
        // Case 1: Insert a vertex with no connexion to any of the other vertex in the graph.
        setupScene1();
        directedGraph.insertVertex(2);

        assertEquals(2, directedGraph.getNumberOfVertices());
        assertEquals(1, directedGraph.getVerticesL().get(0).getValue());
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree()); // Adjacent vertices to (1).
        assertEquals(2, directedGraph.getVerticesL().get(1).getValue());
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree()); // Adjacent vertices to (2).
    }

    @Test
    void insertEdgeTest(){
        ArrayList<EdgeL<Integer, Integer>> edges;
        // Case 1: Connect a vertex to another which were not connected previously..
        setupScene1();
        directedGraph.insertVertex(2);
        directedGraph.insertEdge(0, 1, 5);

        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());

        edges = directedGraph.getEdges(0, 1);

        assertEquals(1, edges.size());
        assertEquals(1, edges.get(0).getOriginVertex().getValue()); // The vertex which is adjacent to (2).
        assertEquals(2, edges.get(0).getDestinationVertex().getValue()); // The vertex which is adjacent to (1).
        assertEquals(5, edges.get(0).getWeight()); // The weight of the edge.

        edges = directedGraph.getEdges(1, 0);

        assertEquals(0, edges.size());

        setupScene1();
        directedGraph.insertVertex(2);
        directedGraph.insertEdge(1, 0, 5);

        edges = directedGraph.getEdges(1, 0);
        assertEquals(1, edges.size());
        assertEquals(2, edges.get(0).getOriginVertex().getValue()); // The vertex which is adjacent to (2).
        assertEquals(1, edges.get(0).getDestinationVertex().getValue()); // The vertex which is adjacent to (1).
        assertEquals(5, edges.get(0).getWeight()); // The weight of the edge.

        // Case 2: Add an edge from one vertex to the same one.
        setupScene1();
        directedGraph.insertEdge(0,0,10);

        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree());

        edges = directedGraph.getEdges(0, 0);

        assertEquals(1, edges.size());
        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getOriginVertex().getValue()); // The edge points to the same vertex making a cycle.
        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getDestinationVertex().getValue()); // The edge points to the same vertex making a cycle.
        assertEquals(10, edges.get(0).getWeight()); // The weight of the edge.

        // Case 3: Add an edge with the same direction than the one already inserted between two vertices.
        setupScene2();
        directedGraph.insertEdge(1, 0, 20);

        edges = directedGraph.getEdges(1, 0);
        Collections.reverse(edges);

        assertEquals(2, edges.size());

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getDestinationVertex().getValue()); //The edge from (2) to (1)
        assertEquals(directedGraph.getVerticesL().get(1).getValue(), edges.get(0).getOriginVertex().getValue());
        assertEquals(10, edges.get(0).getWeight()); // The weight of the edge.

        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(1).getDestinationVertex().getValue()); //The second edge from (2) to (1)
        assertEquals(directedGraph.getVerticesL().get(1).getValue(), edges.get(1).getOriginVertex().getValue());
        assertEquals(20, edges.get(1).getWeight()); // The weight of the edge.

        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());

        // Case 4: Add an edge with an opposite direction than the one already inserted between two vertices.
        setupScene2();
        directedGraph.insertEdge(0, 1, 20);

        edges = directedGraph.getEdges(1, 0);
        Collections.reverse(edges);

        assertEquals(1, edges.size());
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getDestinationVertex().getValue()); //The edge from (2) to (1)
        assertEquals(directedGraph.getVerticesL().get(1).getValue(), edges.get(0).getOriginVertex().getValue());
        assertEquals(10, edges.get(0).getWeight()); // The weight of the edge.

        edges = directedGraph.getEdges(0, 1);
        Collections.reverse(edges);

        assertEquals(1, edges.size());
        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree()); // The degree of the vertex (1).
        assertEquals(directedGraph.getVerticesL().get(1).getValue(), edges.get(0).getDestinationVertex().getValue()); //The edge from (1) to (2)
        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getOriginVertex().getValue()); //The edge from (1) to (2)
        assertEquals(20, edges.get(0).getWeight()); // The weight of the edge.
    }

    @Test
    void deleteVertexTest(){
        setupScene8();

        assertEquals(3, directedGraph.getNumberOfVertices()); // Number of vertices in the graph

        // Case 1: Delete a vertex which does not exist.
        try{
            directedGraph.deleteVertex(3);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Delete a vertex with no connection to any of the others.
        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices()); // Number of vertices in the graph

        directedGraph.deleteVertex(2);

        assertTrue(directedGraph.getVerticesL().size() == 2); // Number of vertices in the graph

        // Case 3: Delete a vertex with an edge pointing to another vertex.
        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices()); // Number of vertices in the graph

        directedGraph.deleteVertex(1);

        assertEquals(2, directedGraph.getNumberOfVertices()); // Number of vertices in the graph


        // Case 4: Delete a vertex with an incident edge to it.
        setupScene2();

        assertEquals(3, directedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree()); // The degree of vertex (2) which is incident to the vertex (1)

        directedGraph.deleteVertex(0);

        assertEquals(2, directedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree()); // The degree of vertex (2) which is incident to the vertex (1)

        // Case 5: Delete a vertex with multiple connections to any others.
        setupScene8();

        assertEquals(3, directedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals(5, directedGraph.getNumberOfEdges()); // Number of edges in the graph
        assertEquals( 1, directedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 2, directedGraph.getVerticesL().get(1).getValue()); // Vertex (2)
        assertEquals( 3, directedGraph.getVerticesL().get(2).getValue()); // Vertex (3)
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree()); // The degree of the vertex (3)

        directedGraph.deleteVertex(1);

        assertEquals(0, directedGraph.getNumberOfEdges()); // Number of edges in the graph

        assertEquals(2, directedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals( 1, directedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 3, directedGraph.getVerticesL().get(1).getValue()); // Vertex (3)
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (3)
    }

    @Test
    void deleteEdgeTest() throws ThereIsNotAnEdgeException {
        ArrayList<EdgeL<Integer, Integer>> edges;
        setupScene8();

        // Case 1: Delete an edge which does not exist.
        try{
            directedGraph.deleteEdge(1, 0, 55);
            fail();
        }
        catch (IndexOutOfBoundsException | ThereIsNotAnEdgeException e){
            assertTrue(true);
        }

        // Case 2: Delete an edge between two vertices in a simple graph.
        setupScene2();

        directedGraph.deleteEdge(1, 0, 10);
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, directedGraph.getNumberOfEdges());

        // Case 3: Delete an edge between two vertices in a multigraph.
        setupScene3();

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());

        directedGraph.deleteEdge(1, 0, 10);

        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());

        assertEquals(1, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 0);
        assertEquals(1, edges.size());

        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());


        // Case 4: Delete an edge pointing to the same vertex.
        setupScene4();

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(2, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 1);
        assertEquals(1, edges.size());

        directedGraph.deleteEdge(1, 1, 20);

        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(1, directedGraph.getNumberOfEdges());

        edges = directedGraph.getEdges(1, 0);

        assertEquals(directedGraph.getVerticesL().get(0).getValue(), edges.get(0).getDestinationVertex().getValue());
        assertEquals(directedGraph.getVerticesL().get(1).getValue(), edges.get(0).getOriginVertex().getValue());
        assertEquals(10, edges.get(0).getWeight());
    }

    @Test
    void deleteAllEdge() throws ThereIsNotAnEdgeException {
        // Case 1: Two vertices without any edges between them.
        setupScene3();
        try{
            directedGraph.deleteAllEdge(0, 2); // Vertices (1) and (3) are not connected.
            directedGraph.deleteAllEdge(2, 0);
            fail();
        }
        catch (ThereIsNotAnEdgeException e){
            assertTrue(true);
        }

        // Case 2: Two vertices connected by one edge without any other edges to other vertices.
        setupScene2();
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(1, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, directedGraph.getNumberOfEdges());

        // Case 3: Two vertices connected by one edge with other edges to other vertices.
        setupScene5();
        assertEquals(3, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(2, 1);
        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(2, directedGraph.getNumberOfEdges());

        // Case 4: Two vertices connected by many edges without any other edges to other vertices.
        setupScene3();
        assertEquals(2, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, directedGraph.getNumberOfEdges());

        // Case 5: Two vertices connected by many edges with other edges to other vertices.
        setupScene8();
        assertEquals(5, directedGraph.getNumberOfEdges());
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(4, directedGraph.getVerticesL().get(1).getDegree());
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(3, directedGraph.getNumberOfEdges());

        // Case 6: One edge from one vertex to the same one without any connection to other vertices.
        setupScene6();
        assertEquals(2, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(1, directedGraph.getNumberOfEdges());

        // Case 7: One edge from one vertex to the same one with other edges to other vertices.
        setupScene6();
        directedGraph.insertEdge(2, 1, 50);
        assertEquals(2, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(3, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(2, directedGraph.getNumberOfEdges());

        // Case 8: Many edges from one vertex to the same one without any connection to other vertices.
        setupScene7();
        assertEquals(3, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(1, directedGraph.getNumberOfEdges());

        // Case 9: Many edges from one vertex to the same one with other edges to other vertices.
        setupScene7();
        directedGraph.insertEdge(2, 1, 50);
        assertEquals(3, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(4, directedGraph.getNumberOfEdges());
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree());
        assertEquals(2, directedGraph.getNumberOfEdges());
    }

    private void printBFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = directedGraph.getVerticesL().get(j).getPredecessor();
            System.out.println("Valor: " + directedGraph.getVerticesL().get(j).getValue());
            System.out.println("Color: " + directedGraph.getVerticesL().get(j).getColor());
            System.out.println("Distancia desde el vértice " + directedGraph.getVerticesL().get(tree.get(0)).getValue() + ": " + directedGraph.getVerticesL().get(j).getDistance());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : directedGraph.getVerticesL().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    private void printDFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = directedGraph.getVerticesL().get(j).getPredecessor();
            System.out.println("Valor: " + directedGraph.getVerticesL().get(j).getValue());
            System.out.println("Color: " + directedGraph.getVerticesL().get(j).getColor());
            System.out.println("Tiempo inicial: " + directedGraph.getVerticesL().get(j).getInitialTime());
            System.out.println("Tiempo final: " + directedGraph.getVerticesL().get(j).getFinalTime());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : directedGraph.getVerticesL().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    @Test
    void BFSWithStartPositionTest() throws UnderflowException {
        ArrayList<Integer> tree = null;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = directedGraph.BFS(0);
        assertEquals(1, tree.size());
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(tree.get(0)));

        // Case 2: A disconnected graph of n vertices.
        setupScene9();
        tree = directedGraph.BFS(0);
        assertEquals(6, tree.size());
        assertEquals(1, directedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(0, directedGraph.getVerticesL().get(tree.get(0)).getDistance());
        assertEquals(-1, directedGraph.getVerticesL().get(tree.get(0)).getPredecessor());
        assertEquals(2, directedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(1, directedGraph.getVerticesL().get(tree.get(1)).getDistance());
        assertEquals(0, directedGraph.getVerticesL().get(tree.get(1)).getPredecessor());
        assertEquals(3, directedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(1, directedGraph.getVerticesL().get(tree.get(2)).getDistance());
        assertEquals(0, directedGraph.getVerticesL().get(tree.get(2)).getPredecessor());
        assertEquals(4, directedGraph.getVerticesL().get(tree.get(3)).getValue());
        assertEquals(1, directedGraph.getVerticesL().get(tree.get(3)).getDistance());
        assertEquals(0, directedGraph.getVerticesL().get(tree.get(3)).getPredecessor());
        assertEquals(6, directedGraph.getVerticesL().get(tree.get(4)).getValue());
        assertEquals(2, directedGraph.getVerticesL().get(tree.get(4)).getDistance());
        assertEquals(2, directedGraph.getVerticesL().get(tree.get(4)).getPredecessor());
        assertEquals(5, directedGraph.getVerticesL().get(tree.get(5)).getValue());
        assertEquals(2, directedGraph.getVerticesL().get(tree.get(5)).getDistance());
        assertEquals(3, directedGraph.getVerticesL().get(tree.get(5)).getPredecessor());

        tree = directedGraph.BFS(6);
        assertEquals(3, tree.size());
        assertEquals(7, directedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(0, directedGraph.getVerticesL().get(tree.get(0)).getDistance());
        assertEquals(-1, directedGraph.getVerticesL().get(tree.get(0)).getPredecessor());
        assertEquals(8, directedGraph.getVerticesL().get(tree.get(1)).getValue());
        assertEquals(1, directedGraph.getVerticesL().get(tree.get(1)).getDistance());
        assertEquals(6, directedGraph.getVerticesL().get(tree.get(1)).getPredecessor());
        assertEquals(9, directedGraph.getVerticesL().get(tree.get(2)).getValue());
        assertEquals(2, directedGraph.getVerticesL().get(tree.get(2)).getDistance());
        assertEquals(7, directedGraph.getVerticesL().get(tree.get(2)).getPredecessor());

        // Case 3: A connected graph with n vertices.
        setupScene10();
        tree = directedGraph.BFS(0);
        assertEquals(5, tree.size());

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("\n------------------------------ BFS with start position ------------------------------");
        System.out.println("--------- Starting from (3) ---------");
        tree = directedGraph.BFS(2);
        assertEquals(3, tree.size());
        printBFSTree(tree);
        System.out.println("--------- Starting from (2) ---------");
        tree = directedGraph.BFS(1);
        assertEquals(3, tree.size());
        printBFSTree(tree);
    }

    @Test
    void BFSWithoutParametersTest() throws UnderflowException {
        ArrayList<ArrayList<Integer>> forest = null;
        // Case 1: A graph with one vertex.
        setupScene1();
        forest = directedGraph.BFS();
        assertEquals(1, forest.size());
        assertEquals(1, forest.get(0).size());
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(forest.get(0).get(0)));

        // Case 2: A disconnected graph of n vertices.
        setupScene9();
        forest = directedGraph.BFS();
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
        forest = directedGraph.BFS();
        assertEquals(1, forest.size());
        assertEquals(5, forest.get(0).size());
        System.out.println("===================CASE 3: CONNECTED GRAPH===================");
        printBFSTree(forest.get(0));

        // Case 4: A graph with a cycle.
        setupScene8();
        forest = directedGraph.BFS();
        assertEquals(2, forest.size());
        assertEquals(1, forest.get(0).size());
        assertEquals(2, forest.get(1).size());
        assertEquals(1, directedGraph.getVerticesL().get(forest.get(0).get(0)).getValue());
        assertEquals(2, directedGraph.getVerticesL().get(forest.get(1).get(0)).getValue());
        assertEquals(3, directedGraph.getVerticesL().get(forest.get(1).get(1)).getValue());
    }

    @Test
    void DFSWithInitialVertexTest(){
        ArrayList<Integer> tree;
        // Case 1: A graph with one vertex.
        setupScene1();
        tree = directedGraph.DFS(0);
        assertEquals(1, tree.size());
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(tree.get(0)));

        // Case 2: A connected graph of n vertices.
        setupScene11();
        tree = directedGraph.DFS(2);
        assertEquals(2, tree.size());
        assertEquals(3, directedGraph.getVerticesL().get(tree.get(0)).getValue());
        assertEquals(6, directedGraph.getVerticesL().get(tree.get(1)).getValue());

        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        tree = directedGraph.DFS(1);
        assertEquals(6, tree.size());
        System.out.println("\n------------------------------ DFS with start position ------------------------------");
        System.out.println("---------- Case 3: A disconnected graph of n vertices ---------");
        System.out.println("--------First tree-------");
        printDFSTree(tree);

        // Case 4: A graph with a cycle.
        setupScene8();
        System.out.println("---------- Case 4: A graph with a cycle. ---------");
        System.out.println("--------- Starting from (3) ---------");
        tree = directedGraph.DFS(2);
        assertEquals(3, tree.size());
        printDFSTree(tree);
        System.out.println("--------- Starting from (2) ---------");
        tree = directedGraph.DFS(1);
        assertEquals(3, tree.size());
        printDFSTree(tree);
    }

    @Test
    void DFSWithoutStartPositionTest(){
        ArrayList<ArrayList<Integer>> forest;
        // Case 1: A graph with one vertex.
        setupScene1();
        forest = directedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(1, forest.get(0).size());
        assertEquals(1, directedGraph.getVerticesL().get(forest.get(0).get(0)).getValue());

        // Case 2: A connected graph of n vertices.
        setupScene11();
        forest = directedGraph.DFS();
        assertEquals(1, forest.size());
        assertEquals(6, forest.get(0).size());
        System.out.println("\n------------------------------ DFS without start position ------------------------------");
        System.out.println("---------- Case 2: A connected graph of n vertices ---------");
        printDFSTree(forest.get(0));

        // Case 3: A disconnected graph of n vertices.
        setupScene9();
        forest = directedGraph.DFS();
        assertEquals(2, forest.size());
        assertEquals(6, forest.get(0).size());
        assertEquals(3, forest.get(1).size());
        System.out.println("===================CASE 3: DISCONNECTED GRAPH===================");
        System.out.println("--------First tree-------");
        printDFSTree(forest.get(0));
        System.out.println("\n--------Second tree-------");
        printDFSTree(forest.get(1));
    }

    @Test
    void DijkstraTest(){
        Object[] a;
        double[] dist;
        int[] pred;

        // Case 1: A graph with one vertex
        setupScene1();
        a = directedGraph.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];
        assertEquals(1, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(1, pred.length);
        assertEquals(-1, pred[0]);

        // Case 2: A simple connected graph
        setupScene12();
        a = directedGraph2.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];

        assertEquals(6, dist.length);
        assertEquals(0, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(2, dist[2]);
        assertEquals(4, dist[3]);
        assertEquals(6, dist[4]);
        assertEquals(6, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(0, pred[1]);
        assertEquals(0, pred[2]);
        assertEquals(1, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(3, pred[5]);

        a = directedGraph2.Dijsktra(3);
        dist = (double[])a[0];
        pred = (int[])a[1];

        assertEquals(6, dist.length);
        assertEquals(Integer.MAX_VALUE, dist[0]);
        assertEquals(3, dist[1]);
        assertEquals(Integer.MAX_VALUE, dist[2]);
        assertEquals(0, dist[3]);
        assertEquals(2, dist[4]);
        assertEquals(2, dist[5]);

        assertEquals(6, pred.length);
        assertEquals(-1, pred[0]);
        assertEquals(4, pred[1]);
        assertEquals(-1, pred[2]);
        assertEquals(-1, pred[3]);
        assertEquals(3, pred[4]);
        assertEquals(3, pred[5]);

        // Case 3: A connected multigraph
        setupScene13();
        a = directedGraph2.Dijsktra(0);
        dist = (double[])a[0];
        pred = (int[])a[1];

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

        // Case 4: Begin the path from a vertex which does not exist.
        setupScene1();
        try{
            a = directedGraph.Dijsktra(90);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }
    }
}
