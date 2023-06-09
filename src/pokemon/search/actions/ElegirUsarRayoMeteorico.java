package pokemon.search.actions;

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarRayoMeteorico extends SearchAction{
	Double costo = 0.0;
	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
		 agState.incrementarCosto(this.getCost());
		 if(agState.getPoderes().get(1).getCantCiclos() == 0 && agState.getPoderes().get(1).getPuedoUsar() && agState.getNivel() >= 4) {
			 agState.setEnergia(agState.getEnergia()*1.30);
			 agState.getPoderes().get(1).setCantCiclos(3);
			 agState.getPoderes().get(1).setPuedoUsar(false);
			 return agState;
		 } 
		 return null;
	} //EN EL METODO UPDATE STATE SE DECREMENTAN ESTOS VALORES --> Ciclo percepcion/accion

	@Override
	public Double getCost() {
		return this.costo;  //El segundo menos costoso
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		agState.incrementarCosto(this.getCost());
		 if(agState.getPoderes().get(1).getCantCiclos() == 0 && agState.getPoderes().get(1).getPuedoUsar() && agState.getNivel() >= 4) {
			 agState.setEnergia(agState.getEnergia()*1.30);
			 environmentState.setEnergia(agState.getEnergia()*1.30);
			 agState.getPoderes().get(1).setCantCiclos(3);
			 agState.getPoderes().get(1).setPuedoUsar(false);
        	return environmentState;
        }
		
		return null;
	}

	@Override
	public String toString() {
		return "Elegir Usar Rayo Meteorico";
	}
	
	public void setCost(Double costo) {
		this.costo = costo;
	}

}
