package pokemon.search.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarSatelite extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
		 if(agState.getPoderes().get(3).getCantCiclos() == 0 && agState.getPoderes().get(3).getPuedoUsar()) {
			 return agState;
		 } 
		 return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return 0.0; //no creo q tenga costo
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
		 if(agState.getPoderes().get(3).getCantCiclos() == 0 && agState.getPoderes().get(3).getPuedoUsar()) {
			 agState.setMapa(environmentState.getGrafo());
        	return environmentState;
        }
		
		return null;
	}

	@Override
	public String toString() {
		return "Elegir Usar Satelite";
	}

}
