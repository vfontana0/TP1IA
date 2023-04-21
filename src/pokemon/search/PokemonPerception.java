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
	
	
	
	
	@Override
 public void initPerception(Agent agentIn, Environment environmentIn) {
    	
        Agente agent = (Agente) agentIn;
        AmbientePokemon environment = (AmbientePokemon) environmentIn;
        EstadoAmbiente environmentState = (EstadoAmbiente) environment.getEnvironmentState();
       //ver por que castea

        
        Nodo actual = environmentState.getUbicacion();
 
        hayPokemonNodoActual = actual.getTienePokemon();
        hayPokebolaNodoActual = actual.getTienePokebola();
        energiaPokemonNodoActual = actual.getPokemon().getEnergia();
        energiaPokebolaNodoActual = actual.getPokebola().getPuntos();
        pokemonVencido = !(actual.getPokemon().getVivo());
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
	

}
