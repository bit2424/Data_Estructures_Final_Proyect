package Estructures.test;

import Estructures.graphs.AdjacencyListGraph;
import Estructures.graphs.Edge;
import Estructures.graphs.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest {

    private AdjacencyListGraph<Integer> graph;

    public void setupScene1(){

        graph = new AdjacencyListGraph(false, true, new Vertex<Integer>(5));
    }

    @Test
    void constructorTest(){
        setupScene1();
        assertFalse(graph.isDirected());
        assertTrue(graph.isWeighted());
        assertTrue(graph.getVertices().size() == 1);
        assertTrue(graph.getVertices().get(0).getValue() == 5);
    }

    @Test
    public void insertTest(){
        setupScene1();

        //Inserts one vertex
        Edge<Integer> edge = new Edge<>(graph.getVertices().get(0), 25);
        graph.insertVertex(10, edge);

        assertTrue(graph.getVertices().size() == 2);

        assertTrue(graph.getVertices().get(1).getValue() == 10);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 25);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 10);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 25);

        //Inserts a vertex with multiple adjacency vertices
        Edge<Integer> edge1 = new Edge<>(graph.getVertices().get(0), 40);
        Edge<Integer> edge2 = new Edge<>(graph.getVertices().get(1), 100);
        ArrayList<Edge<Integer>> adj = new ArrayList<>();
        adj.add(edge1);
        adj.add(edge2);
        graph.insertVertex(15, adj);

        assertTrue(graph.getVertices().size() == 3);
        assertTrue(graph.getVertices().get(2).getValue() == 15);

        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 40);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(1).getVertex().getValue() == 10);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(1).getWeight() == 100);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 10);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 25);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 15);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 40);

        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 25);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 15);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getWeight() == 100);

        //Inserts a vertex with no connections to any another
        graph.insertVertex(400);
        assertTrue(graph.getVertices().size() == 4);
        assertTrue(graph.getVertices().get(3).getValue() == 400);

        assertTrue(graph.getVertices().get(3).getAdjacencyList().size() == 0);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 2);
    }

}
