package pokemon.search.actions;


import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoJugador;

public class IrANodoN extends SearchAction {
	int numeroNodo;
	
	public IrANodoN(int numeroNodo) {
		this.numeroNodo = numeroNodo;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoJugador agState = (EstadoJugador) s;
		return agState;
        
        
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
