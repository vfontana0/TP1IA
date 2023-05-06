package pokemon.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Nodo;
import domain.PercepcionNodo;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokemonPerception extends Perception{

	/*
	private Boolean hayPokemonNodoActual; //percepcion si hay o no pokemon en el nodo actual
	private Boolean hayPokebolaNodoActual; //percepcion si hay o no pokebola en el nodo actual
	private Double energiaPokemonNodoActual; //energia del pokemon en el nodo actual
	private Double energiaPokebolaNodoActual; //energia pokebola nodo actual
	private Boolean pokemonVencido; //true si se vencio al pokemon, false sino
	private Boolean pokemonEsMaestro;
	*/
	
	private HashMap<Integer, PercepcionNodo> percepcionesAdyacentes = new HashMap<>(); //tengo una lista de nodos adyacentes donde la clave es un entero
	private Nodo actual;

	@Override
 public void initPerception(Agent agentIn, Environment environmentIn) {
    	
        Jugador agent = (Jugador) agentIn;
        AmbientePokemon environment = (AmbientePokemon) environmentIn;
        EstadoAmbiente environmentState = (EstadoAmbiente) environment.getEnvironmentState();
        this.actual = environmentState.getUbicacion();
        Nodo actual = environmentState.getUbicacion();
        List<Nodo> vecinos = environmentState.getGrafo().getNeighbors(actual);
        for(Nodo vecino : vecinos) {
        	percepcionesAdyacentes.put(vecino.getNumero(), new PercepcionNodo(vecino));
        }
            
	}
	

	public HashMap<Integer, PercepcionNodo> getPercepcionesAdyacentes() {
		return percepcionesAdyacentes;
	}


	public void setPercepcionesAdyacentes(HashMap<Integer, PercepcionNodo> percepcionesAdyacentes) {
		this.percepcionesAdyacentes = percepcionesAdyacentes;
	}
	
	


	public Nodo getActual() {
		return actual;
	}


	public void setActual(Nodo actual) {
		this.actual = actual;
	}


	@Override
	public String toString() {
		String str = "\n ------------------------------------------------------------------------- \n";
		for (Map.Entry<Integer, PercepcionNodo> entry : this.getPercepcionesAdyacentes().entrySet()) {
			if(entry.getKey() == this.actual.getNumero())
				str = str + "\u001B[32m";
			str = str + "Percepcion Nodo " + entry.getKey() + ": " +  entry.getValue().toString() + "\n";
			if(entry.getKey() == this.actual.getNumero())
				str = str + "\u001B[0m";
		}
		str = str + "----------------------------------------------------------------------------- \n";
		return str;
	}

}
	
