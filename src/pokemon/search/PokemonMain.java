package pokemon.search;

import java.util.ArrayList;

import datastructures.Graph;
import domain.Nodo;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PokemonMain {
    public static void main(String[] args) throws PrologConnectorException {
    	//TODO Aca setear nodo inicial enttre los noos creados y luego pasarlo como parametro a los dos estados
    	Graph grafoAmbiente = new Graph();
    	Graph grafoAgente = new Graph();
    	initGrafo(grafoAmbiente);
    	initGrafo(grafoAgente);
        AmbientePokemon environment = new AmbientePokemon(grafoAmbiente); //ambiente pokemon PASAR GRAFO Y CARGAR COSAS
        Jugador agent = new Jugador(grafoAgente); //jugador --> crea PASAR GRAFO NOMAS
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }
    
    public static void initGrafo(Graph grafo) {
		ArrayList<Nodo> nodos = new ArrayList<>();
		nodos.add(0, null);
		for(int i=1; i<=29; i++) {
			Nodo actual = new Nodo();
			actual.setNumero(i);
			actual.setTienePokebola(false);
			actual.setPokebola(null);
			actual.setTienePokemon(false);
			actual.setPokemon(null);
			nodos.add(i, actual);
			grafo.addVertex(actual);
		}

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
}
