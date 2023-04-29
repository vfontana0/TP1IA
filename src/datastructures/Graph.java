package datastructures;

import java.util.*;

import domain.Nodo;

public class Graph {
    private Map<Nodo, List<Nodo>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }
    
    public Graph(Map<Nodo, List<Nodo>> adjVertices) {
        this.adjVertices = adjVertices;
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
    
    
    
    public Nodo getVertex(int numero) {
        for (Nodo vertex : adjVertices.keySet()) {
            if (vertex.getNumero() == numero) {
                return vertex;
            }
        }
        return null;
    }
    
    public Graph clone() {
        Map<Nodo, List<Nodo>> newAdjVertices = new HashMap<>();
        
        for (Map.Entry<Nodo, List<Nodo>> entry : adjVertices.entrySet()) {
            Nodo vertice = entry.getKey(); //la clave es el nodo
            List<Nodo> vecinos = entry.getValue(); //vecinos del nodo
            
            // clonar vertice
            Nodo newVertex = vertice.clone();
            
            // Crear copias de los vecinos
            List<Nodo> nuevosVecinos = new ArrayList<>();
            for (Nodo vecino : vecinos) {
                Nodo nuevoVecino = vecino.clone();
                nuevosVecinos.add(nuevoVecino);
            }
            
            newAdjVertices.put(newVertex, nuevosVecinos);
        }
        
        return new Graph(newAdjVertices);
    }
}
    

