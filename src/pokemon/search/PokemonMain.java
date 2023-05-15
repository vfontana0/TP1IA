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
	static ArrayList<Pair<Action, Double>> accionesEjecutadas;
	private static Integer nodoInicio;
	private static Boolean gano;
    public static void main(String[] args) throws PrologConnectorException {
    	PokemonMain.startPokemon();
    }
    
	public static void startPokemon() {
    	//Creacion de mapas del ambiente y agente
    	Graph grafoAmbiente = new Graph();
    	Graph grafoAgente = new Graph();
    	
    	//Seteo del nodo inicial, estrategia y energia del agente y maestro
    	Datos.nodoInicio = (new Random()).nextInt(29) + 1;
    	Datos.energiaJugador = (new Random()).nextDouble(10)+10;
    	Datos.nodoMaestro = 18;
    	Datos.energiaMaestro = 10.0;
    	Datos.cantAdversarios = 11;
    	if (Datos.nroEstrategia == null) Datos.nroEstrategia = 4;
    	System.out.println("Estrategia: " + Datos.nroEstrategia);
    	System.out.println("Nodo inicial: " + Datos.nodoInicio);
    	
    	//agregar nodos y conexiones al mapa
    	grafoAmbiente.initGrafo();
    	grafoAgente.initGrafo();
    	
        AmbientePokemon environment = new AmbientePokemon(grafoAmbiente);//ambiente pokemon PASAR GRAFO Y CARGAR COSAS
        Jugador agent = new Jugador(grafoAgente); //jugador --> crea PASAR GRAFO NOMAS
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
        accionesEjecutadas = agent.getSearchActions();
        gano = agent.gano();
	}

	public ArrayList<Pair<Action, Double>> getAccionesEjecutadas() {
		return accionesEjecutadas;
	}

	public void setAccionesEjecutadas(ArrayList<Pair<Action, Double>> accionesEjecutadas) {
		PokemonMain.accionesEjecutadas = accionesEjecutadas;
	}
	
	public Integer getNodoInicio() {
		return nodoInicio;
	}
	
	public Boolean getGano() {
		return PokemonMain.gano;
	}

}
