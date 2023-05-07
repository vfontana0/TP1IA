package pokemon.search.actions;

import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarRayoAurora extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
		 if(agState.getPoderes().get(0).getCantCiclos() == 0 && agState.getPoderes().get(0).getPuedoUsar()) {
			 agState.setEnergia(agState.getEnergia()*1.20);
			 agState.getPoderes().get(0).setCantCiclos(3);
			 agState.getPoderes().get(0).setPuedoUsar(false);
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
		
		 if(agState.getPoderes().get(0).getCantCiclos() == 0 && agState.getPoderes().get(0).getPuedoUsar()) {
			 agState.setEnergia(agState.getEnergia()*1.20);
			 agState.getPoderes().get(0).setCantCiclos(3);
			 agState.getPoderes().get(0).setPuedoUsar(false);
        	return environmentState;
        }
		
		return null;
	}


	@Override
	public String toString() {
		return "Elegir usar Rayo Aurora";
	}

}