package src.pokemon.search.actions;

import src.domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import src.pokemon.search.EstadoAmbiente;
import src.pokemon.search.EstadoJugador;

public class ElegirHuir extends SearchAction {

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoJugador estado= (EstadoJugador) s;
		Nodo actual = estado.getUbicacion();
		Double energiaJugador = estado.getEnergia();
		if(actual.getTienePokemon() 
				&& actual.getPokemon().getVivo()
				&& !estado.getHuyoUltimoNodo() 
				&& (energiaJugador - actual.getPokemon().getEnergia()/4.0) > 0 ) {		
			estado.setEnergia(energiaJugador - actual.getPokemon().getEnergia()/4.0);
			estado.setHuyoUltimoNodo(true);
		return estado;
		}
		return null;
	}
	

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoJugador estado= (EstadoJugador) ast;
		EstadoAmbiente estadoAmbiente =  (EstadoAmbiente) est;
		Nodo actual = estado.getUbicacion();
		Double energiaJugador = estado.getEnergia();
		if(actual.getTienePokemon() 
				&& actual.getPokemon().getVivo()
				&& !estado.getHuyoUltimoNodo() 
				&& (energiaJugador - actual.getPokemon().getEnergia()/4.0) > 0 ) {
			estadoAmbiente.setEnergia(energiaJugador - actual.getPokemon().getEnergia()/4.0);
			estado.setEnergia(energiaJugador - actual.getPokemon().getEnergia()/4.0);
			estado.setHuyoUltimoNodo(true);
		return estadoAmbiente;
		}
		return null;
	}


	@Override
	public Double getCost() {
		return 4.0; //Pelear y Huir tienen el mismo costo para que se elija 
				//uno u otro según la vida del pokemon adversario y no según este costo.
	}

	
	@Override
	public String toString() {
		return "Elegir Huir";
	}

}
