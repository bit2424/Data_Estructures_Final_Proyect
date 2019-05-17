package Estructures.test;

import Estructures.auxiliary_estructures.UnderflowException;
import Estructures.Graphs.AdjacencyListGraph;
import Estructures.Graphs.VertexL;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest_DirectedGraph {

    private AdjacencyListGraph<Integer, Integer> directedGraph;

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

    @Test
    void constructorMethodTest(){
        setupScene1();

        assertTrue(directedGraph.isDirected()); // Tests if the graph is directed
        assertTrue(directedGraph.isWeighted()); // Tests if the graph is weighted
        assertEquals(1, directedGraph.getNumberOfVertices()); // Tests the number of vertices in the graph
        assertEquals(1, directedGraph.getVerticesL().get(0).getValue()); // Tests if the first vertex is correct
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
        // Case 1: Connect a vertex to another which were not connected previously..
        setupScene1();
        directedGraph.insertVertex(2);
        directedGraph.insertEdge(0, 1, 5);

        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(2, directedGraph.getVerticesL().get(0).getAdjacencyList().get(0).getVertex().getValue()); // The vertex which is adjacent to (1).
        assertEquals(5, directedGraph.getVerticesL().get(0).getAdjacencyList().get(0).getWeight()); // The weight of the edge.
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());

        // Case 2: Add an edge form one vertex to the same one.
        setupScene1();
        directedGraph.insertEdge(0,0,10);

        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(10, directedGraph.getVerticesL().get(0).getAdjacencyList().get(0).getWeight()); // The weight of the edge.
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(0)); // The edge points to the same vertex making a cycle.

        // Case 3: Add an edge with the same direction than the one already inserted between two vertices.
        setupScene2();
        directedGraph.insertEdge(1, 0, 20);

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getVertex()); //The edge from (2) to (1)
        assertEquals(10, directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getWeight()); // The weight of the edge.
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(1).getAdjacencyList().get(1).getVertex()); //The second edge from (2) to (1)
        assertEquals(20, directedGraph.getVerticesL().get(1).getAdjacencyList().get(1).getWeight()); // The weight of the edge.

        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());

        // Case 4: Add an edge with an opposite direction than the one already inserted between two vertices.
        setupScene2();
        directedGraph.insertEdge(0, 1, 20);

        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (2).
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getVertex()); //The edge from (2) to (1)
        assertEquals(10, directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getWeight()); // The weight of the edge.

        assertEquals(1, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(directedGraph.getVerticesL().get(1), directedGraph.getVerticesL().get(0).getAdjacencyList().get(0).getVertex()); //The edge from (1) to (2)
        assertEquals(20, directedGraph.getVerticesL().get(0).getAdjacencyList().get(0).getWeight()); // The weight of the edge.
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
        assertEquals( 1, directedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 2, directedGraph.getVerticesL().get(1).getValue()); // Vertex (2)
        assertEquals( 3, directedGraph.getVerticesL().get(2).getValue()); // Vertex (3)
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree()); // The degree of the vertex (3)

        directedGraph.deleteVertex(1);

        assertEquals(2, directedGraph.getNumberOfVertices()); // Number of vertices in the graph
        assertEquals( 1, directedGraph.getVerticesL().get(0).getValue()); // Vertex (1)
        assertEquals( 3, directedGraph.getVerticesL().get(1).getValue()); // Vertex (3)
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree()); // The degree of the vertex (3)
    }

    @Test
    void deleteEdgeTest(){
        setupScene8();

        // Case 1: Delete an edge which does not exist.
        try{
            directedGraph.deleteEdge(1, 0, 55);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        // Case 2: Delete an edge between two vertices in a simple graph.
        setupScene2();

        directedGraph.deleteEdge(1, 0, 10);
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());

        // Case 3: Delete an edge between two vertices in a multigraph.
        setupScene3();

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        directedGraph.deleteEdge(1, 0, 10);
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(40, directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getWeight());
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getVertex());

        // Case 4: Delete an edge pointing to the same vertex.
        setupScene4();

        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        directedGraph.deleteEdge(1, 1, 20);
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(directedGraph.getVerticesL().get(0), directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getVertex());
        assertEquals(10, directedGraph.getVerticesL().get(1).getAdjacencyList().get(0).getWeight());
    }

    @Test
    void deleteAllEdge(){
        // Case 1: Two vertices without any edges between them.
        setupScene3();
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree()); // Vertices (1) and (3) are not connected.
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());
        directedGraph.deleteAllEdge(0, 2);
        directedGraph.deleteAllEdge(2, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree()); // Verifies that no edges were deleted between (1) and (3).
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());

        // Case 2: Two vertices connected by one edge without any other edges to other vertices.
        setupScene2();
        assertEquals(1, directedGraph.getVerticesL().get(1).getDegree());
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());

        // Case 3: Two vertices connected by one edge with other edges to other vertices.
        setupScene5();
        directedGraph.deleteAllEdge(2, 1);
        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());

        // Case 4: Two vertices connected by many edges without any other edges to other vertices.
        setupScene3();
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(0, directedGraph.getVerticesL().get(1).getDegree());

        // Case 5: Two vertices connected by many edges with other edges to other vertices.
        setupScene8();
        directedGraph.deleteAllEdge(1, 0);
        assertEquals(0, directedGraph.getVerticesL().get(0).getDegree());
        assertEquals(2, directedGraph.getVerticesL().get(1).getDegree());

        // Case 6: One edge from one vertex to the same one without any connection to other vertices.
        setupScene6();
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());

        // Case 7: One edge from one vertex to the same one with other edges to other vertices.
        setupScene6();
        directedGraph.insertEdge(2, 1, 50);
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree());

        // Case 8: Many edges from one vertex to the same one without any connection to other vertices.
        setupScene7();
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(0, directedGraph.getVerticesL().get(2).getDegree());

        // Case 9: Many edges from one vertex to the same one with other edges to other vertices.
        setupScene7();
        directedGraph.insertEdge(2, 1, 50);
        directedGraph.deleteAllEdge(2, 2);
        assertEquals(1, directedGraph.getVerticesL().get(2).getDegree());
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
        System.out.println("\nBFSWithStartPositionTest");
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
        System.out.println("\nBFSWithoutParametersTest");
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
        System.out.println("\nDFSWithInitialVertexTest");
        System.out.println("---------- Case 3: A disconnected graph of n vertices ---------");
        System.out.println("--------First tree-------");
        printDFSTree(tree);

        // Case 4: A graph with a cycle.
        setupScene8();
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
        System.out.println("\nDFSWithoutStartPositionTest");
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
}
