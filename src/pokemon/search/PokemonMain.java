package pokemon.search;


import java.util.ArrayList;
import java.util.Random;

import datastructures.Graph;
import domain.Nodo;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import javafx.util.Pair;

public class PokemonMain {
	ArrayList<Pair<Action, Double>> accionesEjecutadas;
	private static Integer nodoInicio;
	private Boolean gano;
    public static void main(String[] args) throws PrologConnectorException {
    
    	//Creacion de mapas del ambiente y agente
    	Graph grafoAmbiente = new Graph();
    	Graph grafoAgente = new Graph();
    	
    	//Seteo del nodo inicial, estrategia y energia
    	Integer nodoInicio = (new Random()).nextInt(29) + 1;
        Integer nroEstrategia = 5; 
    	Double energia = (new Random()).nextDouble(10)+10;
    	
    	
    	//agregar nodos y conexiones al mapa
    	grafoAmbiente.initGrafo();
    	grafoAgente.initGrafo();
    	
    	
        AmbientePokemon environment = new AmbientePokemon(grafoAmbiente, nodoInicio, energia);//ambiente pokemon PASAR GRAFO Y CARGAR COSAS
        Jugador agent = new Jugador(grafoAgente, nodoInicio, energia, nroEstrategia); //jugador --> crea PASAR GRAFO NOMAS
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }
    

    
	public void startPokemon() {
    	//TODO Aca setear nodo inicial enttre los noos creados y luego pasarlo como parametro a los dos estados
    	Graph grafoAmbiente = new Graph();
    	Graph grafoAgente = new Graph();
    	//Integer nodoInicio = (new Random()).nextInt(29) + 1;
    	nodoInicio = 27;
    	Double energia = new Random().nextDouble(10)+10;
    	System.out.println("\u001B[32m" + "Arranca en Nodo " + nodoInicio + "\u001B[0m");
    	grafoAmbiente.initGrafo();
    	grafoAgente.initGrafo();
    	
        AmbientePokemon environment = new AmbientePokemon(grafoAmbiente, nodoInicio, energia); //ambiente pokemon PASAR GRAFO Y CARGAR COSAS
        Integer nroEstrategia = 2;
        Jugador agent = new Jugador(grafoAgente, nodoInicio, energia, nroEstrategia); //jugador --> crea PASAR GRAFO NOMAS
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        accionesEjecutadas = agent.getSearchActions();
        simulator.start();
        this.gano = agent.gano();

	}

	public ArrayList<Pair<Action, Double>> getAccionesEjecutadas() {
		return accionesEjecutadas;
	}

	public void setAccionesEjecutadas(ArrayList<Pair<Action, Double>> accionesEjecutadas) {
		this.accionesEjecutadas = accionesEjecutadas;
	}
	
	public Integer getNodoInicio() {
		return nodoInicio;
	}
	
	public Boolean getGano() {
		return this.gano;
	}

}
