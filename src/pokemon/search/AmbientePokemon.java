package pokemon.search;

import datastructures.Graph;
import domain.Nodo;
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
	        perception.setHayPokemonNodoActual(actual.getTienePokemon());
	        perception.setHayPokebolaNodoActual(actual.getTienePokebola());
	        System.out.println("Primeras dos percepciones: ok");
	        if(actual.getTienePokebola())
	        	perception.setEnergiaPokebolaNodoActual(actual.getPokebola().getPuntos());
	        if(actual.getTienePokemon()) {
	        	perception.setEnergiaPokemonNodoActual(actual.getPokemon().getEnergia());
	        	perception.setPokemonVencido(!actual.getPokemon().getVivo());
	        	perception.setPokemonEsMaestro(actual.getPokemon().getEsMaestro());
	        }
			return perception;
		}
		
		@Override
		public String toString() {
			return this.getEnvironmentState().toString();
		}
		
		
		
		
		
		
}
