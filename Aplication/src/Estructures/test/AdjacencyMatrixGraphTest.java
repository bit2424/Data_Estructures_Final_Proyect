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
        assertEquals(0, noDirectedGraph.getMatrixAdyacency()[1][3].getValue());


        noDirectedGraph.insertVertex("6");
        noDirectedGraph.insertEdge(4,4,6);
        noDirectedGraph.deleteEdge(4,4,6);
        assertEquals(0, noDirectedGraph.getMatrixAdyacency()[4][4]);

    }

    @Test
    public void deleteAllEdge(){


    }

    @Test
    public void BFSTest(){


    }

    @Test
    public void DFSTest(){


    }

    @Test
    public void primTest(){


    }
    @Test
    public void dikstraTest(){

    }

    @Test
    public void floydTest(){


    }



}