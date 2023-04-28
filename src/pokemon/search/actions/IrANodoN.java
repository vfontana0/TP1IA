package pokemon.search.actions;


import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoJugador;
import pokemon.search.EstadoAmbiente;

public class IrANodoN extends SearchAction {
	Nodo nodo;
	
	public IrANodoN(Nodo nodo) {
		this.nodo = nodo;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoJugador agState = (EstadoJugador) s;
        
        Nodo nodoActual = agState.getUbicacion();
        if (nodoActual.getNodosAdyacentes().contains(nodo)) {
        	agState.setUbicacion(nodo);
        	//Falta el visitado en el grafo?
    		return agState;
        }
        
        return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
		Nodo nodoActual = agState.getUbicacion();
        if (nodoActual.getNodosAdyacentes().contains(nodo)) {
        	agState.setUbicacion(nodo);
        	//Falta el visitado en el grafo?
        	
        	environmentState.setUbicacion(nodo);
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
