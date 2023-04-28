package pokemon.search.actions;


import domain.Nodo;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoJugador;
import pokemon.search.EstadoAmbiente;

public class JuntarPokebola extends SearchAction {
	Nodo nodo;
	
	public JuntarPokebola(Nodo nodo) {
		this.nodo = nodo;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
		// TODO Auto-generated method stub
				EstadoJugador agState = (EstadoJugador) s;
				
				Nodo nodoActual = agState.getUbicacion();
		        if (nodoActual.getTienePokebola()) {
		        	double energiaGanada = nodoActual.getPokebola().getPuntos();
		        	agState.setEnergiaGanada(agState.getEnergiaGanada() + energiaGanada);
		        	nodoActual.setPokebola(null);
		        	nodoActual.setTienePokebola(null);
		        	return agState;
		        }
				
				return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		// TODO Auto-generated method stub
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
		Nodo nodoActual = environmentState.getUbicacion();
        if (nodoActual.getTienePokebola()) {
        	double energiaGanada = nodoActual.getPokebola().getPuntos();
        	agState.setEnergiaGanada(agState.getEnergiaGanada() + energiaGanada);
        	nodoActual.setPokebola(null);
        	nodoActual.setTienePokebola(null);
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
