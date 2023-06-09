package pokemon.search.actions;

import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirPelear extends SearchAction{
	Double costo = 0.0;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		EstadoJugador estadoJugador = (EstadoJugador) s;
		estadoJugador.incrementarCosto(this.getCost());
		Nodo actual = estadoJugador.getUbicacion();
		//chequear si tiene pokemon, si esta vivo, y si el agente tiene mas energia q el pokemon
		if(actual.getTienePokemon()  && !estadoJugador.getHuyoUltimoNodo() && estadoJugador.getEnergia() > actual.getPokemon().getEnergia()) {
			Double energiaPokemon = actual.getPokemon().getEnergia();
			Double energiaAgente = estadoJugador.getEnergia();
			estadoJugador.setEnergia(energiaAgente - energiaPokemon + energiaPokemon*0.2);
			Double energiaGanada = estadoJugador.getEnergiaGanada();
			estadoJugador.setEnergiaGanada(energiaGanada + energiaPokemon*0.2);
			
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
		estadoJugador.incrementarCosto(this.getCost());
		EstadoAmbiente estadoAmbiente = (EstadoAmbiente) est;
		Nodo actualAgente = estadoJugador.getUbicacion();
		Nodo actualAmbiente = estadoAmbiente.getUbicacion();

		//chequear si tiene pokemon, si esta vivo, y si el agente tiene mas energia q el pokemon
		if(actualAgente.getTienePokemon() && !estadoJugador.getHuyoUltimoNodo() && estadoJugador.getEnergia() > actualAgente.getPokemon().getEnergia()) {
			Double energiaPokemon = actualAgente.getPokemon().getEnergia();
			Double energiaAgente = estadoJugador.getEnergia();
			Double energiaGanada = estadoJugador.getEnergiaGanada();

			//actualizo energia y energia ganada
			estadoJugador.setEnergia(energiaAgente - energiaPokemon + energiaPokemon*0.2);
			estadoJugador.setEnergiaGanada(energiaGanada + energiaPokemon*0.2);
			estadoAmbiente.setEnergia(energiaAgente - energiaPokemon + energiaPokemon*0.2);
			
			//actualizo nodo del ambiente
			actualAmbiente.getPokemon().setVivo(false); 
			actualAmbiente.setPokemon(null);
			actualAmbiente.setTienePokemon(false);
			
			//actualizo nodo del agente
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


	public Double getCost() {
		return this.costo;  //Pelear y Huir tienen el mismo costo para que se elija 
				//uno u otro según la vida del pokemon adversario y no según este costo.
	}
	
	public void setCost(Double costo) {
		this.costo = costo;
	}
}
