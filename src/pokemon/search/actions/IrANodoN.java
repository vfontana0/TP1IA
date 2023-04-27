package pokemon.search.actions;

import frsf.cidisi.exercise.aspiradora.search.EstadoAspiradora;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class IrANodoN extends SearchAction {
	int numeroNodo;
	
	public IrANodoN(int numeroNodo) {
		this.numeroNodo = numeroNodo;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoJugador agState = (EstadoJugador) s;
        
        
	}
}
