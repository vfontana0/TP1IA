package pokemon.search;

import java.util.List;

import datastructures.Graph;
import domain.Nodo;
import domain.PercepcionNodo;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbientePokemon extends Environment{
	    public AmbientePokemon(Graph grafo) {
	        // Create the environment state
	        this.environmentState = new EstadoAmbiente(grafo);
	    }

		@Override
		public Perception getPercept() {
			PokemonPerception perception = new PokemonPerception();
	        Nodo actual = ((EstadoAmbiente) environmentState).getUbicacion();
	        List<Nodo> vecinos =((EstadoAmbiente) environmentState).getGrafo().getNeighbors(actual);
	        for(Nodo vecino : vecinos) {
	        	perception.getPercepcionesAdyacentes().put(vecino.getNumero(), new PercepcionNodo(vecino));
	        }
	        return perception;
		}
		
		@Override
		public String toString() {
			return this.getEnvironmentState().toString();
		}
		
		
		
		
		
		
}
