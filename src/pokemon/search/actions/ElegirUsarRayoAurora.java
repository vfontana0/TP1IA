package pokemon.search.actions;

<<<<<<< HEAD
import domain.Nodo;
=======
>>>>>>> master
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import pokemon.search.EstadoAmbiente;
import pokemon.search.EstadoJugador;

public class ElegirUsarRayoAurora extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		 EstadoJugador agState = (EstadoJugador) s;
<<<<<<< HEAD
		 if(agState.getPoderes().get(0).getCantCiclos() == 0 && agState.getPoderes().get(0).getPuedoUsar()) {
=======
		 if(agState.getPoderes().get(0).getCantCiclos() == 3 && agState.getPoderes().get(0).getPuedoUsar()) {
>>>>>>> master
			 agState.setEnergia(agState.getEnergia()*1.20);
			 agState.getPoderes().get(0).setCantCiclos(3);
			 agState.getPoderes().get(0).setPuedoUsar(false);
			 return agState;
		 } 
		 return null;
	}

	@Override
	public Double getCost() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return 0.0; //no creo q tenga costo
=======
		return 3.0; //De los tres poderes, este es el mas costoso.
>>>>>>> master
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		EstadoAmbiente environmentState = (EstadoAmbiente) est;
		EstadoJugador agState = (EstadoJugador) ast;
		
<<<<<<< HEAD
		 if(agState.getPoderes().get(0).getCantCiclos() == 0 && agState.getPoderes().get(0).getPuedoUsar()) {
=======
		 if(agState.getPoderes().get(0).getCantCiclos() == 3 && agState.getPoderes().get(0).getPuedoUsar()) {
>>>>>>> master
			 agState.setEnergia(agState.getEnergia()*1.20);
			 agState.getPoderes().get(0).setCantCiclos(3);
			 agState.getPoderes().get(0).setPuedoUsar(false);
        	return environmentState;
        }
		
		return null;
	}


	@Override
	public String toString() {
		return "Elegir usar Rayo Aurora";
	}

}
