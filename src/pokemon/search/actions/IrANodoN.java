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
	Double costo = 0.0;
	public IrANodoN(Integer i) {
		this.nodoNro = i;
	}
	
	public Integer getNumero() {
		return nodoNro;
	}
	
	@Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoJugador agState = (EstadoJugador) s;
        agState.incrementarCosto(this.getCost());
        Nodo nodoActual = agState.getUbicacion();
        Nodo destino = agState.getMapa().getVertex(nodoNro);
	     if((!nodoActual.getTienePokemon() || agState.getHuyoUltimoNodo()) && agState.getMapa().getNeighbors(destino).contains(nodoActual)) {
        	agState.setUbicacion(destino);
        	agState.setHuyoUltimoNodo(false);
    		return agState;
        }
        
        return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		agState.incrementarCosto(this.getCost());
		 Nodo nodoActual = agState.getUbicacion();
		 Nodo destinoAgente = agState.getMapa().getVertex(nodoNro); //debe ser el de destino 
		 Nodo destinoAmbiente = environmentState.getGrafo().getVertex(nodoNro);
	     if((!nodoActual.getTienePokemon() || agState.getHuyoUltimoNodo())
	    		 && agState.getMapa().getNeighbors(destinoAgente).contains(nodoActual)) {
        	agState.setUbicacion(destinoAgente);
        	agState.setHuyoUltimoNodo(false);
        	environmentState.setUbicacion(destinoAmbiente);
        	return environmentState;
        }
		
		return null;
	}

	@Override
	public Double getCost() {
		return this.costo;  //No tiene costo moverse, pero si pelear o huir.
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "IrANodo " + nodoNro;
	}
	
	public void setCost(Double costo) {
		this.costo = costo;
	}
}
