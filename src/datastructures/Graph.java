package datastructures;

import java.util.*;

import domain.Nodo;

public class Graph {
    private Map<Nodo, List<Nodo>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(Nodo vertex) {
        adjVertices.put(vertex, new LinkedList<>());
    }

    public void addEdge(Nodo vertex1, Nodo vertex2) {
        adjVertices.get(vertex1).add(vertex2);
        adjVertices.get(vertex2).add(vertex1);
    }

    public List<Nodo> getNeighbors(Nodo vertex) {
        return adjVertices.get(vertex);
    }

    public Set<Nodo> getAllVertices() {
        return adjVertices.keySet();
    }
}

