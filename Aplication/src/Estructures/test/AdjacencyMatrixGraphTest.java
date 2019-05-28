package Estructures.test;

import Estructures.Graphs.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    private AdjacencyMatrixGraph<String, Integer> noDirectedGraph;

    //Build scenes
    private void setupScene1(){

        noDirectedGraph = new AdjacencyMatrixGraph<String, Integer>( true, false );

    }

    private void setupScene2(){
        setupScene1();
        noDirectedGraph.insertVertex("0");
        noDirectedGraph.insertVertex("1");
        noDirectedGraph.insertVertex("2");
        noDirectedGraph.insertVertex("3");
        noDirectedGraph.insertEdge(0,1,3);
    }

    private void setupScene3(){
        setupScene2();
        noDirectedGraph.insertEdge(1,2,4);
        noDirectedGraph.insertEdge(1,3,5);
        noDirectedGraph.insertEdge(3,2,6);
    }

    @Test
    public void insertVertexTest() {
        setupScene2();
        noDirectedGraph.insertVertex("4");
        assertEquals(5,noDirectedGraph.getnVertex());
        assertEquals("4",noDirectedGraph.getElementsReference().get(4).getValue());
    }

    @Test
    public void insertEdgeTest(){

        setupScene2();
        // Case 1: Connect a vertex to another which were not connected previously.
        noDirectedGraph.insertEdge(0,3,4);

        assertEquals(4, noDirectedGraph.getMatrixAdyacency()[0][3].getValue());

        //Case 2: Add an edge form one vertex to the same one.

        noDirectedGraph.insertEdge(0,0,2);
        assertEquals(2,noDirectedGraph.getMatrixAdyacency()[0][0].getValue());
    }

    @Test
    public void deleteVertexTest(){
        setupScene2();
        assertEquals(4, noDirectedGraph.getnVertex()); // Number of vertices in the graph

        // Case 1: Delete a vertex which does not exist.
        try{
            //La matriz tiene 1001 posiciones por default.

            noDirectedGraph.deleteVertex(1002);
            fail();
        }
        catch (IndexOutOfBoundsException e){
            assertTrue(true);
        }

        //Case 2: Case 2: Delete a vertex with no connection to any of the others.

        setupScene2();

        noDirectedGraph.insertVertex("6");
        noDirectedGraph.insertVertex("7");

        assertEquals(6, noDirectedGraph.getnVertex()); // Number of vertices in the graph

        noDirectedGraph.deleteVertex(5);
        assertEquals(5, noDirectedGraph.getnVertex());
    }

    @Test
    public void deleteEdgeTest() {
        // Case 1: Delete an edge which does not exist.
        try {
            noDirectedGraph.deleteEdge(1, 0, 9);
            fail();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            assertTrue(true);
        }

        // Case 2: Delete an edge between two vertices in a simple graph.
        setupScene3();
        assertEquals(5, noDirectedGraph.getMatrixAdyacency()[1][3].getValue());
        noDirectedGraph.deleteEdge(1, 3, 5);
        assertEquals(null, noDirectedGraph.getMatrixAdyacency()[1][3]);


        noDirectedGraph.insertVertex("6");
        noDirectedGraph.insertEdge(4,4,6);
        noDirectedGraph.deleteEdge(4,4,6);
        assertEquals(null, noDirectedGraph.getMatrixAdyacency()[4][4]);

    }

    @Test
    public void deleteAllEdge(){


    }

    @Test
    public void BFSTest(){
        ArrayList<Integer> tree = null;
        // Case 1: A graph with one vertex.
        setupScene1();
        noDirectedGraph.insertVertex("4");
        tree = noDirectedGraph.BFS(0);
        assertEquals(1, tree.size());
        assertEquals(noDirectedGraph.getElementsReference().get(0), noDirectedGraph.getElementsReference().get(tree.get(0)));
        // Case 2: A disconnected graph of n vertices.
        setupScene3();
        tree = noDirectedGraph.BFS(0);
        assertEquals(4, tree.size());
        assertEquals("0", noDirectedGraph.getElementsReference().get(tree.get(0)).getValue());
        assertEquals("1", noDirectedGraph.getElementsReference().get(tree.get(1)).getValue());
        assertEquals("2", noDirectedGraph.getElementsReference().get(tree.get(2)).getValue());
        assertEquals("3", noDirectedGraph.getElementsReference().get(tree.get(3)).getValue());

        // Case 3: A connected graph with n vertices.
        setupScene3();
        tree = noDirectedGraph.BFS(0);
        assertEquals(4, tree.size());

        // Case 4: A graph with a cycle.
        setupScene3();
        noDirectedGraph.insertEdge(3,3,6);
        System.out.println("\nBFSWithStartPositionTest");
        System.out.println("--------- Starting from (3) ---------");
        tree = noDirectedGraph.BFS(2);
        assertEquals(4, tree.size());
        System.out.println("--------- Starting from (2) ---------");
        tree = noDirectedGraph.BFS(1);
        assertEquals(4, tree.size());
    }
    @Test
    private void printDFSTree(ArrayList<Integer> tree){
        for(int i = 0; i < tree.size(); i++){
            int j = tree.get(i);
            int predecessor = tree.get(j); //position
            noDirectedGraph.getElementsReference().get(tree.get(j)); //User
            System.out.println("Valor: " + noDirectedGraph.getElementsReference().get(j).getValue());
            System.out.println("Tiempo inicial: " + noDirectedGraph.getVerticesL().get(j).getInitialTime());
            System.out.println("Tiempo final: " + noDirectedGraph.getVerticesL().get(j).getFinalTime());
            System.out.println("Predecesor: " + ((predecessor == -1) ? null : noDirectedGraph.getElementsReference().get(predecessor).getValue()));
            System.out.println("\n");
        }
    }

    @Test
    void DFSWithInitialVertexTest(){
        ArrayList<Integer> tree;
        // Case 1: A graph with one vertex.
        setupScene1();
        noDirectedGraph.insertVertex("0");
        tree = noDirectedGraph.DFS(0);
        assertEquals(1, tree.size());
        assertEquals(noDirectedGraph.getElementsReference().get(0), noDirectedGraph.getElementsReference().get(tree.get(0)));

        // Case 2: A connected graph of n vertices.
        setupScene3();
        tree = noDirectedGraph.DFS(2);
        assertEquals(4, tree.size());
        assertEquals("2", noDirectedGraph.getElementsReference().get(tree.get(0)).getValue());
        assertEquals("3", noDirectedGraph.getElementsReference().get(tree.get(1)).getValue());
        assertEquals("1", noDirectedGraph.getElementsReference().get(tree.get(2)).getValue());
        assertEquals("0", noDirectedGraph.getElementsReference().get(tree.get(3)).getValue());


        // Case 3: A disconnected graph of n vertices.
        setupScene3();
        tree = noDirectedGraph.DFS(1);
        assertEquals(4, tree.size());
        System.out.println("\nDFSWithInitialVertexTest");
        System.out.println("---------- Case 3: A disconnected graph of n vertices ---------");
        System.out.println("--------First tree-------");

        // Case 4: A graph with a cycle.
        setupScene3();
        System.out.println("--------- Starting from (3) ---------");
        tree = noDirectedGraph.DFS(2);
        assertEquals(4, tree.size());

        System.out.println("--------- Starting from (2) ---------");
        tree = noDirectedGraph.DFS(1);
        assertEquals(4, tree.size());

    }
    @Test
    public void DFSTest(){

        ArrayList<ArrayList<Integer>> forest;

        // Case 1: A graph with one vertex.
        setupScene1();
        noDirectedGraph.insertVertex("1");
        forest = noDirectedGraph.DFS();
        assertEquals(0, forest.size());
        assertEquals(1, noDirectedGraph.getnVertex());

        // Case 2: A connected graph of n vertices.
        setupScene3();
        noDirectedGraph.insertVertex("5");
        noDirectedGraph.insertEdge(0,4,7);
        forest = noDirectedGraph.DFS();
        assertEquals(0, forest.size());
        System.out.println("\nDFSWithoutStartPositionTest");
        System.out.println("---------- Case 2: A connected graph of n vertices ---------");

    }

    @Test
    public void primTest(){
        ArrayList<Integer> tree;

        setupScene3();
        noDirectedGraph.insertEdge(0,1,9);
        tree = noDirectedGraph.Prim(0);
        assertEquals(4, tree.size());
        assertEquals("0", noDirectedGraph.getElementsReference().get(tree.get(0)).getValue());
        assertEquals("3", noDirectedGraph.getElementsReference().get(tree.get(1)).getValue());
        assertEquals("1", noDirectedGraph.getElementsReference().get(tree.get(2)).getValue());
        assertEquals("2", noDirectedGraph.getElementsReference().get(tree.get(3)).getValue());
        System.out.println( noDirectedGraph.getElementsReference().get(tree.get(0)).getValue());
        System.out.println("------------- Case 2: A connected graph -------------");

    }
    @Test
    public void dikstraTest(){

    }

    @Test
    public void floydTest(){


    }



}