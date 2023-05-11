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
        ArrayList<Nodo> nodos = new ArrayList<>();
        Graph grafoNuevo = new Graph(); //creo nuevo grafo
        nodos.add(0, null);
        //clono todos los nodos
        for(int i=1; i<=29; i++) {
        	Nodo clonado = this.getVertex(i).clone();
        	nodos.add(i, clonado);
        	grafoNuevo.addVertex(clonado);
        } 
        this.agregarConexiones(nodos, grafoNuevo); //le agrego las conexiones
        return grafoNuevo;     
    }
    
	private void agregarConexiones(ArrayList<Nodo> nodos, Graph grafo) {
		grafo.addEdge(nodos.get(1), nodos.get(2));
		grafo.addEdge(nodos.get(2), nodos.get(3));
		grafo.addEdge(nodos.get(2), nodos.get(9));
		grafo.addEdge(nodos.get(3), nodos.get(8));
		grafo.addEdge(nodos.get(3), nodos.get(4));
		grafo.addEdge(nodos.get(4), nodos.get(12));
		grafo.addEdge(nodos.get(4), nodos.get(5));
		grafo.addEdge(nodos.get(4), nodos.get(7));
		grafo.addEdge(nodos.get(5), nodos.get(6));
		grafo.addEdge(nodos.get(6), nodos.get(7));
		grafo.addEdge(nodos.get(8), nodos.get(12));
		grafo.addEdge(nodos.get(9), nodos.get(10));
		grafo.addEdge(nodos.get(9), nodos.get(11));
		grafo.addEdge(nodos.get(9), nodos.get(12));
		grafo.addEdge(nodos.get(10), nodos.get(11));
		grafo.addEdge(nodos.get(10), nodos.get(14));
		grafo.addEdge(nodos.get(11), nodos.get(15));
		grafo.addEdge(nodos.get(12), nodos.get(13));
		grafo.addEdge(nodos.get(13), nodos.get(16));
		grafo.addEdge(nodos.get(14), nodos.get(20));
		grafo.addEdge(nodos.get(15), nodos.get(16));
		grafo.addEdge(nodos.get(15), nodos.get(20));
		grafo.addEdge(nodos.get(16), nodos.get(17));
		grafo.addEdge(nodos.get(16), nodos.get(20));
		grafo.addEdge(nodos.get(17), nodos.get(18));
		grafo.addEdge(nodos.get(18), nodos.get(19));
		grafo.addEdge(nodos.get(18), nodos.get(28));
		grafo.addEdge(nodos.get(19), nodos.get(21));
		grafo.addEdge(nodos.get(20), nodos.get(21));
		grafo.addEdge(nodos.get(20), nodos.get(22));
		grafo.addEdge(nodos.get(21), nodos.get(25));
		grafo.addEdge(nodos.get(21), nodos.get(29));
		grafo.addEdge(nodos.get(22), nodos.get(23));
		grafo.addEdge(nodos.get(22), nodos.get(24));
		grafo.addEdge(nodos.get(24), nodos.get(25));
		grafo.addEdge(nodos.get(25), nodos.get(26));
		grafo.addEdge(nodos.get(26), nodos.get(27));
		grafo.addEdge(nodos.get(27), nodos.get(28));
		grafo.addEdge(nodos.get(28), nodos.get(29));

		
	}

	public Map<Nodo, List<Nodo>> getAdjVertices() {
		return adjVertices;
	}

	public void setAdjVertices(Map<Nodo, List<Nodo>> adjVertices) {
		this.adjVertices = adjVertices;
	}

	public int getHeuristica(Nodo ubicacion) {
		return this.dijkstra(ubicacion, this.getVertex(11)); //distancia entre el actual y nodo 11
	}
	
	private int dijkstra(Nodo origen, Nodo destino) {
	    Map<Nodo, Integer> distancia = new HashMap<>();
	    Set<Nodo> visitados = new HashSet<>();
	    PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(distancia::get));
	    
	    // Inicializar la distancia de todos los nodos como infinito, excepto el nodo origen que tiene distancia 0
	    for (Nodo nodo : adjVertices.keySet()) {
	        distancia.put(nodo, Integer.MAX_VALUE);
	    }
	    distancia.put(origen, 0);
	    cola.add(origen);
	    
	    while (!cola.isEmpty()) {
	        Nodo actual = cola.poll();
	        visitados.add(actual);
	        List<Nodo> vecinos = adjVertices.get(actual);
	        for (Nodo vecino : vecinos) {
	            if (!visitados.contains(vecino)) {
	                int peso = distancia.get(actual) + 1;
	                if (peso < distancia.get(vecino)) {
	                    distancia.put(vecino, peso);
	                    cola.add(vecino);
	                }
	            }
	        }
	    }
	    
	    return distancia.get(destino);
	}



	
}
    

