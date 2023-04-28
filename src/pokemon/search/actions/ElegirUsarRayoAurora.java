package pokemon.search.actions;


import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoJugador;
import pokemon.search.EstadoAmbiente;

public class ElegirUsarRayoAurora extends SearchAction {
	Nodo nodo;
	
	public ElegirUsarRayoAurora(Nodo nodo) {
		this.nodo = nodo;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
				EstadoJugador agState = (EstadoJugador) s;
				
		        if (agState.getPoderes().get(1).getPuedoUsar()) {
		        	agState.getPoderes().get(1).setCantCiclos(3);
		        	agState.getPoderes().get(1).setPuedoUsar(false);
		        	agState.setEnergiaGanada(agState.getEnergia()*0.2);
		        	agState.setEnergia(agState.getEnergia()*1.2);
		        	
		        	return agState;
		        }
				
				return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
        if (environmentState.getPoderes().get(1).getPuedoUsar()) {
        	environmentState.getPoderes().get(1).setCantCiclos(3);
        	environmentState.getPoderes().get(1).setPuedoUsar(false);
        	agState.setEnergiaGanada(agState.getEnergia()*0.2);
        	agState.setEnergia(agState.getEnergia()*1.2);
        	
        	return environmentState;
        }
		
		return null;
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return Double.valueOf(0); //No tiene costo moverse, pero si pelear o escaparse
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "IrANodo " + nodo.getNumero();
	}
}
