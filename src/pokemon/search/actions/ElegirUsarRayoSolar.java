package pokemon.search.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarRayoSolar extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
		 if(agState.getPoderes().get(2).getCantCiclos() == 0 && agState.getPoderes().get(2).getPuedoUsar() && agState.getNivel() >= 5) {
			 agState.setEnergia(agState.getEnergia()*1.50);
			 agState.getPoderes().get(2).setCantCiclos(3);
			 agState.getPoderes().get(2).setPuedoUsar(false);
			 return agState;
		 } 
		 return null;
	}

	@Override
	public Double getCost() {
		return 1.0; //Es el menor costo seteado, para que al tenerlo disponible 
					//lo use antes que cualquier otro, ya que es el más poderoso.
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		agState.incrementarCosto(this.getCost());
		 if(agState.getPoderes().get(2).getCantCiclos() == 0
				 && agState.getPoderes().get(2).getPuedoUsar() 
				 && agState.getNivel() >= 5) {
			 agState.setEnergia(agState.getEnergia()*1.50);
			 environmentState.setEnergia(agState.getEnergia()*1.50);
			 agState.getPoderes().get(2).setCantCiclos(3);
			 agState.getPoderes().get(2).setPuedoUsar(false);
        	return environmentState;
        }
		
		return null;
	}

	@Override
	public String toString() {
		return "Elegir usar Rayo Solar";
	}

}
