package pokemon.search.actions;

import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirPelear extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoJugador estadoJugador = (EstadoJugador) s;
		Nodo actual = estadoJugador.getUbicacion();
		//chequear si tiene pokemon, si esta vivo, y si el agente tiene mas energia q el pokemon
		if(actual.getTienePokemon()  && estadoJugador.getEnergia() > actual.getPokemon().getEnergia()) {
			Double energiaPokemon = actual.getPokemon().getEnergia();
			Double energiaAgente = estadoJugador.getEnergia();
			estadoJugador.setEnergia(energiaAgente - energiaPokemon + energiaPokemon*0.2);
			
			actual.setTienePokemon(false);
			actual.getPokemon().setVivo(false);
			actual.setPokemon(null);
			
			return estadoJugador;
		}
		return null;
	}


	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoJugador estadoJugador = (EstadoJugador) ast;
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo actualAgente = estadoJugador.getUbicacion();
		Nodo actualAmbiente = estadoAmbiente.getUbicacion();

		//chequear si tiene pokemon, si esta vivo, y si el agente tiene mas energia q el pokemon
		if(actualAgente.getTienePokemon() && estadoJugador.getEnergia() > actualAgente.getPokemon().getEnergia()) {
			Double energiaPokemon = actualAgente.getPokemon().getEnergia();
			Double energiaAgente = estadoJugador.getEnergia();
			Double energiaGanada = estadoJugador.getEnergiaGanada();
			
			//si es el maestro, actualizo que lo mate
			//actualizar energia del agente
			estadoJugador.setEnergia(energiaAgente - energiaPokemon + energiaPokemon*0.2);
			estadoJugador.setEnergiaGanada(energiaGanada + energiaPokemon*0.2);
			
			// actualizar nodo del estado del ambiente --> actualizo solo el ambiente pq es el mismo nodo en los dos grafos
			// estoy actualizando el estado del agente al hacer esto
			actualAmbiente.getPokemon().setVivo(false); 
			actualAmbiente.setPokemon(null);
			actualAmbiente.setTienePokemon(false);
			
			actualAgente.getPokemon().setVivo(false); 
			actualAgente.setPokemon(null);
			actualAgente.setTienePokemon(false);
			

			return estadoAmbiente;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Elegir pelear";
	}
	

}
