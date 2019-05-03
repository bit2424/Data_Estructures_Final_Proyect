package Estructures.test;

import Estructures.Graphs.AdjacencyListGraph;
import Estructures.Graphs.Edge;
import Estructures.Graphs.Vertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest {

    private AdjacencyListGraph<Integer> graph;

    public void setupScene1(){
        graph = new AdjacencyListGraph(false, true, new Vertex<>(5));
    }

    public void setupScene2(){
        setupScene1();
        graph.insertVertex(8, new Edge<>(graph.getVertices().get(0), 45));
        graph.insertVertex(4, new Edge<>(graph.getVertices().get(0), 27));
        graph.insertVertex(2, new Edge<>(graph.getVertices().get(0), 32));

        graph.addEdge(graph.getVertices().get(3), graph.getVertices().get(1), 18);

        ArrayList<Edge<Integer>> adj = new ArrayList<>();
        adj.add(new Edge<>(graph.getVertices().get(0), 22));
        adj.add(new Edge<>(graph.getVertices().get(3), 40));
        graph.insertVertex(1, adj);
    }

    public void setupScene3(){
        graph = new AdjacencyListGraph<>(false, true, new Vertex<>(1));
        graph.insertVertex(2, new Edge<>(graph.getVertices().get(0), 10));
        graph.insertVertex(3, new Edge<>(graph.getVertices().get(0), 23));
        graph.insertVertex(4, new Edge<>(graph.getVertices().get(0), 15));
        graph.insertVertex(5, new Edge<>(graph.getVertices().get(3), 12));
        graph.insertVertex(6, new Edge<>(graph.getVertices().get(2), 40));
        graph.addEdge(graph.getVertices().get(5), graph.getVertices().get(4), 28);
        graph.insertVertex(7);
        graph.insertVertex(8, new Edge<>(graph.getVertices().get(6), 30));
        graph.insertVertex(9, new Edge<>(graph.getVertices().get(7), 39));
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

    @Test
    void addEdgeTest(){
        setupScene1();

        graph.insertVertex(200);
        graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(0), 10);

        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 10);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 200);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 10);

        graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(1), 3);

        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getWeight() == 3);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 200);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 3);

        Edge<Integer> edge1 = new Edge<>(graph.getVertices().get(1), 45);
        graph.insertVertex(30, edge1);
        graph.addEdge(graph.getVertices().get(2), graph.getVertices().get(0), 89);

        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 200);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getWeight() == 45);

        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(1).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(1).getWeight() == 89);

        setupScene1();

        graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(0), 1);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 5);

        graph.insertVertex(6);
        graph.addEdge(graph.getVertices().get(0), graph.getVertices().get(1), 2);
        graph.addEdge(graph.getVertices().get(1), graph.getVertices().get(0), 3);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 3);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 6);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 2);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(2).getVertex().getValue() == 6);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(2).getWeight() == 3);

        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getWeight() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 5);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getWeight() == 3);

    }

    @Test
    void deleteEdgeTest(){
        setupScene2();

        graph.deleteEdge(graph.getVertices().get(0), 2);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 3);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 8);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 45);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 4);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 27);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(2).getVertex().getValue() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(2).getWeight() == 22);

        assertTrue(graph.getVertices().get(3).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().get(0).getVertex().getValue() == 8);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().get(0).getWeight() == 18);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().get(1).getVertex().getValue() == 1);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().get(1).getWeight() == 40);

        graph.deleteEdge(graph.getVertices().get(2), 0);

        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 0);

        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 8);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getWeight() == 45);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getVertex().getValue() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(1).getWeight() == 22);
    }

    @Test
    void deleteVertexTest(){
        setupScene2();

        graph.deleteVertex(0);

        assertTrue(graph.getVertices().size() == 4);

        assertTrue(graph.getVertices().get(0).getValue() == 8);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 2);

        assertTrue(graph.getVertices().get(1).getValue() == 4);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 0);

        assertTrue(graph.getVertices().get(2).getValue() == 2);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 8);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(1).getVertex().getValue() == 1);

        assertTrue(graph.getVertices().get(3).getValue() == 1);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(3).getAdjacencyList().get(0).getVertex().getValue() == 2);

        graph.deleteVertex(1);

        assertTrue(graph.getVertices().size() == 3);

        assertTrue(graph.getVertices().get(0).getValue() == 8);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(0).getAdjacencyList().get(0).getVertex().getValue() == 2);

        assertTrue(graph.getVertices().get(1).getValue() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().size() == 2);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(0).getVertex().getValue() == 8);
        assertTrue(graph.getVertices().get(1).getAdjacencyList().get(1).getVertex().getValue() == 1);

        assertTrue(graph.getVertices().get(2).getValue() == 1);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().size() == 1);
        assertTrue(graph.getVertices().get(2).getAdjacencyList().get(0).getVertex().getValue() == 2);
    }

    @Test
    void BFSTest() {
        setupScene3();

        ArrayList<Vertex<Integer>> bfsTree;

        //Travels from a vertex which does not have a path to the target vertex.
        bfsTree = graph.BFS(0, 8);

        assertNull(bfsTree);

        //Travels from one vertex to the same one.
        bfsTree = graph.BFS(0, 0);

        assertNotNull(bfsTree);
        assertTrue(bfsTree.size() == 1);
        assertTrue(bfsTree.get(0).getValue() == 1);

        //Travels from one vertex to an adjacent vertex of it.
        bfsTree = graph.BFS(0, 1);

        assertNotNull(bfsTree);
        assertTrue(bfsTree.size() == 2);
        assertTrue(bfsTree.get(0).getValue() == 1);
        assertTrue(bfsTree.get(1).getValue() == 2);

        //Travels from one vertex to another one.
        bfsTree = graph.BFS(1, 5);

        assertNotNull(bfsTree);
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

        //DFS with a one vertex graph.
        setupScene1();

        forest = graph.DFS();

        assertTrue(forest.size() == 1);//One DFS tree
        assertTrue(forest.get(0).size() == 1);
        assertTrue(forest.get(0).get(0).getValue() == 5);

        //DFS with multiple vertex graph which is a convex graph
        setupScene2();

        forest = graph.DFS();

        assertTrue(forest.size() == 1); //One DFS tree
        assertTrue(forest.get(0).size() == 5);
        assertTrue(forest.get(0).get(0).getValue() == 5);

        //DFS with multiple vertex graph which is not a convex graph.
        setupScene3();

        forest = graph.DFS();
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
