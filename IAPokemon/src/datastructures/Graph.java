package datastructures;

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(Integer vertex) {
        adjVertices.put(vertex, new LinkedList<>());
    }

    public void addEdge(Integer vertex1, Integer vertex2) {
        adjVertices.get(vertex1).add(vertex2);
        adjVertices.get(vertex2).add(vertex1);
    }

    public List<Integer> getNeighbors(Integer vertex) {
        return adjVertices.get(vertex);
    }

    public Set<Integer> getAllVertices() {
        return adjVertices.keySet();
    }
}

