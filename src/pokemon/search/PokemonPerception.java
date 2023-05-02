package pokemon.search;

import java.util.List;

import domain.Nodo;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PokemonPerception extends Perception{

	
	private Boolean hayPokemonNodoActual; //percepcion si hay o no pokemon en el nodo actual
	private Boolean hayPokebolaNodoActual; //percepcion si hay o no pokebola en el nodo actual
	private Double energiaPokemonNodoActual; //energia del pokemon en el nodo actual
	private Double energiaPokebolaNodoActual; //energia pokebola nodo actual
	private Boolean pokemonVencido; //true si se vencio al pokemon, false sino
	private Boolean pokemonEsMaestro;
	
	

	@Override
 public void initPerception(Agent agentIn, Environment environmentIn) {
    	
        Jugador agent = (Jugador) agentIn;
        AmbientePokemon environment = (AmbientePokemon) environmentIn;
        EstadoAmbiente environmentState = (EstadoAmbiente) environment.getEnvironmentState();
        Nodo actual = environmentState.getUbicacion();
        hayPokemonNodoActual = actual.getTienePokemon();
        hayPokebolaNodoActual = actual.getTienePokebola();
       if(hayPokemonNodoActual)
    	   energiaPokemonNodoActual = actual.getPokemon().getEnergia();
       if(hayPokebolaNodoActual)
    	   energiaPokebolaNodoActual = actual.getPokebola().getPuntos();
       if(hayPokemonNodoActual)
        pokemonVencido = !(actual.getPokemon().getVivo());
       if(hayPokemonNodoActual)
    	   pokemonEsMaestro = actual.getPokemon().getEsMaestro();
	}
   



	public Boolean getHayPokemonNodoActual() {
		return hayPokemonNodoActual;
	}


	public void setHayPokemonNodoActual(Boolean hayPokemonNodoActual) {
		this.hayPokemonNodoActual = hayPokemonNodoActual;
	}


	public Boolean getHayPokebolaNodoActual() {
		return hayPokebolaNodoActual;
	}

	public void setHayPokebolaNodoActual(Boolean hayPokebolaNodoActual) {
		this.hayPokebolaNodoActual = hayPokebolaNodoActual;
	}


	public Double getEnergiaPokemonNodoActual() {
		return energiaPokemonNodoActual;
	}


	public void setEnergiaPokemonNodoActual(Double energiaPokemonNodoActual) {
		this.energiaPokemonNodoActual = energiaPokemonNodoActual;
	}


	public Double getEnergiaPokebolaNodoActual() {
		return energiaPokebolaNodoActual;
	}


	public void setEnergiaPokebolaNodoActual(Double energiaPokebolaNodoActual) {
		this.energiaPokebolaNodoActual = energiaPokebolaNodoActual;
	}


	public Boolean getPokemonVencido() {
		return pokemonVencido;
	}

	public void setPokemonVencido(Boolean pokemonVencido) {
		this.pokemonVencido = pokemonVencido;
	}
	
	@Override
	public String toString() {
		return "Hay pokemon en nodo actual? --> " + this.getHayPokemonNodoActual() + " Hay pokebola nodo actual? --> " + this.getHayPokebolaNodoActual();
	}




	public Boolean getPokemonEsMaestro() {
		return pokemonEsMaestro;
	}




	public void setPokemonEsMaestro(Boolean pokemonEsMaestro) {
		this.pokemonEsMaestro = pokemonEsMaestro;
	}
	
	
	

}
