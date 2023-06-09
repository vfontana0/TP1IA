package pokemon.search.actions;

import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarSatelite extends SearchAction{
	Double costo = 0.0;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
		 if(agState.getPoderes().get(3).getCantCiclos() == 0 && agState.getPoderes().get(3).getPuedoUsar()) {
			 agState.getPoderes().get(3).setCantCiclos(11-agState.getNivel());
			 agState.getPoderes().get(3).setPuedoUsar(false);
			 return agState;
		 } 
		 return null;
	} 

	@Override
	public Double getCost() {
		return this.costo;  //No tiene costo, para que si lo tiene disponible lo use.
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
		 if(agState.getPoderes().get(3).getCantCiclos() == 0 && agState.getPoderes().get(3).getPuedoUsar()) {
				for(Nodo n : environmentState.getGrafo().getAllVertices()) { //para cada numero de nodo
					agState.getMapa().getVertex(n.getNumero()).actualizar(n);
				}
			 agState.getPoderes().get(3).setCantCiclos(11-agState.getNivel());
			 agState.getPoderes().get(3).setPuedoUsar(false);
			 
        	 return environmentState;
        }
		
		return null;
	}

	@Override
	public String toString() {
		return "Elegir Usar Satelite";
	}
	
	public void setCost(Double costo) {
		this.costo = costo;
	}
}
