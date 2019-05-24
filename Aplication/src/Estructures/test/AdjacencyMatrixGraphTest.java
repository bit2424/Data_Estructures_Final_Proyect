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
        setupScene1();


        assertEquals(3,noDirectedGraph.getElementsReference().get(3));
    }

    @Test
    public void insertEdgeTest(){

        setupScene2();

        noDirectedGraph.insertEdge(0,3,4);
        noDirectedGraph.insertEdge(0,0,2);
        assertEquals(4,noDirectedGraph.getElementsReference().get(4));
        noDirectedGraph.deleteEdge(0,3,4);
        noDirectedGraph.deleteEdge(0,0,2);


    }

    @Test
    public void deleteVertexTest(){
        noDirectedGraph.deleteVertex(2);
        assertEquals(2,noDirectedGraph.getElementsReference().get(2));
    }

    @Test
    public void deleteEdgeTest(){

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



}