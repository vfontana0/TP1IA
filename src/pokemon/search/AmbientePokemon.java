package pokemon.search;

import domain.Nodo;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbientePokemon extends Environment{
	    public AmbientePokemon() {
	        // Create the environment state
	        this.environmentState = new EstadoAmbiente();
	    }

		@Override
		public Perception getPercept() {
			PokemonPerception perception = new PokemonPerception();
	        Nodo actual = ((EstadoAmbiente) environmentState).getUbicacion();
	        perception.setHayPokemonNodoActual(actual.getTienePokemon());
	        perception.setHayPokebolaNodoActual(actual.getTienePokebola());
	        perception.setEnergiaPokebolaNodoActual(actual.getPokebola().getPuntos());
	        perception.setEnergiaPokemonNodoActual(actual.getPokemon().getEnergia());
	        perception.setPokemonVencido(!actual.getPokemon().getVivo());
			return perception;
		}
		
		
		
		
}
