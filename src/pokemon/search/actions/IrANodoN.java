package pokemon.search.actions;


import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoJugador;
import pokemon.search.EstadoAmbiente;

public class IrANodoN extends SearchAction {
	Integer nodoNro;
	
	public IrANodoN(Integer i) {
		this.nodoNro = i;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoJugador agState = (EstadoJugador) s;
        
        Nodo nodoActual = agState.getUbicacion();
        Nodo destino = agState.getMapa().getVertex(nodoNro);
        if(agState.getMapa().getNeighbors(destino).contains(nodoActual)) {
        	agState.setUbicacion(destino);
        	agState.setHuyoUltimoNodo(false);
    		return agState;
        }
        
        return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		System.out.println("Entra aca?");
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
		Nodo nodoActual = agState.getUbicacion();
		 Nodo destino = agState.getMapa().getVertex(nodoNro);
	     if(agState.getMapa().getNeighbors(destino).contains(nodoActual)) {
        	agState.setUbicacion(destino);
        	agState.setHuyoUltimoNodo(false);
        	environmentState.setUbicacion(destino);
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
		return "IrANodo " + nodoNro;
	}
}
