package Estructures.test;

import Estructures.Graphs.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    private AdjacencyMatrixGraph<Integer, Integer> noDirectedGraph;

    //Build scenes
    private void setupScene1(){

        noDirectedGraph = new AdjacencyMatrixGraph<>( true, false );

    }

    private void setupScene2(){
        setupScene1();
        EdgeM<Integer> edge = new EdgeM<Integer>(3);
        noDirectedGraph.insertVertex(2);
        noDirectedGraph.insertVertex(1);
        noDirectedGraph.insertVertex(3);
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
        noDirectedGraph.insertVertex(2);
        boolean contains = false;
        for (VertexM<Integer> e : noDirectedGraph.getVertexM()) {
            contains = e.getValue() == 2;
            if(contains) break;
        }
        assertTrue(contains);
    }

    @Test
    public void insertEdgeTest(){

        setupScene2();

        noDirectedGraph.insertEdge(1,2,4);

    }

    @Test
    public void deleteVertexTest(){


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