package pokemon.search.actions;

import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class JuntarPokebola extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoJugador estado = (EstadoJugador) s;
		if(estado.getUbicacion().getTienePokebola()) {
			estado.setEnergia(estado.getEnergia() + estado.getUbicacion().getPokebola().getPuntos());
			estado.setEnergiaGanada(estado.getEnergiaGanada() + estado.getUbicacion().getPokebola().getPuntos());
			return estado; 
		} 
		return null;
	}

	@Override
	public Double getCost() {
<<<<<<< HEAD
		return 0.0;
=======
		return 0.0; //Siempre que llegue a una pokebola va a elegir absorber su energía.
>>>>>>> master
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoJugador estadoJugador = (EstadoJugador) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		if(estadoJugador.getUbicacion().getTienePokebola()) {
			estadoJugador.setEnergia(estadoJugador.getEnergia() + estadoJugador.getUbicacion().getPokebola().getPuntos());
			estadoJugador.setEnergiaGanada(estadoJugador.getEnergiaGanada() + estadoJugador.getUbicacion().getPokebola().getPuntos());
			estadoAmbiente.setEnergia(estadoJugador.getEnergia() + estadoJugador.getUbicacion().getPokebola().getPuntos());
			Nodo actualAmbiente = estadoAmbiente.getUbicacion(); //cambio el nodo en el ambiente
			actualAmbiente.setPokebola(null);
			actualAmbiente.setTienePokebola(false);
			
			Nodo actualAgente = estadoJugador.getUbicacion(); //cambio el nodo en el jugador
			actualAgente.setPokebola(null);
			actualAgente.setTienePokebola(false);
			return estadoAmbiente; 
		} 
		return null;
				
	}

	@Override
	public String toString() {
		return "Juntar Pokebola";
	}
	

	
}
